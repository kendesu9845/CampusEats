
package org.example.campuseats.pipeline;

import java.util.List;

public interface Filtro<T> {

    List<T> aplicar(List<T> datos);

}
