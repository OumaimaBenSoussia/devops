package com.example.gestionprouit.service;

import com.example.gestionprouit.entity.Produit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IServiceProduit {
    public Produit saveProduct (Produit p, MultipartFile mf) throws IOException;
    public List<Produit> getAllProducts();


    Page<Produit> getProductsByMC(String mc, Pageable p);

    public List<Produit> getProductsBCat(Long idCat);
    public void deleteProduct(Long id);
    public Produit getProduct(Long id);

    byte[] getImage(Long id) throws IOException;

    @Transactional
    Produit updateProduct(Produit p);
}
