package dev.rdx.perfumeshop.repositories;

import dev.rdx.perfumeshop.models.Perfume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PerfumesRepository extends JpaRepository<Perfume, Integer> {
    List<Perfume> findAllByDeletedAtIsNull();
}
