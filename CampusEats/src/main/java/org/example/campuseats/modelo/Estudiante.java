
package org.example.campuseats.modelo;

public class Estudiante {

    private String id;
    private String carrera;

    public Estudiante() {
    }

    public Estudiante(String id, String carrera) {
        this.id = id;
        this.carrera = carrera;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }
}
