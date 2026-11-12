
package org.example.campuseats.fabrica;

import org.example.campuseats.adaptador.*;

import java.nio.file.Path;

public class FabricaFuentes {

    public static FuenteDatos<?> crear(FuenteConfig config) {

        Path ruta = Path.of(config.getRuta());

        switch (config.getTipo()) {

            case "app_json":
                return new AppPedidosJsonAdapter(ruta);

            case "pasarela_csv":
                return new PasarelaCsvAdapter(ruta);

            case "menu_csv":
                return new MenuCsvAdapter(ruta);

            default:
                throw new IllegalArgumentException(
                        "Tipo de fuente desconocido: " + config.getTipo()
                );
        }
    }
}
