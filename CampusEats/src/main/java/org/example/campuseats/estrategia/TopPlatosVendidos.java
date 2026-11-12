
package org.example.campuseats.estrategia;

import org.example.campuseats.modelo.LineaPedido;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TopPlatosVendidos implements Metrica {

    @Override
    public String nombre() {
        return "Platos más vendidos";
    }

    @Override
    public void calcular(List<LineaPedido> datos,
                         LocalDate desde,
                         LocalDate hasta) {

        Map<String, Integer> ventas = new HashMap<>();

        for (LineaPedido linea : datos) {

            LocalDate fecha = linea.getRegistradoEn().toLocalDate();

            if (!fecha.isBefore(desde) && !fecha.isAfter(hasta)) {

                String plato = linea.getNombrePlato();

                ventas.put(
                        plato,
                        ventas.getOrDefault(plato, 0) + linea.getCantidad()
                );
            }
        }

        System.out.println("\n" + nombre());


        ventas.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(5)
                .forEach(entrada ->
                        System.out.println(
                                entrada.getKey() + ": "
                                        + entrada.getValue() + " unidades"
                        )
                );
    }
}
