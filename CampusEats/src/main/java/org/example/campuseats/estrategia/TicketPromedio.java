
package org.example.campuseats.estrategia;

import org.example.campuseats.modelo.LineaPedido;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class TicketPromedio implements Metrica {

    @Override
    public String nombre() {
        return "Ticket promedio";
    }

    @Override
    public void calcular(List<LineaPedido> datos,
                         LocalDate desde,
                         LocalDate hasta) {

        Map<String, BigDecimal> totalesPedidos = new HashMap<>();

        for (LineaPedido linea : datos) {

            LocalDate fecha = linea.getRegistradoEn().toLocalDate();

            if (!fecha.isBefore(desde) && !fecha.isAfter(hasta)) {

                BigDecimal subtotal = linea.calcularSubtotal();

                totalesPedidos.merge(
                        linea.getNumeroPedido(),
                        subtotal,
                        BigDecimal::add
                );
            }
        }

        BigDecimal totalVentas = totalesPedidos.values()
                .stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        if (totalesPedidos.isEmpty()) {
            System.out.println("No existen pedidos en el periodo.");
            return;
        }

        BigDecimal promedio = totalVentas.divide(
                BigDecimal.valueOf(totalesPedidos.size()),
                2,
                RoundingMode.HALF_UP
        );

        System.out.println("\n" + nombre());

        System.out.println("Pedidos analizados: " + totalesPedidos.size());
        System.out.println("Total de ventas: $" + totalVentas);
        System.out.println("Promedio por pedido: $" + promedio);
    }
}
