
package org.example.campuseats.adaptador;

import org.example.campuseats.modelo.Pago;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PasarelaCsvAdapter implements FuenteDatos<Pago> {

    private final Path ruta;

    public PasarelaCsvAdapter(Path ruta) {
        this.ruta = ruta;
    }

    @Override
    public List<Pago> leer() throws IOException {

        List<Pago> pagos = new ArrayList<>();

        List<String> lineas = Files.readAllLines(ruta);

        DateTimeFormatter formatoFecha =
                DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        // Comenzamos desde 1 para saltar la cabecera
        for (int i = 1; i < lineas.size(); i++) {

            String linea = lineas.get(i);

            String[] datos = linea.split(";", -1);

            String referencia = datos[0];

            BigDecimal monto = new BigDecimal(
                    datos[1].replace(",", ".")
            );

            String estadoTx = datos[2];

            String codigoAutorizacion =
                    datos[3].isBlank() ? null : datos[3];

            String motivoRechazo =
                    datos[4].isBlank() ? null : datos[4];

            LocalDateTime fechaHora = LocalDateTime.parse(
                    datos[5],
                    formatoFecha
            );

            Pago pago = new Pago(
                    referencia,
                    monto,
                    estadoTx,
                    codigoAutorizacion,
                    motivoRechazo,
                    fechaHora
            );

            pagos.add(pago);
        }

        return pagos;
    }
}
