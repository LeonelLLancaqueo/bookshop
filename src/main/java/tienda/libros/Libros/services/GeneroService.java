package tienda.libros.Libros.services;

import java.util.Optional;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import tienda.libros.Libros.models.Genero;
import tienda.libros.Libros.models.Libro;
import tienda.libros.Libros.models.modelRequest.GeneroRequest;
import tienda.libros.Libros.repository.IGeneroRepository;

@Service
@AllArgsConstructor
public class GeneroService {


    @Autowired
    private IGeneroRepository generoRepository;

    public Optional<Genero> getGeneroById(int id){
        return  generoRepository.findById(id);
    } 

    public List<Genero> getAllGenero(){
        return generoRepository.findAll();
    }

    public Optional<Genero> saveGenero(GeneroRequest generoRequest){
        


        Genero otroGenero= Genero.builder()
            .nombre(generoRequest.getNombre())
            .descripcion(generoRequest.getDescripcion())
            .colLibro(new ArrayList<Libro>() )
            .build();
        
        return Optional.of(generoRepository.save(otroGenero)) ;
        
    }

    public Optional<Genero> updateGeneroById(int id, GeneroRequest generoRequest){
        Optional<Genero> optionalGenero= generoRepository.findById(id);


        if(optionalGenero.isPresent()){
            Genero generoAux= optionalGenero.get();

            generoAux.setDescripcion(generoRequest.getDescripcion());
            generoAux.setNombre(generoRequest.getNombre());
            
            optionalGenero= Optional.of(generoRepository.save(generoAux));
        }

        return optionalGenero;
    }

    public Optional<Genero> deleteGeneroById(int id){
        Optional<Genero> optionalGenero= generoRepository.findById(id);


        if(optionalGenero.isPresent()){
            generoRepository.deleteById(id);
        }

        return optionalGenero;
    }

    

}
