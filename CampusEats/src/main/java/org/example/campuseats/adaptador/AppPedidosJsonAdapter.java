
package org.example.campuseats.adaptador;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import org.example.campuseats.modelo.Pedido;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class AppPedidosJsonAdapter implements FuenteDatos<Pedido> {

    private final Path ruta;
    private final ObjectMapper mapper;

    public AppPedidosJsonAdapter(Path ruta) {
        this.ruta = ruta;

        this.mapper = new ObjectMapper();
        this.mapper.registerModule(new JavaTimeModule());
    }

    @Override
    public List<Pedido> leer() throws IOException {

        return mapper.readValue(
                ruta.toFile(),
                new TypeReference<List<Pedido>>() {}
        );
    }
}
