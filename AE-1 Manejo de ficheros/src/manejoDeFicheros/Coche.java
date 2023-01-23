package manejoDeFicheros;

import java.io.Serializable;

public class Coche implements Serializable {
    private int id;
    private String matricula;
    private String marca;
    private String modelo;
    private String color;

    public Coche(int id, String licensePlate, String brand, String model, String color) {
        this.id = id;
        this.matricula = licensePlate;
        this.marca = brand;
        this.modelo = model;
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLicensePlate() {
        return matricula;
    }

    public void setLicensePlate(String licensePlate) {
        this.matricula = licensePlate;
    }

    public String getBrand() {
        return marca;
    }

    public void setBrand(String brand) {
        this.marca = brand;
    }

    public String getModel() {
        return modelo;
    }

    public void setModel(String model) {
        this.modelo = model;
    }

    public String getColor() {
        return color;
    }
}