
package org.example.campuseats;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.example.campuseats.adaptador.FuenteDatos;
import org.example.campuseats.fabrica.ConfiguracionFuentes;
import org.example.campuseats.fabrica.FuenteConfig;
import org.example.campuseats.fabrica.FabricaFuentes;

import org.example.campuseats.modelo.Pedido;
import org.example.campuseats.modelo.Pago;
import org.example.campuseats.modelo.Plato;

import org.example.campuseats.estrategia.Metrica;
import org.example.campuseats.estrategia.TopPlatosVendidos;
import org.example.campuseats.estrategia.PedidosPorHora;
import org.example.campuseats.estrategia.TicketPromedio;
import org.example.campuseats.estrategia.TiempoPreparacion;

import org.example.campuseats.repositorio.RepoAnalitica;
import org.example.campuseats.repositorio.InMemoryRepoAnalitica;

import org.example.campuseats.servicio.ServicioAnalitica;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        try {

            // Leer configuración
            ObjectMapper mapper = new ObjectMapper();

            ConfiguracionFuentes configuracion =
                    mapper.readValue(
                            Path.of("config/fuentes.json").toFile(),
                            ConfiguracionFuentes.class
                    );

            // Crear fuentes mediante Factory
            FuenteDatos<Pedido> fuentePedidos =
                    crearFuente(configuracion, "app_json");

            FuenteDatos<Pago> fuentePagos =
                    crearFuente(configuracion, "pasarela_csv");

            FuenteDatos<Plato> fuenteMenu =
                    crearFuente(configuracion, "menu_csv");

            // Crear repositorio
            RepoAnalitica repositorio =
                    new InMemoryRepoAnalitica();

            // Crear estrategias
            List<Metrica> metricas = List.of(
                    new TopPlatosVendidos(),
                    new PedidosPorHora(),
                    new TicketPromedio()
                    //new TiempoPreparacion()
            );

            // Crear servicio Facade
            ServicioAnalitica servicio =
                    new ServicioAnalitica(
                            fuentePedidos,
                            fuentePagos,
                            fuenteMenu,
                            repositorio,
                            metricas
                    );

            // Ejecutar el análisis
            servicio.cargar();

            servicio.resumen(
                    LocalDate.of(2026, 10, 19),
                    LocalDate.of(2026, 10, 30)
            );

        } catch (Exception e) {

            System.out.println("Error al ejecutar la analítica:");
            e.printStackTrace();

        }
    }

    @SuppressWarnings("unchecked")
    private static <T> FuenteDatos<T> crearFuente(
            ConfiguracionFuentes configuracion,
            String tipo) {

        for (FuenteConfig fuente : configuracion.getFuentes()) {

            if (fuente.getTipo().equals(tipo)) {
                return (FuenteDatos<T>) FabricaFuentes.crear(fuente);
            }
        }

        throw new IllegalArgumentException(
                "No se encontró la fuente: " + tipo
        );
    }
}
