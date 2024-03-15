package dev.rdx.perfumeshop.controllers.dashboard;

import dev.rdx.perfumeshop.models.Image;
import dev.rdx.perfumeshop.models.Perfume;
import dev.rdx.perfumeshop.services.ImagesService;
import dev.rdx.perfumeshop.services.PerfumesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ImageController {
    @Autowired
    private ImagesService imagesService;
    @Autowired
    private PerfumesService perfumesService;

    @PostMapping("/api/images/new")
    public Image createImage(@ModelAttribute Image images) {
        return imagesService.save(images);
    }

    @PostMapping("/api/images/perfume/link")
    public List<Image> linkImage(@RequestParam Integer perfumeId, @RequestParam Integer imageId) {
        Perfume perfume = perfumesService.byId(perfumeId);
        perfume.getImages().add(imagesService.byId(imageId));
        perfumesService.save(perfume);

        return perfume.getImages();
    }

    @GetMapping("/api/perfumes/{id}/images")
    public List<Image> allPerfumeImages(@PathVariable Integer id) {
        return perfumesService.byId(id).getImages();
    }
}
