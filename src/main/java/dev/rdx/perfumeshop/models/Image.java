package dev.rdx.perfumeshop.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String url;
}
