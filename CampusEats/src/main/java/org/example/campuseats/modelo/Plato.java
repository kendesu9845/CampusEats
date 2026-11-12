
package org.example.campuseats.modelo;

import java.math.BigDecimal;

public class Plato {

    private int idPlato;
    private String nombrePlato;
    private String categoria;
    private BigDecimal precioActual;
    private String estado;

    public Plato() {
    }

    public Plato(int idPlato, String nombrePlato,
                 String categoria, BigDecimal precioActual,
                 String estado) {
        this.idPlato = idPlato;
        this.nombrePlato = nombrePlato;
        this.categoria = categoria;
        this.precioActual = precioActual;
        this.estado = estado;
    }

    public int getIdPlato() {
        return idPlato;
    }

    public void setIdPlato(int idPlato) {
        this.idPlato = idPlato;
    }

    public String getNombrePlato() {
        return nombrePlato;
    }

    public void setNombrePlato(String nombrePlato) {
        this.nombrePlato = nombrePlato;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public BigDecimal getPrecioActual() {
        return precioActual;
    }

    public void setPrecioActual(BigDecimal precioActual) {
        this.precioActual = precioActual;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
