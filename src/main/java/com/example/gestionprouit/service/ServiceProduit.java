package com.example.gestionprouit.service;

import com.example.gestionprouit.dao.ProduitRepository;
import com.example.gestionprouit.entity.Produit;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ServiceProduit implements IServiceProduit{
    private ProduitRepository produitRepository;

    @Override
    @Transactional
    public Produit saveProduct(Produit p,MultipartFile mf) throws IOException {

        if(!mf.isEmpty())
            p.setPhoto(saveImage(mf));

        return produitRepository.save(p);
    }

    @Override
    public List<Produit> getAllProducts() {
        return produitRepository.findAll();
    }




    @Override
    public Page<Produit> getProductsByMC(String mc, Pageable p) {
        return produitRepository.findByNomContains(mc, p);
    }

    @Override
    public List<Produit> getProductsBCat(Long idCat) {
        return produitRepository.rechercherProdParCat(idCat);
    }

    @Override
    public void deleteProduct(Long id) {
        produitRepository.deleteById(id);

    }

    @Override
    public Produit getProduct(Long id) {
        return produitRepository.findById(id).orElse(null);
    }


    @Override
    public byte[] getImage(Long id) throws IOException{
        File f=new ClassPathResource("static/photos").getFile();
        String chemin=f.getAbsolutePath();
        Path p=Paths.get(chemin,getProduct(id).getPhoto());
        return Files.readAllBytes(p);
    }
   private String saveImage(MultipartFile mf) throws IOException {
        String nomPhoto=mf.getOriginalFilename();
        String tab[]=nomPhoto.split("\\."); //extension
       String newName=tab[0]+System.currentTimeMillis()+"."+tab[1];

       File file=new ClassPathResource("static/photos").getFile();
       String chemin=file.getAbsolutePath();

       Path p= Paths.get(chemin, newName);
       Files.write(p,mf.getBytes());//write tekhou 2 parametres path et mf
       return newName;
   }
    @Override
    @Transactional
    public Produit updateProduct(Produit p) {
        // Ensure that the id is set on the Produit object
        if (p.getId() == null) {
            // Handle the case where the id is not set
            throw new IllegalArgumentException("Product id must be set for update");
        }

        // Check if the product with the given id exists in the database
        Optional<Produit> existingProduct = produitRepository.findById(p.getId());

        if (existingProduct.isPresent()) {
            // If the product exists, update its fields and save
            Produit updatedProduct = existingProduct.get();
            updatedProduct.setNom(p.getNom());
            updatedProduct.setPrix(p.getPrix());
            updatedProduct.setQuantite(p.getQuantite());
            updatedProduct.setCategorie(p.getCategorie());

            return produitRepository.save(updatedProduct);
        } else {
            // Handle the case where the product with the given id doesn't exist
            throw new IllegalArgumentException("Product with id " + p.getId() + " not found");
        }
    }
}
