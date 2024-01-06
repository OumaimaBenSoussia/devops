package com.example.gestionprouit.controller;

import com.example.gestionprouit.dao.CategorieRepository;
import com.example.gestionprouit.entity.Produit;
import com.example.gestionprouit.service.IServiceCategorie;
import com.example.gestionprouit.service.IServiceProduit;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@Controller
@AllArgsConstructor
public class ProduitController {
    private IServiceProduit serviceProduit;
    private IServiceCategorie serviceCategorie;
    private CategorieRepository categorieRepository;

    @GetMapping("/user/index")
    public String getAllProducts(Model m, @RequestParam(name="page", defaultValue = "1") int page,
                                 @RequestParam (name="size", defaultValue = "5") int size,
                                 @RequestParam (name="mc", defaultValue = "")String mc){
        Page<Produit> list=serviceProduit.getProductsByMC(mc, PageRequest.of(page-1,size));
        m.addAttribute("data", list.getContent());
        m.addAttribute("pages", new int[list.getTotalPages()]);
        m.addAttribute("current", list.getNumber());
        m.addAttribute("mc", mc);
        m.addAttribute("products",list);
        return "index";
    }
    @GetMapping("/admin/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long idProduit)
    {
        serviceProduit.deleteProduct(idProduit);
        return "redirect:/user/index";
    }
    @GetMapping("/admin/create")
    public String formAjout(Model m)
    {m.addAttribute("categories",categorieRepository.findAll());
        m.addAttribute("produit",new Produit());
        return "create";
    }

    @PostMapping("/admin/save")
    public String saveProduct(@Valid Produit p, BindingResult bindingResult,  Model m, @RequestParam("file") MultipartFile mf) throws IOException {
        if (bindingResult.hasErrors()) {
            m.addAttribute("categories", serviceCategorie.getAllCategorie());
            return "create";
        }

        {
            serviceProduit.saveProduct(p,mf);
            return "redirect:/user/index";
        }
    }

    @GetMapping("/admin/update/{id}")
    public String editProduct(@PathVariable  ("id") Long idProduit, Model m){
        m.addAttribute("produit",serviceProduit.getProduct(idProduit));
        m.addAttribute("categories",serviceCategorie.getAllCategorie());
        return "update";
    }
    @PostMapping("/admin/update")
    public String updateProduct(@ModelAttribute Produit updatedProduct, RedirectAttributes redirectAttributes) {
        serviceProduit.updateProduct(updatedProduct);
        redirectAttributes.addFlashAttribute("successMessage", "Product updated successfully");
        return "redirect:/user/index";
    }
    @GetMapping("/")
    public String home(){
        return "redirect:/user/index";
    }



    }
