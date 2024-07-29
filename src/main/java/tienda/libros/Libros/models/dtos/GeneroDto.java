package tienda.libros.Libros.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GeneroDto {
    int idGenero;
    String nombre;
    String descripcion;
}
