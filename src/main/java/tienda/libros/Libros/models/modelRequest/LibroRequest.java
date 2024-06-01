package tienda.libros.Libros.models.modelRequest;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tienda.libros.Libros.models.Autor;
import tienda.libros.Libros.models.Genero;


@Data

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LibroRequest {
    String nombre;
    Genero genero;
    Autor autor;


    public boolean tieneGenero(){
        return this.genero != null;   
    }

    public boolean tieneAutor(){
        return this.autor != null;
    }
}
