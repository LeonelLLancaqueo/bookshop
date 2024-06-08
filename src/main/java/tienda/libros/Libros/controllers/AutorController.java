package tienda.libros.Libros.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import lombok.AllArgsConstructor;
import tienda.libros.Libros.models.Autor;

import tienda.libros.Libros.models.modelRequest.AutorRequest;
import tienda.libros.Libros.services.AutorService;

@RestController
@RequestMapping("/autor")
@AllArgsConstructor
@CrossOrigin(origins = "http://127.0.0.1:5500/")
public class AutorController {


    @Autowired
    AutorService autorService;



    @GetMapping
    public ResponseEntity<List<Autor>> getAll(){
        return  ResponseEntity.ok(autorService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getAutorById(@PathVariable int id){

        Optional<Autor> response= autorService.getAutorById(id);

        if(response.isPresent()){

            return new ResponseEntity<>(response.get(), HttpStatus.OK);
        }

        return  new ResponseEntity<>("No existe un autor con el id " + id, HttpStatus.NOT_FOUND);
    }
    @GetMapping("/{id}/generosUsados")
    public ResponseEntity<Object> getGenerosUsadosPorAutor(@PathVariable int id){

        Optional<Autor> response= autorService.getAutorById(id);
        
        if(response.isPresent()){

            return new ResponseEntity<>(response.get().colGenerosUsados(), HttpStatus.OK);
        }

        return  new ResponseEntity<>("No existe un autor con el id " + id, HttpStatus.NOT_FOUND);
        
        
    }

    @PostMapping
    public ResponseEntity<Object> saveAutor(@RequestBody AutorRequest autorRequest){


        Optional<Autor>optionalAutorAux= autorService.saveAutor(autorRequest);

        if(optionalAutorAux.isPresent()){
              
            return  new ResponseEntity<>(optionalAutorAux.get(), HttpStatus.CREATED);
        }
        
        return new ResponseEntity<>("no se pudo guardar al autor", HttpStatus.NOT_ACCEPTABLE);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateAutor(@PathVariable int id, @RequestBody AutorRequest autorRequest){


        Optional<Autor> optionalAutor= autorService.updateAutor(id, autorRequest);

        if(optionalAutor.isPresent()){
            return  new ResponseEntity<>(optionalAutor.get(), HttpStatus.OK);
        }
        
        return new ResponseEntity<>("no se pudo encontrar al autor", HttpStatus.NOT_FOUND);

    } 

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAutorById(@PathVariable int id){


        Optional<Autor> optionalAutor= autorService.deleteAutor(id);

        if(optionalAutor.isPresent()){
            return  new ResponseEntity<>(optionalAutor.get(), HttpStatus.OK);
        }
        
        return new ResponseEntity<>("no se pudo encontrar al autor", HttpStatus.NOT_FOUND);

    } 

    
}
