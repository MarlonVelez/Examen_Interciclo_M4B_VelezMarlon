package com.example.examen_interciclo_m4b_velezmarlon.modelo;

public class Proveedor {

    private String RUC;
    private String nombreComercial;
    private String representanteLegal;
    private String direccion;
    private String telefono;
    private String productos;
    private double credito;

    public Proveedor(String RUC, String nombreComercial, String representanteLegal, String direccion, String telefono, String productos, double credito) {
        this.RUC = RUC;
        this.nombreComercial = nombreComercial;
        this.representanteLegal = representanteLegal;
        this.direccion = direccion;
        this.telefono = telefono;
        this.productos = productos;
        this.credito = credito;
    }

    public Proveedor() {
    }

    public String getRUC() {
        return RUC;
    }

    public void setRUC(String RUC) {
        this.RUC = RUC;
    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    public String getRepresentanteLegal() {
        return representanteLegal;
    }

    public void setRepresentanteLegal(String representanteLegal) {
        this.representanteLegal = representanteLegal;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getProductos() {
        return productos;
    }

    public void setProductos(String productos) {
        this.productos = productos;
    }

    public double getCredito() {
        return credito;
    }

    public void setCredito(double credito) {
        this.credito = credito;
    }
}
