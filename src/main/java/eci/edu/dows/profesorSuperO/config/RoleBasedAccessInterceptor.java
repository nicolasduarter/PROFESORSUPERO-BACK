package eci.edu.dows.profesorSuperO.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class RoleBasedAccessInterceptor implements HandlerInterceptor {
    private static final String ROLE_HEADER = "X-USER-ROLE";
    private static final Set<String> ADMIN_ROLES = Set.of("ADMINISTRATOR");
    private static final Set<String> DECANATURA_ROLES = Set.of("DECANATURA", "ADMINISTRATOR");

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        String requestUri = request.getRequestURI();
        String role = request.getHeader(ROLE_HEADER);

        if (requestUri.startsWith("/administracion") && !ADMIN_ROLES.contains(role)) {
            return deny(response);
        }

        if (requestUri.startsWith("/decanatura") && !DECANATURA_ROLES.contains(role)) {
            return deny(response);
        }

        return true;
    }

    private boolean deny(HttpServletResponse response) throws IOException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write("{\"error\":\"Acceso denegado\",\"mensaje\":\"No tiene permisos para este recurso\"}");
        return false;
    }
}
