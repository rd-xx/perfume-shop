package dev.rdx.perfumeshop.controllers;

import dev.rdx.perfumeshop.models.Order;
import dev.rdx.perfumeshop.models.Perfume;
import dev.rdx.perfumeshop.models.User;
import dev.rdx.perfumeshop.services.OrdersService;
import dev.rdx.perfumeshop.services.PerfumesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class OrderController {
    @Autowired
    private PerfumesService perfumesService;
    @Autowired
    private OrdersService ordersService;

    @GetMapping("/order/{perfumeId}")
    public String order(@PathVariable Integer perfumeId, @AuthenticationPrincipal User user) {
        if (user == null) {
            return "redirect:/auth/sign-in";
        }

        Perfume perfume = perfumesService.byId(perfumeId);

        if (perfume == null) {
            return "redirect:/";
        }

        Order order = new Order();
        order.setStatus("Pending");
        order.setPerfume(perfume);
        order.setUser(user);

        ordersService.save(order);

        return "redirect:/perfume/" + perfumeId;
    }
}
