package dev.rdx.perfumeshop.services;

import dev.rdx.perfumeshop.models.Image;
import dev.rdx.perfumeshop.repositories.ImagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImagesService {
    @Autowired
    private ImagesRepository repository;

    public Image save(Image photos) {
        return repository.save(photos);
    }

    public Image byId(Integer id) {
        return repository.findById(id).get();
    }
}
