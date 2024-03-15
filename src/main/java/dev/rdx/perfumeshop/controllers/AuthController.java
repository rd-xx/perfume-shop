package dev.rdx.perfumeshop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller("/auth")
public class AuthController {
    @GetMapping("/sign-in")
    public String signIn() {
        return "auth/sign-in";
    }

    @GetMapping("/sign-up")
    public String signUp() {
        return "auth/sign-up";
    }
}
