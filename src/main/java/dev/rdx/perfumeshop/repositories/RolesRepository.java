package dev.rdx.perfumeshop.repositories;

import dev.rdx.perfumeshop.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends JpaRepository<Role, Integer> {
    Role findByNameIgnoreCase(String nom);
}
