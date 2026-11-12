
package org.example.campuseats.servicio;

import org.example.campuseats.adaptador.FuenteDatos;
import org.example.campuseats.modelo.Pedido;
import org.example.campuseats.modelo.Pago;
import org.example.campuseats.modelo.Plato;
import org.example.campuseats.modelo.LineaPedido;

import org.example.campuseats.pipeline.*;
import org.example.campuseats.estrategia.Metrica;
import org.example.campuseats.repositorio.RepoAnalitica;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ServicioAnalitica {

    private final FuenteDatos<Pedido> fuentePedidos;
    private final FuenteDatos<Pago> fuentePagos;
    private final FuenteDatos<Plato> fuenteMenu;

    private final RepoAnalitica repositorio;
    private final List<Metrica> metricas;

    public ServicioAnalitica(
            FuenteDatos<Pedido> fuentePedidos,
            FuenteDatos<Pago> fuentePagos,
            FuenteDatos<Plato> fuenteMenu,
            RepoAnalitica repositorio,
            List<Metrica> metricas) {

        this.fuentePedidos = fuentePedidos;
        this.fuentePagos = fuentePagos;
        this.fuenteMenu = fuenteMenu;
        this.repositorio = repositorio;
        this.metricas = List.copyOf(metricas);
    }

    // Método 1: cargar y procesar los datos
    public void cargar() throws IOException {

        // Leer los archivos
        List<Pedido> pedidos = fuentePedidos.leer();
        List<Pago> pagos = fuentePagos.leer();
        List<Plato> platos = fuenteMenu.leer();

        // Crear Pipeline
        Pipeline<Pedido> pipeline = new Pipeline<>(
                pedido -> pedido.getLineas().size()
        );

        // Agregar filtros
        pipeline.agregarFiltro(new EliminarDuplicados());
        pipeline.agregarFiltro(new SoloPedidosPagados());
        pipeline.agregarFiltro(new ValidarCantidades());
        pipeline.agregarFiltro(new ValidarPagos(pagos));
        pipeline.agregarFiltro(new EnriquecerLineas(platos));

        // Ejecutar Pipeline
        List<Pedido> pedidosLimpios = pipeline.ejecutar(pedidos);

        // Obtener todas las líneas de los pedidos limpios
        List<LineaPedido> lineas = new ArrayList<>();

        for (Pedido pedido : pedidosLimpios) {
            lineas.addAll(pedido.getLineas());
        }

        // Guardar datos en el repositorio
        repositorio.guardar(lineas, pagos);

        System.out.println("\nDATOS CARGADOS CORRECTAMENTE");

        System.out.println("Pedidos originales: " + pedidos.size());
        System.out.println("Pedidos finales: " + pedidosLimpios.size());
        System.out.println("Líneas finales: " + lineas.size());
        System.out.println("Pagos leídos: " + pagos.size());
    }

    // Método 2: generar el resumen de métricas
    public void resumen(LocalDate desde, LocalDate hasta) {

        List<LineaPedido> lineas =
                repositorio.lineasEntre(desde, hasta);

        List<Pago> pagos =
                repositorio.pagosEntre(desde, hasta);

        System.out.println("\nRESUMEN DE ANALÍTICA");

        System.out.println("Desde: " + desde);
        System.out.println("Hasta: " + hasta);
        System.out.println("Líneas analizadas: " + lineas.size());
        System.out.println("Transacciones registradas: " + pagos.size());

        // Ejecutar todas las estrategias
        for (Metrica metrica : metricas) {
            metrica.calcular(lineas, desde, hasta);
        }
    }
}
