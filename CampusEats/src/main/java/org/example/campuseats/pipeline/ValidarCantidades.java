
package org.example.campuseats.pipeline;

import org.example.campuseats.modelo.Pedido;
import org.example.campuseats.modelo.LineaPedido;

import java.util.ArrayList;
import java.util.List;

public class ValidarCantidades implements Filtro<Pedido> {

    @Override
    public List<Pedido> aplicar(List<Pedido> datos) {

        List<Pedido> resultado = new ArrayList<>();

        for (Pedido pedido : datos) {

            List<LineaPedido> lineasValidas = new ArrayList<>();

            for (LineaPedido linea : pedido.getLineas()) {

                if (linea.getCantidad() > 0) {
                    lineasValidas.add(linea);
                }
            }

            // Reemplazar las líneas originales por las válidas
            pedido.setLineas(lineasValidas);

            // Conservar el pedido si todavía tiene líneas
            if (!lineasValidas.isEmpty()) {
                resultado.add(pedido);
            }
        }

        return resultado;
    }
}
