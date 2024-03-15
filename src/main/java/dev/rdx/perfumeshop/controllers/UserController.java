package dev.rdx.perfumeshop.controllers;

import dev.rdx.perfumeshop.models.Order;
import dev.rdx.perfumeshop.models.User;
import dev.rdx.perfumeshop.services.OrdersService;
import dev.rdx.perfumeshop.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UsersService usersService;
    @Autowired
    private OrdersService ordersService;

    @GetMapping("/profile/@me")
    public String profile(@AuthenticationPrincipal User user, ModelMap map) {
        if (user == null) {
            return "redirect:/auth/sign-in";
        }

        List<Order> orders = ordersService.getByUser(user);

        map.put("user", user);
        map.put("orders", orders);

        return "profile";
    }

    @PostMapping("/profile/@me")
    public String mutateProfile(@AuthenticationPrincipal User user, @ModelAttribute User newUser) {
        if (user == null) {
            return "redirect:/auth/sign-in";
        }

        if (newUser.getFirstName() != null) {
            user.setFirstName(newUser.getFirstName());
        }

        if (newUser.getLastName() != null) {
            user.setLastName(newUser.getLastName());
        }

        if (newUser.getEmail() != null) {
            user.setEmail(newUser.getEmail());
        }

        if (newUser.getAddress() != null) {
            user.setAddress(newUser.getAddress());
        }

        if (newUser.getPhone() != null) {
            user.setPhone(newUser.getPhone());
        }

        usersService.save(user);

        return "redirect:/profile/@me";
    }
}
