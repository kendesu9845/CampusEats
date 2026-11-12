
package org.example.campuseats.adaptador;

import org.example.campuseats.modelo.Plato;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class MenuCsvAdapter implements FuenteDatos<Plato> {

    private final Path ruta;

    public MenuCsvAdapter(Path ruta) {
        this.ruta = ruta;
    }

    @Override
    public List<Plato> leer() throws IOException {

        List<Plato> platos = new ArrayList<>();

        List<String> lineas = Files.readAllLines(ruta);

        // Comenzamos desde 1 para saltar el encabezado
        for (int i = 1; i < lineas.size(); i++) {

            String linea = lineas.get(i);

            String[] datos = linea.split(",", -1);

            int idPlato = Integer.parseInt(datos[0]);

            String nombrePlato = datos[1];

            String categoria = datos[2];

            BigDecimal precioActual =
                    new BigDecimal(datos[3]);

            String estado = datos[4];

            Plato plato = new Plato(
                    idPlato,
                    nombrePlato,
                    categoria,
                    precioActual,
                    estado
            );

            platos.add(plato);
        }

        return platos;
    }
}
