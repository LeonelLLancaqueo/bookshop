package tienda.libros.Libros.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import lombok.AllArgsConstructor;
import tienda.libros.Libros.models.Autor;
import tienda.libros.Libros.models.Libro;
import tienda.libros.Libros.models.dtos.AutorDto;
import tienda.libros.Libros.models.modelRequest.AutorRequest;
import tienda.libros.Libros.repository.IAutorRepository;

import static java.util.stream.Collectors.toList;


import java.util.HashMap;

@Service
@AllArgsConstructor
public class AutorService {

    @Autowired
    IAutorRepository autorRepository;


    public List<Autor> getAll(){
        return autorRepository.findAll();
    }
    public List<AutorDto> getAllConvertedToDto(){
        
       
        List<AutorDto> listAutorDtos= autorRepository.getAllUserConvertedToDto().stream()
            .map(d -> registerToDto(d))
            .collect(toList());

        return listAutorDtos;
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
            autorAux.setUrlImage(autorRequest.getUrlImage());
            autorAux.setDescripcion(autorRequest.getDescripcion());


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
            .urlImage(autorRequest.getUrlImage())
            .descripcion(autorRequest.getDescripcion())
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
    public List<Libro> getLibrosAutor(int id){

        Optional<Autor> optionalAutorAux= autorRepository.findById(id);

        if(optionalAutorAux.isPresent()){
            return optionalAutorAux.get().getColLibro();
        }
        return null;
    }


    private AutorDto registerToDto(Object [] t){

        return new AutorDto((int)t[0], (String)t[1], (String)t[2], (String)t[3], (String)t[4]);
    }

    //querys 

    public List<AutorDto> getPorNacionalidad(String nacionalidad){
        
       
        List<AutorDto> listAutorDtos= autorRepository.getPorNacionalidad(nacionalidad).stream()
            .map(d -> registerToDto(d))
            .collect(toList());

        return listAutorDtos;
    }
    public List<Map<String, String>> getIdAndName(){
        
     
        List<Object[]> result= autorRepository.getIdAndName();
        
        List<Map<String,String>> listAutor= result.stream()
            .map(d -> new HashMap<String,String>(){{put("idAutor",  ((Integer)d[0]).toString()); put("nombre", (String)d[1]); }} )
            .collect(toList());        

        return listAutor;
    }
}
