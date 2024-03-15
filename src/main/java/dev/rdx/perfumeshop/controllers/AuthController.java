package dev.rdx.perfumeshop.controllers;

import dev.rdx.perfumeshop.models.User;
import dev.rdx.perfumeshop.services.RolesService;
import dev.rdx.perfumeshop.services.UsersService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

import static org.springframework.security.web.context.HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;

@Controller
public class AuthController {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UsersService usersService;
    @Autowired
    private RolesService rolesService;

    @GetMapping("/auth/sign-in")
    public String signIn(ModelMap map) {
        map.put("user", new User());

        return "auth/sign-in";
    }

    @GetMapping("/auth/sign-up")
    public String signUp(ModelMap map) {
        map.put("user", new User());

        return "auth/sign-up";
    }

    @PostMapping("/auth/sign-up")
    public String register(HttpServletRequest req, @ModelAttribute User user) {
        user.setActive(true);
        user.setRoles(List.of(rolesService.findOrSave("ADMIN")));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        this.usersService.save(user);

        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        SecurityContext sc = SecurityContextHolder.getContext();
        sc.setAuthentication(authentication);
        HttpSession session = req.getSession(true);
        session.setAttribute(SPRING_SECURITY_CONTEXT_KEY, sc);

        return "redirect:/dashboard";
    }
}
