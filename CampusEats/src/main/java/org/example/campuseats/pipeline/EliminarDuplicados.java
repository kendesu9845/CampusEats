
package org.example.campuseats.pipeline;

import org.example.campuseats.modelo.Pedido;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EliminarDuplicados implements Filtro<Pedido> {

    @Override
    public List<Pedido> aplicar(List<Pedido> datos) {

        List<Pedido> resultado = new ArrayList<>();

        Set<String> pedidosRegistrados = new HashSet<>();

        for (Pedido pedido : datos) {

            String numeroPedido = pedido.getNumeroPedido();

            if (pedidosRegistrados.add(numeroPedido)) {

                resultado.add(pedido);

            }
        }

        return resultado;
    }
}
