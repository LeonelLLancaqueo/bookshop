package tienda.libros.Libros.models.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AutorDto {
    
    int idAutor;
    String nombre;
    String nacionalidad;
    String urlImage;
    String descripcion;
}
