package com.example.accesscontrol.estructural;

import java.io.Serializable;

public class Usuario implements Serializable
{

    private String usuario,password, nombre, apellido,posCompany, genero;
    private  int edad, documento;

    public Usuario(String usuario, String password, String nombre, String apellido, String posCompany, String genero, int edad, int documento) {
        this.usuario = usuario;
        this.password = password;
        this.nombre = nombre;
        this.apellido = apellido;
        this.posCompany = posCompany;
        this.genero = genero;
        this.edad = edad;
        this.documento = documento;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getPosCompany() {
        return posCompany;
    }

    public void setPosCompany(String posCompany) {
        this.posCompany = posCompany;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getDocumento() {
        return documento;
    }

    public void setDocumento(int documento) {
        this.documento = documento;
    }


}
