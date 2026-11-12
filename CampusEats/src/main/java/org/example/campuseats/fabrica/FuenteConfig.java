
package org.example.campuseats.fabrica;

public class FuenteConfig {

    private String tipo;
    private String ruta;

    public FuenteConfig() {
    }

    public FuenteConfig(String tipo, String ruta) {
        this.tipo = tipo;
        this.ruta = ruta;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }
}
