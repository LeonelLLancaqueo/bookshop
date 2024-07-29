package tienda.libros.Libros.models;

import java.util.ArrayList;
import java.util.List;


import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idAutor;

    String nombre;
    String nacionalidad;
    String urlImage;
    String descripcion;
    
    @JsonManagedReference(value = "libro-autor")
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name= "idAutor")
    
    List<Libro> colLibro;

    public List<Genero> colGenerosUsados(){
        
        List<Genero> colGenerosUsados= new ArrayList<Genero>();
        for (Libro libro: colLibro){
                            
            Genero unGenero= libro.genero;
            if(!colGenerosUsados.contains(unGenero)){
                colGenerosUsados.add(unGenero);
            }
                
        }

        return colGenerosUsados;
    }

    public boolean addLibro(Libro libro){
        return colLibro.add(libro);
    }

    
    

}
