package es.unican.is2.clases;

import java.util.LinkedList;
import java.util.List;

/*
 * nº métodos = 17
 * WMC = 18
 * WMC/n = 18/17 = 1.05
 * CCog = 1
 * CCog/n = 1/17 = 0.0588
 * DIT = 0
 * NOC = 0
 * CBO EFF = 2; clases: Cuenta, Tarjeta
 * CBO AFF = 0
 * CBO = 2
 */

public class Cliente {

    private String nombre;
    private String calle;
    private String zip;
    private String localidad;
    private String telefono;
    private String dni;

    private List<Cuenta> cuentas = new LinkedList<>();
    private List<Tarjeta> tarjetas = new LinkedList<>();

    // CC = 1
    // CCog = 0
     public Cliente(String titular, String calle, String zip, String localidad,
             String telefono, String dni) {
        this.nombre = titular;
        this.calle = calle;
        this.zip = zip;
        this.localidad = localidad;
        this.telefono = telefono;
        this.dni = dni;
    }

    // CC = 1
    // CCog = 0
    public void cambiaDireccion(String calle, String zip, String localidad) {
        this.calle = calle;
        this.zip = zip;
        this.localidad = localidad;
    }

    // CC = 1
    // CCog = 0
    public void anhadeCuenta(Cuenta c) {
        cuentas.add(c);
    }

    // CC = 1
    // CCog = 0
    public void anhadeTarjeta(Tarjeta t) {
        tarjetas.add(t);
    }

    // CC = 2
    // CCog = 1
    public double getSaldoTotal() {
        double total = 0.0;
        for (Cuenta c: cuentas) { // CCog + 1
            total += c.getSaldo();
        }
        return total;
    }

    // CC = 1
    // CCog = 0
    public String getNombre() {
        return nombre;
    }

    // CC = 1
    // CCog = 0
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // CC = 1
    // CCog = 0
    public String getCalle() {
        return calle;
    }

    // CC = 1
    // CCog = 0
    public void setCalle(String calle) {
        this.calle = calle;
    }

    // CC = 1
    // CCog = 0
    public String getZip() {
        return zip;
    }

    // CC = 1
    // CCog = 0
    public void setZip(String zip) {
        this.zip = zip;
    }

    // CC = 1
    // CCog = 0
    public String getLocalidad() {
        return localidad;
    }

    // CC = 1
    // CCog = 0
    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    // CC = 1
    // CCog = 0
    public String getTelefono() {
        return telefono;
    }

    // CC = 1
    // CCog = 0
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    // CC = 1
    // CCog = 0
    public String getDni() {
        return dni;
    }

    // CC = 1
    // CCog = 0
    public void setDni(String dni) {
        this.dni = dni;
    }

}