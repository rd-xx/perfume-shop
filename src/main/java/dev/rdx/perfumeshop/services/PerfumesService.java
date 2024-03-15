package dev.rdx.perfumeshop.services;

import dev.rdx.perfumeshop.models.Perfume;
import dev.rdx.perfumeshop.repositories.PerfumesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PerfumesService {
    @Autowired
    private PerfumesRepository repository;

    public Perfume save(Perfume users) {
        return repository.save(users);
    }

    public List<Perfume> findAll() {
        return repository.findAllByDeletedAtIsNull();
    }

    public Perfume byId(Integer id) {
        return repository.findById(id).get();
    }

    public void delete(Integer id) {
        Perfume perfume = byId(id);
        perfume.setDeletedAt(new Date());

        save(perfume);
    }
}
