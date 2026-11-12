
package org.example.campuseats.pipeline;

import org.example.campuseats.modelo.CambioEstado;
import org.example.campuseats.modelo.Pedido;

import java.util.ArrayList;
import java.util.List;

public class SoloPedidosPagados implements Filtro<Pedido> {

    @Override
    public List<Pedido> aplicar(List<Pedido> datos) {

        List<Pedido> resultado = new ArrayList<>();

        for (Pedido pedido : datos) {

            boolean fuePagado = false;

            // Revisar el historial de estados
            for (CambioEstado cambio : pedido.getHistorialEstados()) {

                if ("PAGADO".equals(cambio.getEstado())) {

                    fuePagado = true;
                    break;
                }
            }

            // Conservar únicamente los pedidos que fueron pagados
            if (fuePagado) {
                resultado.add(pedido);
            }
        }

        return resultado;
    }
}
