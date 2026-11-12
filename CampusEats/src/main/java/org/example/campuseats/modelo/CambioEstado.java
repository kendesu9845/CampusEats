
package org.example.campuseats.modelo;

import java.time.LocalDateTime;

public class CambioEstado {

    private String estado;
    private LocalDateTime fechaHora;
    private String usuario;

    public CambioEstado() {
    }

    public CambioEstado(String estado,
                        LocalDateTime fechaHora,
                        String usuario) {
        this.estado = estado;
        this.fechaHora = fechaHora;
        this.usuario = usuario;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}
