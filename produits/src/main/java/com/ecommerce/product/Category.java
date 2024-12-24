package com.ecommerce.product;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@Entity
@NoArgsConstructor
@Builder
public class Category {
    @Id
    @GeneratedValue
    private int Id;
    private String name;
    private String description;

    @OneToMany(mappedBy = "category" , cascade = CascadeType.REMOVE)
    private List<Product> products;
}
