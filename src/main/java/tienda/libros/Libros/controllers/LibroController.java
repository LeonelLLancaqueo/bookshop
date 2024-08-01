package tienda.libros.Libros.controllers;


import java.util.Optional;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



import lombok.AllArgsConstructor;
import tienda.libros.Libros.models.Libro;
import tienda.libros.Libros.models.dtos.LibroDto;
import tienda.libros.Libros.models.modelRequest.LibroRequest;
import tienda.libros.Libros.services.LibroService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/libro")
@AllArgsConstructor
@CrossOrigin(origins = {"http://127.0.0.1:5500/", "https://librosstaticweb.onrender.com/"})
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
    public ResponseEntity<List<LibroDto>> getAllLibros(){
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
    //otras consultas 

    @GetMapping("/porAutor/{id}")
    public ResponseEntity<List<LibroDto>> getLibroByIdAutor(@PathVariable int id) {
        
        List<LibroDto> listLibros= libroService.getLibrosByIdAutor(id);
    
        
        return new ResponseEntity<>(listLibros, HttpStatus.OK);
        
        
    }

    @PutMapping("/conGenero/{id}")
    public ResponseEntity<Object> updateLibroConGenero(@PathVariable int id, @RequestBody  LibroDto libroDto) {
        
        System.out.println(libroDto.toString());
        
        Optional<LibroDto> optionalLibro= libroService.updateLibroConGenero(id, libroDto);
    
        
    
        if(optionalLibro.isPresent()){
            return new ResponseEntity<>(optionalLibro.get(), HttpStatus.ACCEPTED);
        }

        return new ResponseEntity<>("No se pudo encontrar el libro", HttpStatus.NOT_FOUND);
        
    }


    @PostMapping("/filterByOptions")
    public ResponseEntity<Object> getFilterLibrosByOptions(@RequestBody Map<String,Integer>params){

        List<LibroDto> listLibroDtos= libroService.getLibrosFilter(params.get("precioMin").intValue(), params.get("precioMax").intValue(), params.get("cantDispMin").intValue(), params.get("cantDispMax").intValue(), params.get("cantVendMin").intValue(), params.get("cantVendMax").intValue(), params.get("cantUnidMin").intValue(), params.get("cantUnidMax").intValue());
    
        return new ResponseEntity<>(listLibroDtos, HttpStatus.OK);

        //return new ResponseEntity<>("No se pudo almacena el libro", HttpStatus.NOT_FOUND);
    }

    @PostMapping("/filterByOptionsAndGenero")
    public ResponseEntity<Object> getFilterLibrosByOptionsAndGenero(@RequestBody Map<String,Integer>params){

        List<LibroDto> listLibroDtos= libroService.getLibrosFilterAndGenero(params.get("precioMin").intValue(), params.get("precioMax").intValue(), params.get("cantDispMin").intValue(), params.get("cantDispMax").intValue(), params.get("cantVendMin").intValue(), params.get("cantVendMax").intValue(), params.get("cantUnidMin").intValue(), params.get("cantUnidMax").intValue(), params.get("genero").intValue());
    
        return new ResponseEntity<>(listLibroDtos, HttpStatus.OK);

        //return new ResponseEntity<>("No se pudo almacena el libro", HttpStatus.NOT_FOUND);
    }
}
