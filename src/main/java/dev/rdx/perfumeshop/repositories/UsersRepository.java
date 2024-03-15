package dev.rdx.perfumeshop.repositories;

import dev.rdx.perfumeshop.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<User, Integer> {
    User findByEmailLikeIgnoreCase(String email);
}
