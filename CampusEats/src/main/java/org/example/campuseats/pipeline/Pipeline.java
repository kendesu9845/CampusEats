
package org.example.campuseats.pipeline;

import java.util.ArrayList;
import java.util.List;
import java.util.function.ToIntFunction;

public class Pipeline<T> {

    private final List<Filtro<T>> filtros;
    private final ToIntFunction<T> contadorDetalles;

    public Pipeline(ToIntFunction<T> contadorDetalles) {
        this.filtros = new ArrayList<>();
        this.contadorDetalles = contadorDetalles;
    }

    public void agregarFiltro(Filtro<T> filtro) {
        filtros.add(filtro);
    }

    public List<T> ejecutar(List<T> datos) {

        List<T> resultado = new ArrayList<>(datos);

        int pedidosOriginales = resultado.size();
        int lineasOriginales = contarDetalles(resultado);


        System.out.println("          INFORME DE LIMPIEZA DE DATOS");

        System.out.printf("%-27s %12s %12s%n",
                "FILTRO APLICADO",
                "PEDIDOS",
                "LINEAS");


        for (Filtro<T> filtro : filtros) {

            int pedidosAntes = resultado.size();
            int lineasAntes = contarDetalles(resultado);

            resultado = filtro.aplicar(resultado);

            int pedidosDespues = resultado.size();
            int lineasDespues = contarDetalles(resultado);

            int pedidosDescartados = pedidosAntes - pedidosDespues;
            int lineasDescartadas = lineasAntes - lineasDespues;

            System.out.printf("%-27s %12d %12d%n",
                    nombreFiltro(filtro),
                    pedidosDescartados,
                    lineasDescartadas);
        }



        System.out.println("RESULTADO DE LA LIMPIEZA");

        System.out.println("Pedidos originales: " + pedidosOriginales);
        System.out.println("Pedidos finales:    " + resultado.size());

        System.out.println("Lineas originales:  " + lineasOriginales);
        System.out.println("Lineas finales:     " + contarDetalles(resultado));


        return resultado;
    }

    private String nombreFiltro(Filtro<T> filtro) {

        String nombre = filtro.getClass().getSimpleName();

        return switch (nombre) {
            case "EliminarDuplicados" -> "Eliminar duplicados";
            case "SoloPedidosPagados" -> "Filtrar no pagados";
            case "ValidarCantidades" -> "Validar cantidades";
            case "ValidarPagos" -> "Validar pagos";
            case "EnriquecerLineas" -> "Enriquecer informacion";
            default -> nombre;
        };
    }

    private int contarDetalles(List<T> datos) {

        int total = 0;

        for (T elemento : datos) {
            total += contadorDetalles.applyAsInt(elemento);
        }

        return total;
    }
}
