package tienda.libros.Libros.models;

import com.fasterxml.jackson.annotation.JsonBackReference;


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
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idGenero")
    @JsonBackReference
    Genero genero;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idAutor")
    @JsonBackReference
    Autor autor;

}
