
package org.example.campuseats.modelo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class LineaPedido {

    private String numeroPedido;
    private int platoId;
    private String nombrePlato;
    private int cantidad;
    private BigDecimal precioUnitario;
    private LocalDateTime registradoEn;
    private String categoria;
    private LocalDateTime fechaPago;
    private LocalDateTime fechaPreparacion;
    private LocalDateTime fechaListoRetiro;


    public LineaPedido() {
    }

    public LineaPedido(String numeroPedido, int platoId,
                       String nombrePlato, int cantidad,
                       BigDecimal precioUnitario,
                       LocalDateTime registradoEn) {

        this.numeroPedido = numeroPedido;
        this.platoId = platoId;
        this.nombrePlato = nombrePlato;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.registradoEn = registradoEn;
    }

    public LocalDateTime getFechaPreparacion() {
        return fechaPreparacion;
    }

    public void setFechaPreparacion(LocalDateTime fechaPreparacion) {
        this.fechaPreparacion = fechaPreparacion;
    }

    public LocalDateTime getFechaListoRetiro() {
        return fechaListoRetiro;
    }

    public void setFechaListoRetiro(LocalDateTime fechaListoRetiro) {
        this.fechaListoRetiro = fechaListoRetiro;
    }

    public LocalDateTime getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(LocalDateTime fechaPago) {
        this.fechaPago = fechaPago;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getNumeroPedido() {
        return numeroPedido;
    }

    public void setNumeroPedido(String numeroPedido) {
        this.numeroPedido = numeroPedido;
    }

    public int getPlatoId() {
        return platoId;
    }

    public void setPlatoId(int platoId) {
        this.platoId = platoId;
    }

    public String getNombrePlato() {
        return nombrePlato;
    }

    public void setNombrePlato(String nombrePlato) {
        this.nombrePlato = nombrePlato;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public LocalDateTime getRegistradoEn() {
        return registradoEn;
    }

    public void setRegistradoEn(LocalDateTime registradoEn) {
        this.registradoEn = registradoEn;
    }

    public BigDecimal calcularSubtotal() {
        return precioUnitario.multiply(BigDecimal.valueOf(cantidad));
    }
}
