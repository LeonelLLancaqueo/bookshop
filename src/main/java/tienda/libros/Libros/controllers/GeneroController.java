package tienda.libros.Libros.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import tienda.libros.Libros.models.Genero;
import tienda.libros.Libros.models.modelRequest.GeneroRequest;
import tienda.libros.Libros.services.GeneroService;

@RestController
@RequestMapping("/genero")
public class GeneroController {

    @Autowired
    private GeneroService generoService;

    @GetMapping("/{id}")
    public ResponseEntity<Object> getGeneroById(@PathVariable int id){

        Optional<Genero> optionalGenero= generoService.getGeneroById(id);

        if(optionalGenero.isPresent()){
            return new ResponseEntity<>(optionalGenero.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>("No se encontro un genero con el id ingresado", HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<List<Genero>> getAllGeneros(){
        return new ResponseEntity<>(generoService.getAllGenero(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> saveGenero(@RequestBody GeneroRequest generoRequest){

        Optional<Genero> optionalGenero= generoService.saveGenero(generoRequest);
    
        if(optionalGenero.isPresent()){
            return new ResponseEntity<>(optionalGenero.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>("No se pudo almacena el genero", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateGenero(@PathVariable int id, @RequestBody  GeneroRequest generoRequest) {
        
        Optional<Genero> optionalGenero= generoService.updateGeneroById(id, generoRequest);
    
        if(optionalGenero.isPresent()){
            return new ResponseEntity<>(optionalGenero.get(), HttpStatus.ACCEPTED);
        }

        return new ResponseEntity<>("No se pudo encontrar el genero", HttpStatus.NOT_FOUND);
        
        
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteGenero(@PathVariable int id) {
        
        Optional<Genero> optionalGenero= generoService.deleteGeneroById(id);
    
        if(optionalGenero.isPresent()){
            return new ResponseEntity<>(optionalGenero.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>("No se pudo encontrar el genero", HttpStatus.NOT_FOUND);
        
        
    }

    

}
