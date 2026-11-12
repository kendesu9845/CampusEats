
package org.example.campuseats.repositorio;

import org.example.campuseats.modelo.LineaPedido;
import org.example.campuseats.modelo.Pago;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class InMemoryRepoAnalitica implements RepoAnalitica {

    private List<LineaPedido> lineas;
    private List<Pago> pagos;

    public InMemoryRepoAnalitica() {
        this.lineas = new ArrayList<>();
        this.pagos = new ArrayList<>();
    }

    @Override
    public void guardar(List<LineaPedido> lineas, List<Pago> pagos) {
        this.lineas = new ArrayList<>(lineas);
        this.pagos = new ArrayList<>(pagos);
    }

    @Override
    public List<LineaPedido> lineasEntre(LocalDate desde, LocalDate hasta) {

        return lineas.stream()
                .filter(linea -> {
                    LocalDate fecha = linea.getRegistradoEn().toLocalDate();

                    return !fecha.isBefore(desde)
                            && !fecha.isAfter(hasta);
                })
                .toList();
    }

    @Override
    public List<Pago> pagosEntre(LocalDate desde, LocalDate hasta) {

        return pagos.stream()
                .filter(pago -> {
                    LocalDate fecha = pago.getFechaHora().toLocalDate();

                    return !fecha.isBefore(desde)
                            && !fecha.isAfter(hasta);
                })
                .toList();
    }
}
