package com.example.gestionprouit.APIRest;

import com.example.gestionprouit.entity.Produit;
import com.example.gestionprouit.service.IServiceProduit;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@CrossOrigin("*")
@RestController
@AllArgsConstructor
//@RequestMapping://Pour faire destinction
public class ProduitRestController {
    IServiceProduit serviceProduit;
    @GetMapping("/all")
    public List<Produit> getAllProduct(@RequestParam(name="page", defaultValue = "1") int page,
                                       @RequestParam (name="size", defaultValue = "5") int size,
                                       @RequestParam (name="mc", defaultValue = "")String mc){
        Page<Produit> list=serviceProduit.getProductsByMC(mc, PageRequest.of(page,size));
        return list.getContent();

    }
    @DeleteMapping("/deleteApi/{id}")
    public void deleteProduct(@PathVariable("id") Long idProduit)
    {
       serviceProduit.deleteProduct(idProduit);
    }

    @PostMapping("/add")
    public void saveProduit(@RequestParam("produit") String p, @RequestParam("fichier")MultipartFile mf) throws IOException {
        Produit produit = new ObjectMapper().readValue(p, Produit.class);
        serviceProduit.saveProduct(produit, mf);

    }
    @GetMapping(path="/image/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getImage(@PathVariable Long id) throws IOException{
        return serviceProduit.getImage(id);
    }
}
