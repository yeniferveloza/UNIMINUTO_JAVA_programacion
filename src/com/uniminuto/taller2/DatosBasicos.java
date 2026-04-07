package com.uniminuto.taller2;

public class DatosBasicos {
	
	private String nombre;
    private String identificacion;

    public DatosBasicos(String nombre, String identificacion) {
        this.nombre = nombre;
        this.identificacion = identificacion;
    }
    
    public String getNombre() {
    	return nombre;
    }
    public String getIdentificacion() {
    	return identificacion;
    }
    
    public String obtenerInfo() {
        return "Nombre: " + nombre + " | ID: " + identificacion;
    }

}
