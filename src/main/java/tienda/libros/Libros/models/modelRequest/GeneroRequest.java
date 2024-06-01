package tienda.libros.Libros.models.modelRequest;


import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tienda.libros.Libros.models.Libro;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GeneroRequest {
    String nombre;
    String descripcion;
    List<Libro> colLibro;

    public boolean tieneColLibro(){
        return colLibro != null;
    }
}
