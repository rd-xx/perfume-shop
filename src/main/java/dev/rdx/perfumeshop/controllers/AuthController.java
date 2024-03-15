package dev.rdx.perfumeshop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {
    @GetMapping("/auth/sign-in")
    public String signIn() {
        return "auth/sign-in";
    }

    @GetMapping("/auth/sign-up")
    public String signUp() {
        return "auth/sign-up";
    }
}
