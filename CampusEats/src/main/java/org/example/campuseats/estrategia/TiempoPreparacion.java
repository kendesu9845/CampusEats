
package org.example.campuseats.estrategia;

import org.example.campuseats.modelo.LineaPedido;

import java.time.Duration;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TiempoPreparacion implements Metrica {

    @Override
    public String nombre() {
        return "Tiempo promedio de preparacion";
    }

    @Override
    public void calcular(List<LineaPedido> datos,
                         LocalDate desde,
                         LocalDate hasta) {

        // Guardamos un solo tiempo por pedido
        Map<String, Duration> tiempos = new HashMap<>();

        for (LineaPedido linea : datos) {

            if (linea.getNumeroPedido() == null
                    || linea.getFechaPreparacion() == null
                    || linea.getFechaListoRetiro() == null) {
                continue;
            }

            LocalDate fecha = linea.getFechaPreparacion().toLocalDate();

            if (fecha.isBefore(desde) || fecha.isAfter(hasta)) {
                continue;
            }

            Duration tiempo = Duration.between(
                    linea.getFechaPreparacion(),
                    linea.getFechaListoRetiro()
            );

            if (!tiempo.isNegative()) {
                tiempos.put(linea.getNumeroPedido(), tiempo);
            }
        }

        if (tiempos.isEmpty()) {
            System.out.println("No hay pedidos con tiempos de preparacion validos.");
            return;
        }

        long totalSegundos = 0;
        long minimo = Long.MAX_VALUE;
        long maximo = Long.MIN_VALUE;

        for (Duration tiempo : tiempos.values()) {

            long segundos = tiempo.getSeconds();

            totalSegundos += segundos;
            minimo = Math.min(minimo, segundos);
            maximo = Math.max(maximo, segundos);
        }

        double promedioSegundos =
                (double) totalSegundos / tiempos.size();

        System.out.println("\n TIEMPO DE PREPARACION");
        System.out.println("Pedidos analizados: " + tiempos.size());

        System.out.printf("Tiempo promedio: %.2f minutos%n",
                promedioSegundos / 60);

        System.out.println("Tiempo minimo: " + formatear(minimo));
        System.out.println("Tiempo maximo: " + formatear(maximo));


    }

    private String formatear(long segundos) {

        long minutos = segundos / 60;
        long segundosRestantes = segundos % 60;

        return minutos + " min " + segundosRestantes + " seg";
    }
}
