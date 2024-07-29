package tienda.libros.Libros.services;

import java.util.Optional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import tienda.libros.Libros.models.Genero;
import tienda.libros.Libros.models.Libro;
import tienda.libros.Libros.models.dtos.GeneroDto;
import tienda.libros.Libros.models.modelRequest.GeneroRequest;
import tienda.libros.Libros.repository.IGeneroRepository;
import static java.util.stream.Collectors.toList;


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

    //otros
    
    private GeneroDto convertToGeneroDto(Object[] resultConsulta){
        return new GeneroDto((int)resultConsulta[0], (String)resultConsulta[1], (String)resultConsulta[2]);
    }
    
    public Optional<GeneroDto> getGeneroDtoByName(String name){
        
        Optional<GeneroDto> response= Optional.empty();

        List<Object[]> request= generoRepository.getGeneroByName(name);

         
        System.out.println(request.toString() +" size: "+ request.size());

        if(!request.isEmpty()){    
           response= Optional.of(convertToGeneroDto(request.get(0)));
        }
        
        return  response;
    } 

        public List<Map<String, String>> getIdAndName(){
        
     
        List<Object[]> result= generoRepository.getIdAndName();
        
        List<Map<String,String>> listAutor= result.stream()
            .map(d -> new HashMap<String,String>(){{put("idGenero",  ((Integer)d[0]).toString()); put("nombre", (String)d[1]); }} )
            .collect(toList());        
            
        return listAutor;
    }
        

}
