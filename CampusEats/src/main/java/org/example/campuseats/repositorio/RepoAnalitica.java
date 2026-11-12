
package org.example.campuseats.repositorio;

import org.example.campuseats.modelo.LineaPedido;
import org.example.campuseats.modelo.Pago;

import java.time.LocalDate;
import java.util.List;

public interface RepoAnalitica {

    void guardar(List<LineaPedido> lineas, List<Pago> pagos);

    List<LineaPedido> lineasEntre(LocalDate desde, LocalDate hasta);

    List<Pago> pagosEntre(LocalDate desde, LocalDate hasta);
}
