package dev.rdx.perfumeshop.repositories;

import dev.rdx.perfumeshop.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImagesRepository extends JpaRepository<Image, Integer> {
}
