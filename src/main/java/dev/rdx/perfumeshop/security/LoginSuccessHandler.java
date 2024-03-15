package dev.rdx.perfumeshop.security;

import dev.rdx.perfumeshop.models.Role;
import dev.rdx.perfumeshop.models.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        User user = (User) authentication.getPrincipal();
        String redirectUrl;

        for (Role role : user.getRoles()) {
            redirectUrl = switch (role.getName()) {
                case "ADMIN" -> "/dashboard/perfumes";
                case "USER" -> "/";
                default -> "/auth/sign-in";
            };

            response.sendRedirect(redirectUrl);

            return;
        }
    }
}
