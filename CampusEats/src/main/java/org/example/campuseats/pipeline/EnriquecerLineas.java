
package org.example.campuseats.pipeline;

import org.example.campuseats.modelo.Pedido;
import org.example.campuseats.modelo.Plato;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.time.LocalDateTime;

public class EnriquecerLineas implements Filtro<Pedido> {

    private final Map<Integer, Plato> menu;

    public EnriquecerLineas(List<Plato> platos) {

        menu = new HashMap<>();

        for (Plato plato : platos) {
            menu.put(plato.getIdPlato(), plato);
        }
    }


    @Override
    public List<Pedido> aplicar(List<Pedido> datos) {

        for (Pedido pedido : datos) {

            // Buscar la fecha y hora en que el pedido fue pagado
            LocalDateTime fechaPago = null;

            LocalDateTime fechaPreparacion = null;
            LocalDateTime fechaListoRetiro = null;

            for (var cambio : pedido.getHistorialEstados()) {

                if ("EN_PREPARACION".equalsIgnoreCase(cambio.getEstado())) {
                    fechaPreparacion = cambio.getFechaHora();
                }

                if ("LISTO_RETIRO".equalsIgnoreCase(cambio.getEstado())) {
                    fechaListoRetiro = cambio.getFechaHora();
                }

                if ("PAGADO".equalsIgnoreCase(cambio.getEstado())) {
                    fechaPago = cambio.getFechaHora();
                    break;
                }
            }

            for (var linea : pedido.getLineas()) {

                linea.setFechaPreparacion(fechaPreparacion);
                linea.setFechaListoRetiro(fechaListoRetiro);
                // Copiar información del pedido a cada línea
                linea.setNumeroPedido(pedido.getNumeroPedido());
                linea.setRegistradoEn(pedido.getRegistradoEn());

                // Guardar la fecha y hora del pago
                linea.setFechaPago(fechaPago);

                // Buscar información del plato en el menú
                Plato plato = menu.get(linea.getPlatoId());

                if (plato != null) {
                    linea.setNombrePlato(plato.getNombrePlato());
                    linea.setCategoria(plato.getCategoria());
                }
            }
        }

        return datos;
    }
}
