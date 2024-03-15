package dev.rdx.perfumeshop.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String status;
    
    @ManyToOne
    private User user;

    @ManyToOne
    private Perfume perfume;
}