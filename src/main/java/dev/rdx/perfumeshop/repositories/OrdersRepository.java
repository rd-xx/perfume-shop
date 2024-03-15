package dev.rdx.perfumeshop.repositories;

import dev.rdx.perfumeshop.models.Order;
import dev.rdx.perfumeshop.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Order, Integer> {
    List<Order> findByUser(User user);
}
