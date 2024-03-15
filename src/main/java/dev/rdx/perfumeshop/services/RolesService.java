package dev.rdx.perfumeshop.services;

import dev.rdx.perfumeshop.models.Role;
import dev.rdx.perfumeshop.repositories.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolesService {
    @Autowired
    private RolesRepository repository;

    public Role findOrSave(String name) {
        Role role = repository.findByNameIgnoreCase(name);

        if (role != null) {
            return role;
        }

        role = new Role();
        role.setName(name);

        return repository.save(role);
    }
}
