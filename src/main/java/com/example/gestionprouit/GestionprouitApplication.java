package com.example.gestionprouit;


import com.example.gestionprouit.dao.CategorieRepository;
import com.example.gestionprouit.dao.ProduitRepository;
import com.example.gestionprouit.entity.Categorie;
import com.example.gestionprouit.entity.Produit;
import com.example.gestionprouit.security.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class GestionprouitApplication {
    //implements CommandLineRunner
//    @Autowired
//    private ProduitRepository produitRepository;
//    @Autowired
//    private CategorieRepository categorieRepository;

    public static void main(String[] args) {
        SpringApplication.run(GestionprouitApplication.class, args);
    }

    /*
    @Override
    public void run(String... args) throws Exception {
        categorieRepository.save(new Categorie(null,"informatique",null));
        categorieRepository.save(new Categorie(null,"electronique",null));
        categorieRepository.save(Categorie.builder().nom("mecanique").build());
        produitRepository.save(new Produit(null,"pc portable",2000,5,categorieRepository.findById(1L).get()));
    }
*/
  //  @Bean
    //CommandLineRunner commandLineRunner(ProduitRepository produitRepository,CategorieRepository categorieRepository) {

      //  return  args -> {
            //categorieRepository.save(Categorie.builder().nom("informatique").build());
            /**  categorieRepository.save(new Categorie(null,"electronique",null));
            categorieRepository.save(Categorie.builder().nom("mecanique").build());
            produitRepository.save(new Produit(null,"pc portable",2000,5,categorieRepository.findById(1L).get()));
            produitRepository.save(new Produit(null,"pc portable1",2500,6,categorieRepository.findById(2L).get()));
            produitRepository.save(new Produit(null,"play",2500,6,categorieRepository.findById(2L).get()));**/
            //produitRepository.save(new Produit(null,"jeux video",2500,6,categorieRepository.findById(1L).get()));

            //  produitRepository.rechercherProdParCat(2L).forEach(e->System.out.println(e.getNom()+""+e.getPrix()));

     //   };
  //  }
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    //@Bean
    CommandLineRunner commandLineRunner(IAccountService accountService) {
        return args -> {
            accountService.addRole("USER");
            accountService.addRole("ADMIN");
            accountService.addUser("user","1234","user@gmail.com");
            accountService.addUser("admin","1234","admin@gmail.com");
            accountService.addRoleToUser("user","USER");
            accountService.addRoleToUser("admin","ADMIN");
            accountService.addRoleToUser("admin","USER");



        };
    }
}
