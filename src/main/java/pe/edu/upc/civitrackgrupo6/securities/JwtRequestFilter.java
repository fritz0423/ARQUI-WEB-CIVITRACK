package pe.edu.upc.civitrackgrupo6.securities;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import pe.edu.upc.civitrackgrupo6.servicesImplement.JwtUserDetailsService;

import java.io.IOException;
import java.util.List;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        String path = request.getRequestURI();
        if (path.startsWith("/swagger-ui") || path.startsWith("/v3/api-docs") || path.startsWith("/swagger-resources") || path.startsWith("/webjars")) {
            chain.doFilter(request, response);
            return;
        }


        final String requestTokenHeader = request.getHeader("Authorization");
        String username = null;
        String jwtToken = null;

        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            jwtToken = requestTokenHeader.substring(7);
            try {
                username = jwtTokenUtil.getUsernameFromToken(jwtToken);
            } catch (IllegalArgumentException e) {
                logger.warn("No se pudo obtener el token JWT", e);
            } catch (ExpiredJwtException e) {
                logger.warn("Token JWT expirado", e);
            }
        } else {
            // no token; skip
        }

        // solo si no hay auth en contexto
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            // Opcional: cargar userDetails si lo necesitas para validateToken
            UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(username);

            if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {
                // Extraer claim "role" y reconstruir autoridad con prefijo ROLE_
                var claims = jwtTokenUtil.getAllClaimsFromToken(jwtToken);
                String roleClaim = (String) claims.get("role"); // "ADMIN" o "USER"

                GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + roleClaim);
                List<GrantedAuthority> authorities = List.of(authority);

                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(userDetails, null, authorities);
                auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }

        chain.doFilter(request, response);
    }
}
