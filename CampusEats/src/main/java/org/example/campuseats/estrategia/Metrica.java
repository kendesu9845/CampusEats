
package org.example.campuseats.estrategia;

import org.example.campuseats.modelo.LineaPedido;

import java.time.LocalDate;
import java.util.List;

public interface Metrica {

    String nombre();

    void calcular(List<LineaPedido> datos,
                  LocalDate desde,
                  LocalDate hasta);
}
