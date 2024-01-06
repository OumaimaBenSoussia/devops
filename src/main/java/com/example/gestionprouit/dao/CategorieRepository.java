package com.example.gestionprouit.dao;

import com.example.gestionprouit.entity.Categorie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategorieRepository extends JpaRepository<Categorie,Long> {
}
