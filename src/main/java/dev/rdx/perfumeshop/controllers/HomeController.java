package dev.rdx.perfumeshop.controllers;

import dev.rdx.perfumeshop.models.User;
import dev.rdx.perfumeshop.services.PerfumesService;
import dev.rdx.perfumeshop.services.UsersService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import static org.springframework.security.web.context.HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;

@Controller
public class HomeController {
    @Autowired
    private PerfumesService perfumesService;
    @Autowired
    private UsersService usersService;

    @GetMapping("/")
    public String home(HttpServletRequest req, ModelMap map) {
        //TODO:Seulement pour les test
        User user = usersService.byId(2);

        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

        SecurityContext sc = SecurityContextHolder.getContext();
        sc.setAuthentication(authentication);
        HttpSession session = req.getSession(true);
        session.setAttribute(SPRING_SECURITY_CONTEXT_KEY, sc);

        map.put("perfumes", perfumesService.findAll());

        return "index";
    }

    @GetMapping("/perfume/{id}")
    public String perfume(ModelMap map, @AuthenticationPrincipal User user, @PathVariable Integer id) {
        map.put("perfume", perfumesService.byId(id));
        map.put("user", user);

        return "perfume";
    }
}
