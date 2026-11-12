
package org.example.campuseats.modelo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Pago {

    private String referencia;
    private BigDecimal monto;
    private String estadoTx;
    private String codigoAutorizacion;
    private String motivoRechazo;
    private LocalDateTime fechaHora;

    public Pago() {
    }

    public Pago(String referencia, BigDecimal monto,
                String estadoTx, String codigoAutorizacion,
                String motivoRechazo, LocalDateTime fechaHora) {

        this.referencia = referencia;
        this.monto = monto;
        this.estadoTx = estadoTx;
        this.codigoAutorizacion = codigoAutorizacion;
        this.motivoRechazo = motivoRechazo;
        this.fechaHora = fechaHora;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public String getEstadoTx() {
        return estadoTx;
    }

    public void setEstadoTx(String estadoTx) {
        this.estadoTx = estadoTx;
    }

    public String getCodigoAutorizacion() {
        return codigoAutorizacion;
    }

    public void setCodigoAutorizacion(String codigoAutorizacion) {
        this.codigoAutorizacion = codigoAutorizacion;
    }

    public String getMotivoRechazo() {
        return motivoRechazo;
    }

    public void setMotivoRechazo(String motivoRechazo) {
        this.motivoRechazo = motivoRechazo;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }
}
