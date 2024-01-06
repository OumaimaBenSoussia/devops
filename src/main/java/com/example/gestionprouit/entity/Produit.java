package com.example.gestionprouit.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import jakarta.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Produit {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    @Size (min=1,max=20)
    private String nom;
    @Min(1)
    private double prix;
    @Min(0)
    private int quantite;

    @ManyToOne
    @NotNull(message="la categorie ne doit pas être null")
    private Categorie categorie;
    private String photo;






}
