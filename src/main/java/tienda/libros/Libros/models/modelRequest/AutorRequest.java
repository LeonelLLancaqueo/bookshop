package tienda.libros.Libros.models.modelRequest;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;




@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AutorRequest {


    String nombre;
    String nacionalidad;
    String urlImage;
    String descripcion;    



}
