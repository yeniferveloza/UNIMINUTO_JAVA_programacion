package com.uniminuto.taller2;

public class Estudiante {
	
	private String nombres, apellidos, documento, direccion, telefono;

    public Estudiante(String nombres, String apellidos, String documento, String direccion, String telefono) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.documento = documento;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    // Método con retorno para obtener el nombre completo
    public String getInfoPersonal() {
        return "Estudiante: " + nombres + " " + apellidos + "\nDocumento: " + documento;
    }

}
