package com.example.crud.pruebaTec.model;
import jakarta.persistence.*;

@Entity
@Table(name = "clientes")
public class Cliente extends Persona {

    @Column(unique = true)
    private String clienteid;
    private String contrasena;
    private boolean estado;

    public Cliente() {
    }

    public Cliente(String nombre, String genero, int edad, String identificacion, String direccion, String telefono, String clienteid, String contrasena, boolean estado) {
        super(nombre, genero, edad, identificacion, direccion, telefono);
        this.clienteid = clienteid;
        this.contrasena = contrasena;
        this.estado = estado;
    }

    // Getters y Setters

    public String getClienteid() {
        return clienteid;
    }

    public void setClienteid(String clienteid) {
        this.clienteid = clienteid;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}

