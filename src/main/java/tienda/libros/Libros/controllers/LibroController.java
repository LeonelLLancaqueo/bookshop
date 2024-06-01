package tienda.libros.Libros.controllers;


import java.util.Optional;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;



import lombok.AllArgsConstructor;
import tienda.libros.Libros.models.Libro;
import tienda.libros.Libros.models.modelRequest.LibroRequest;
import tienda.libros.Libros.services.LibroService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/libro")
@AllArgsConstructor
public class LibroController {
    
    @Autowired
    private LibroService libroService;

    @GetMapping("/{id}")
    public ResponseEntity<Object> getLibroById(@PathVariable int id){

        Optional<Libro> optionalLibro= libroService.getLibroById(id);

        if(optionalLibro.isPresent()){
            return new ResponseEntity<>(optionalLibro.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>("No se encontro un libro con el id ingresado", HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<List<Libro>> getAllLibros(){
        return new ResponseEntity<>(libroService.getAllLibro(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> saveLibro(@RequestBody LibroRequest libroRequest){

        Optional<Libro> optionalLibro= libroService.saveLibro(libroRequest);
    
        if(optionalLibro.isPresent()){
            return new ResponseEntity<>(optionalLibro.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>("No se pudo almacena el libro", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateLibro(@PathVariable int id, @RequestBody  LibroRequest libroRequest) {
        
        Optional<Libro> optionalLibro= libroService.updateLibro(id, libroRequest);
    
        if(optionalLibro.isPresent()){
            return new ResponseEntity<>(optionalLibro.get(), HttpStatus.ACCEPTED);
        }

        return new ResponseEntity<>("No se pudo encontrar el libro", HttpStatus.NOT_FOUND);
        
        
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteLibro(@PathVariable int id) {
        
        Optional<Libro> optionalLibro= libroService.deleteLibro(id);
    
        if(optionalLibro.isPresent()){
            return new ResponseEntity<>(optionalLibro.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>("No se pudo encontrar el libro", HttpStatus.NOT_FOUND);
        
        
    }

}
