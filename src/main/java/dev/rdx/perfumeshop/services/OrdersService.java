package dev.rdx.perfumeshop.services;

import dev.rdx.perfumeshop.models.Order;
import dev.rdx.perfumeshop.repositories.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrdersService {
    @Autowired
    private OrdersRepository repository;

    public Order save(Order photos) {
        return repository.save(photos);
    }

}
