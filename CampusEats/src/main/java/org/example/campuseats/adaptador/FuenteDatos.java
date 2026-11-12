
package org.example.campuseats.adaptador;

import java.io.IOException;
import java.util.List;

public interface FuenteDatos<T> {

    List<T> leer() throws IOException;

}
