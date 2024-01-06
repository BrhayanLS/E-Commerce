package ecommerce.security.jwt;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class JwtEntryPoint implements AuthenticationEntryPoint {

    //Este metodo controla el error que devuelve el backend cuando un usuario sin permisos intenta realizar una accion
    @Override
    public void commence(jakarta.servlet.http.HttpServletRequest req, jakarta.servlet.http.HttpServletResponse res, AuthenticationException e) throws IOException, jakarta.servlet.ServletException {
        res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "No autorizado");
    }
}
