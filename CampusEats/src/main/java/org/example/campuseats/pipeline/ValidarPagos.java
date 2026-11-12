
package org.example.campuseats.pipeline;

import org.example.campuseats.modelo.Pedido;
import org.example.campuseats.modelo.Pago;

import java.util.ArrayList;
import java.util.List;

public class ValidarPagos implements Filtro<Pedido> {

    private final List<Pago> pagos;

    public ValidarPagos(List<Pago> pagos) {
        this.pagos = pagos;
    }

    @Override
    public List<Pedido> aplicar(List<Pedido> datos) {

        List<Pedido> resultado = new ArrayList<>();

        for (Pedido pedido : datos) {

            boolean pagoValido = false;

            // Buscar los intentos de pago correspondientes al pedido
            for (Pago pago : pagos) {

                boolean mismaReferencia =
                        pedido.getNumeroPedido().equals(pago.getReferencia());

                boolean aprobado =
                        "APROBADA".equals(pago.getEstadoTx());

                boolean mismoMonto =
                        pago.getMonto().compareTo(pedido.getTotal()) == 0;

                if (mismaReferencia && aprobado && mismoMonto) {

                    pagoValido = true;
                    break;
                }
            }

            if (pagoValido) {
                resultado.add(pedido);
            }
        }

        return resultado;
    }
}
