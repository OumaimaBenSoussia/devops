package com.example.gestionprouit.service;

import com.example.gestionprouit.entity.Categorie;
import com.example.gestionprouit.entity.Produit;

import java.util.List;

public interface IServiceCategorie {
    public Categorie saveCategorie (Categorie categorie);
    public List<Categorie> getAllCategorie();
    public void deleteCategorie(Long id);


}
