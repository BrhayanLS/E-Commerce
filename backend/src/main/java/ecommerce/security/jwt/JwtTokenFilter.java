package ecommerce.security.jwt;

import ecommerce.security.services.UserDetailsServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

import java.io.IOException;

public class JwtTokenFilter extends OncePerRequestFilter {
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(JwtTokenFilter.class);
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Value("${jwt.accessTokenCookieName}")
    private String cookieName;

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = getToken(req);
            if (token != null && jwtProvider.validateToken(token)) {
                String userName = jwtProvider.getUserNameFromToken(token);
                UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(userName);
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(auth);

            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        filterChain.doFilter(req, res);
    }

    //Vamos a crear un metodo que interprete las peticiones http y les añada el token
    //Debemos quitarle la palabra Bearer del header y validarlo
    /*
    Cambiamos la forma de obtener el token, ahora se obtendra directamente de la cookie

    private String getToken(HttpServletRequest request){
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer"))
            return header.replace("Bearer ", "");
        return null;
    }*/
    private String getToken(HttpServletRequest request) {
        Cookie cookie = WebUtils.getCookie(request, cookieName);
        return cookie != null ? cookie.getValue():null;
    }
}
