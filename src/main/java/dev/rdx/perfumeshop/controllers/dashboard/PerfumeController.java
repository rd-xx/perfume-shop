package dev.rdx.perfumeshop.controllers.dashboard;

import dev.rdx.perfumeshop.models.Perfume;
import dev.rdx.perfumeshop.services.PerfumesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PerfumeController {
    @Autowired
    private PerfumesService perfumesService;

    @GetMapping("/dashboard/perfumes")
    public String index(ModelMap map) {
        map.put("perfumes", perfumesService.findAll());

        return "dashboard/perfumes/index";
    }

    @GetMapping("/dashboard/perfumes/new")
    public String newPerfume(ModelMap map) {
        map.put("perfume", new Perfume());

        return "dashboard/perfumes/mutate";
    }

    @PostMapping("/dashboard/perfumes/mutate")
    public String mutatePerfume(Perfume perfume) {
        perfumesService.save(perfume);

        return "redirect:/dashboard/perfumes";
    }

    @GetMapping("/dashboard/perfumes/{id}/update")
    public String updatePerfume(ModelMap map, @PathVariable Integer id) {
        map.put("perfume", perfumesService.byId(id));

        return "dashboard/perfumes/mutate";
    }
}
