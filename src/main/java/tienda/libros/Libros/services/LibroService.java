package tienda.libros.Libros.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import tienda.libros.Libros.models.Libro;
import tienda.libros.Libros.models.modelRequest.LibroRequest;
import tienda.libros.Libros.repository.ILibroRespository;
import java.util.Optional;
import java.util.List;

@Service
@AllArgsConstructor
public class LibroService {

    @Autowired
    private ILibroRespository libroRespository;

    public Optional<Libro> getLibroById(int id){
        return libroRespository.findById(id);
    }    

    public List<Libro> getAllLibro(){
        return libroRespository.findAll();
    }

    public Optional<Libro> saveLibro(LibroRequest libroRequest){


        Libro libro= Libro.builder()
            .nombre(libroRequest.getNombre())
            .genero(libroRequest.getGenero())
            .autor(libroRequest.getAutor())
            .build();

        return Optional.of(libroRespository.save(libro));
    }

    public Optional<Libro> updateLibro(int id, LibroRequest libroRequest){
        Optional<Libro> optionalLibro = libroRespository.findById(id);

        if(optionalLibro.isPresent()){
            Libro libro= optionalLibro.get();
            libro.setNombre(libroRequest.getNombre());
            if(libroRequest.tieneGenero()){
                libro.setGenero(libroRequest.getGenero());
            }

            optionalLibro= Optional.of(libroRespository.save(libro));
        }

        return optionalLibro;
    }

    public Optional<Libro> deleteLibro(int id){
        Optional<Libro> optionalLibro = libroRespository.findById(id);

        if(optionalLibro.isPresent()){
            libroRespository.deleteById(id);
        }

        return optionalLibro;
    }

}
