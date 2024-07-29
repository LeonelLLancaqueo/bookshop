package tienda.libros.Libros.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;


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
public class Genero {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idGenero;
    String nombre;
    String descripcion;

    //@JsonManagedReference(value = "libro-genero")
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name= "idGenero")
    List<Libro> colLibro;


}
