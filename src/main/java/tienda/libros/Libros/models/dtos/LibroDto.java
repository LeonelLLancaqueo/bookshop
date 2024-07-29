package tienda.libros.Libros.models.dtos;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LibroDto {

    int idLibro;
    String nombre;
    String url;
    String descripcion;
    double precio;
    int unidadesEnTotal;
    int unidadesDisponibles;
    int unidadesVendidas;
    String genero;
    String autor;
}
