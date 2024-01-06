package com.example.gestionprouit.dao;

import com.example.gestionprouit.entity.Produit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProduitRepository extends JpaRepository<Produit,Long> {
    Optional<Produit> findById(Long id);
    //derived query
    public Page<Produit> findByNomContains(String mc, Pageable p);
    //jpql or hql query
    @Query("select p from Produit p where p.categorie.id=:x")
    public List<Produit> rechercherProdParCat(@Param("x") Long idC);

}
