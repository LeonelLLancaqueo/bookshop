package tienda.libros.Libros.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import lombok.AllArgsConstructor;
import tienda.libros.Libros.models.Autor;
import tienda.libros.Libros.models.Genero;
import tienda.libros.Libros.models.Libro;
import tienda.libros.Libros.models.modelRequest.AutorRequest;
import tienda.libros.Libros.repository.IAutorRepository;

@Service
@AllArgsConstructor
public class AutorService {

    @Autowired
    IAutorRepository autorRepository;


    public List<Autor> getAll(){
        return autorRepository.findAll();
    }
    public Optional<Autor> getAutorById(int id){
        return autorRepository.findById(id);
    }

    
    public Optional<Autor>  updateAutor(int id, AutorRequest autorRequest){


        //realizamos la consulta 
        Optional<Autor> optionalAutorAux= autorRepository.findById(id);

        //verificamos si se encontro un autor con tal id 
        if(optionalAutorAux.isPresent()){

            //obtenemos la instancia de autor
            Autor autorAux= optionalAutorAux.get();
            //setteamos los cambios en la referencia
            autorAux.setNombre(autorRequest.getNombre());
            autorAux.setNacionalidad(autorRequest.getNacionalidad());

            //verificamos si se ingreso una coleccion

            //REVISAR -------> SI LA LOGICA ESTA BIEN
            
            if(autorRequest.getColLibro() != null){
                autorAux.setColLibro(autorRequest.getColLibro());
            }
            

            optionalAutorAux= Optional.of(autorRepository.save(autorAux));
        
  
        }

        return optionalAutorAux;
    }

    public Optional<Autor> saveAutor(AutorRequest autorRequest){
        //Devolvemos optional en caso de que tengamos q validar algo 
        //en la base de datos antes de guardar la entidad
        //de manera que si no se pudo guardar devolvemos un Optional
        //seteado con null

        
        Autor autor= Autor.builder()
            .nombre(autorRequest.getNombre())
            .nacionalidad(autorRequest.getNacionalidad())
            .colLibro(new ArrayList<Libro>() )
            .build();
        
        return Optional.of(autorRepository.save(autor));
    }

    
    public Optional<Autor> deleteAutor(int id){
            
        Optional<Autor> optionalAutorAux= autorRepository.findById(id);
        
        if(optionalAutorAux.isPresent()){
        
            optionalAutorAux.get().getColLibro();
            autorRepository.deleteById(id);
        }
        
        return optionalAutorAux;
    }
    /* 
    public List<Genero> getColGenerosUsados(int id){
        return autorRepository.colGenerosUsados(id);
    }
    */
}
