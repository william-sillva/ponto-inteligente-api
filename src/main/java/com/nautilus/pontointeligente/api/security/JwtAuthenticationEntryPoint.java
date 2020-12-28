package com.nautilus.pontointeligente.api.security;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {

        /*
         * sendError nao esta retornando mensagem customizada no response.
         */
//        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Acesso negado. Você deve estar autenticado no sistema para acessar a URL solicitada.");

        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getOutputStream().println("{ " +
                    "\"timestamp\": \"" + this.dateFormat.format(new Date()) + "\"," +
                    "\"status\": \"" + response.getStatus() + "\"," +
                    "\"error\": \"" + HttpStatus.UNAUTHORIZED.getReasonPhrase() + "\"," +
                    "\"message\": \"" + "Acesso negado. Você deve estar autenticado no sistema para acessar a URL solicitada." + "\"," +
                    "\"path\": \"" + request.getServletPath() + "\"" +
                "}");

    }
}
