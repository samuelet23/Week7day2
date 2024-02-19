package Week7.Day1.security;

import Week7.Day1.Exception.NotFoundElementException;
import Week7.Day1.Exception.UnAuthorizedException;
import Week7.Day1.model.Dipendente.Dipendente;
import Week7.Day1.service.DipendenteService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.security.Security;

public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTools jwtTools;
    @Autowired
    private DipendenteService dipendenteService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
       String authorization = request.getHeader("Authorization");

       if (authorization == null || !authorization.startsWith("Bearer")){
           throw new UnAuthorizedException("Token non presente");
       }
       String token = authorization.substring(7);
       jwtTools.validaToken(token);
       String username  = jwtTools.extractUsernameFromToken(token);

       Dipendente dipendente;
        try {
             dipendente = dipendenteService.getByUsername(username);
        } catch (NotFoundElementException e) {
            throw new RuntimeException(e);
        }

        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(dipendente, null);
        SecurityContextHolder.getContext().setAuthentication(auth);

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return new AntPathMatcher().match("/auth/**", request.getServletPath());
    }
}
