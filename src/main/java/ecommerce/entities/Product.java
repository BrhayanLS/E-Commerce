package ecommerce.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @DecimalMin(value = "0.1")
    private Double price;

    @NotBlank
    @Column(nullable = false)
    private String description;

    @NotBlank
    @Column(nullable = false)
    private String category;

    @NotBlank
    @Column(nullable = false)
    private String image;
}
