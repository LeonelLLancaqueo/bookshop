package tienda.libros.Libros.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idLibro;
    String nombre;
    

    //@JsonBackReference(value = "libro-genero")
     @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idGenero")
    
    Genero genero;

    @JsonBackReference(value = "libro-autor")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idAutor")
    
    Autor autor;

    String url;
    String descripcion;
    double precio;
    int unidadesEnTotal;
    int unidadesDisponibles;
    int unidadesVendidas;

}
