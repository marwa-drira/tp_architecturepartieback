package soa.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import soa.entities.Categorie;
import soa.repository.CategorieRepository;

@RestController // pour déclarer un service web de type REST
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/categories")  //    http://localhost:8080/Categories
public class CategorieRESTController {
    @Autowired // pour l'injection de dépendances
    private CategorieRepository CategorieRepos;

    //  Message d'accueil
    //  http://localhost:8080/Categories/index  (GET)
    @GetMapping(value ="/index" )
    public String accueil() {
        return "BienVenue au service Web REST 'Categories'.....";
    }

    //  Afficher la liste des Categories
    //  http://localhost:8080/Categories/ (GET)

    @GetMapping(
            // spécifier le path de la méthode
            value= "/",
            // spécifier le format de retour en XML
            produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }
    )
    public  List<Categorie> getAllCategories() {
        return CategorieRepos.findAll();
    }

    //  Afficher un Categorie en spécifiant son 'id'
    //  http://localhost:8080/Categories/{id} (GET)
    @GetMapping(
            // spécifier le path de la méthode qui englobe un paramètre
            value= "/{id}" ,
            // spécifier le format de retour en XML
            produces = { MediaType.APPLICATION_JSON_VALUE }
    )
    public Categorie getCategorie(@PathVariable Long id) {
        Categorie p =CategorieRepos.findById(id).get();
        return p;
    }

    // Supprimer un Categorie par 'id' avec la méthode 'GET'
    //  http://localhost:8080/Categories/delete/{id}  (GET)
    @GetMapping(
            // spécifier le path de la méthode
            value = "/delete/{id}")
    public void deleteCategorie(@PathVariable Long id)
    {
        CategorieRepos.deleteById(id);
    }

    //  ajouter un Categorie avec la méthode "POST"
    //  http://localhost:8080/Categories/   (POST)
    @PostMapping(
            // spécifier le path de la méthode
            value = "/"  ,
            //spécifier le format de retour
            produces = { MediaType.APPLICATION_JSON_VALUE }
    )
    public Categorie saveCategorie(@RequestBody Categorie p)
    {
        return CategorieRepos.save(p);
    }

    //  modifier un Categorie avec la méthode "PUT"
    //  http://localhost:8080/Categories/   (PUT)
    @PutMapping(
            // spécifier le path de la méthode
            value = "/"  ,
            //spécifier le format de retour
            produces = { MediaType.APPLICATION_JSON_VALUE  }
    )
    public Categorie updateCategorie(@RequestBody Categorie p)
    {
        return CategorieRepos.save(p);
    }

    // Supprimer un Categorie  avec la méthode 'DELETE'
    //  http://localhost:8080/Categories/   (DELETE)
    @DeleteMapping(
            // spécifier le path de la méthode
            value = "/")
    public void deleteCategorie(@RequestBody Categorie p)
    {
        CategorieRepos.delete(p);
    }

}
