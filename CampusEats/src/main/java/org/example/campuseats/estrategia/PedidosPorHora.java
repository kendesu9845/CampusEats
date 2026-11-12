
package org.example.campuseats.estrategia;

import org.example.campuseats.modelo.LineaPedido;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PedidosPorHora implements Metrica {

    @Override
    public String nombre() {
        return "Pedidos por hora";
    }


    @Override
    public void calcular(List<LineaPedido> datos,
                         LocalDate desde,
                         LocalDate hasta) {

        Map<Integer, Set<String>> pedidosPorHora = new HashMap<>();

        for (LineaPedido linea : datos) {

            // Obtener la fecha y hora del pago
            if (linea.getFechaPago() == null) {
                continue;
            }

            LocalDate fecha = linea.getFechaPago().toLocalDate();

            // Comprobar que el pago esté dentro del rango
            if (!fecha.isBefore(desde) && !fecha.isAfter(hasta)) {

                int hora = linea.getFechaPago().getHour();

                // Contar cada pedido una sola vez por hora
                pedidosPorHora
                        .computeIfAbsent(hora, h -> new HashSet<>())
                        .add(linea.getNumeroPedido());
            }
        }

        System.out.println("\n" + nombre());


        pedidosPorHora.entrySet()
                .stream()
                .sorted((a, b) ->
                        Integer.compare(b.getValue().size(), a.getValue().size()))
                .limit(5)
                .forEach(entrada ->
                        System.out.println(
                                entrada.getKey() + ":00 - "
                                        + entrada.getValue().size()
                                        + " pedidos"
                        )
                );
    }

}
