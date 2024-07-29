package tienda.libros.Libros.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import tienda.libros.Libros.models.Libro;
import tienda.libros.Libros.models.dtos.LibroDto;
import tienda.libros.Libros.models.modelRequest.LibroRequest;
import tienda.libros.Libros.repository.ILibroRespository;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

import java.util.List;

@Service
@AllArgsConstructor
public class LibroService {

    @Autowired
    private ILibroRespository libroRespository;

    public Optional<Libro> getLibroById(int id){
        return libroRespository.findById(id);
    }    

    public List<LibroDto> getAllLibro(){
        List<LibroDto> librosDto= libroRespository.getLibrosDto().stream()
            .map(d -> convertToLibroDto(d))
            .collect(toList());
        return librosDto;
    }   

    public Optional<Libro> saveLibro(LibroRequest libroRequest){


        Libro libro= Libro.builder()
            .nombre(libroRequest.getNombre())
            .genero(libroRequest.getGenero())
            .autor(libroRequest.getAutor())
            .url(libroRequest.getUrl())
            .descripcion(libroRequest.getDescripcion())
            .precio(libroRequest.getPrecio())
            .unidadesDisponibles(libroRequest.getUnidadesDisponibles())
            .unidadesEnTotal(libroRequest.getUnidadesEnTotal())
            .unidadesVendidas(libroRequest.getUnidadesVendidas())
            .build();

        return Optional.of(libroRespository.save(libro));
    }

    public Optional<Libro> updateLibro(int id, LibroRequest libroRequest){
        Optional<Libro> optionalLibro = libroRespository.findById(id);

        if(optionalLibro.isPresent()){
            Libro libro= optionalLibro.get();
            libro.setNombre(libroRequest.getNombre());
            libro.setDescripcion(libroRequest.getDescripcion());
            libro.setUrl(libroRequest.getUrl());
            libro.setPrecio(libroRequest.getPrecio());
            libro.setUnidadesDisponibles(libroRequest.getUnidadesDisponibles());
            libro.setUnidadesEnTotal(libroRequest.getUnidadesEnTotal());
            libro.setUnidadesVendidas(libroRequest.getUnidadesVendidas());
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

    public List<LibroDto> getLibrosByIdAutor(int idAutor){
        
        List<LibroDto> librosDto= libroRespository.getLibrosByIdAutor(idAutor).stream()
            .map(d -> convertToLibroDto(d))
            .collect(toList());
        return librosDto;
    }


    //otros metodos
  


    private LibroDto convertToLibroDto(Object [] d){
        return new LibroDto((int)d[0], (String)d[1], (String)d[2], (String)d[3], (double)d[4], (int)d[5], (int)d[6], (int)d[7], (String)d[8], (String)d[9]);
    }
    
    public Optional<LibroDto> updateLibroConGenero(int id, LibroDto libroDto){

        Optional<LibroDto> libroDtoResponse = Optional.empty();
        //actualizo el libro
        libroRespository.UpdateLibroConGenero
        (id, libroDto.getGenero(), libroDto.getNombre(), libroDto.getUrl(), libroDto.getDescripcion(), libroDto.getPrecio(), libroDto.getUnidadesEnTotal(), libroDto.getUnidadesDisponibles(), libroDto.getUnidadesVendidas());
        //recupero el libro
        Optional<Object[]> optionalLibro = libroRespository.getLibroDtoById(id);
        
        if(optionalLibro.isPresent()){    
            libroDtoResponse= Optional.of(convertToLibroDto(optionalLibro.get()));
        }

        return libroDtoResponse ;
    }

    public List<LibroDto> getLibrosFilter(int precioMin, int precioMax, int cantDispMin, int cantDispMax, int cantVendMin, int cantVendMax, int cantUnidMin, int cantUnidMax){
        
        List<LibroDto> librosDto= libroRespository.getFilterLibrosByOptions(precioMin, precioMax, cantDispMin, cantDispMax, cantVendMin, cantVendMax, cantUnidMin,  cantUnidMax).stream()
            .map(d -> convertToLibroDto(d))
            .collect(toList());
        return librosDto;
    }
    public List<LibroDto> getLibrosFilterAndGenero(int precioMin, int precioMax, int cantDispMin, int cantDispMax, int cantVendMin, int cantVendMax, int cantUnidMin, int cantUnidMax, int idGenero){
        
        List<LibroDto> librosDto= libroRespository.getFilterLibrosByOptionsAndGenero(precioMin, precioMax, cantDispMin, cantDispMax, cantVendMin, cantVendMax, cantUnidMin,  cantUnidMax, idGenero).stream()
            .map(d -> convertToLibroDto(d))
            .collect(toList());
        return librosDto;
    }
}
