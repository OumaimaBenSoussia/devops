package com.example.gestionprouit.service;

import com.example.gestionprouit.dao.CategorieRepository;
import com.example.gestionprouit.entity.Categorie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

public class ServiceCategorie implements IServiceCategorie{
    private CategorieRepository categorieRepository;
    @Autowired
    public ServiceCategorie(CategorieRepository categorieRepository) {
        this.categorieRepository = categorieRepository;
    }

    @Override
    public Categorie saveCategorie(Categorie categorie) {
        return categorieRepository.save(categorie);
    }

    @Override
    public List<Categorie> getAllCategorie() {
        return categorieRepository.findAll();
    }

    @Override
    public void deleteCategorie(Long id) {
        categorieRepository.deleteById(id);

    }
}
