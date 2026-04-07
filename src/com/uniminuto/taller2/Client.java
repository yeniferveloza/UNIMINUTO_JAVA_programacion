package com.uniminuto.taller2;

public class Client {
	
	private String nombres;
    private String apellidos;
    private int edad;
    private String representante;
    
    public Client(String nombres, String apellidos, int edad, String representante) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.edad = edad;
        this.representante = representante;
    }
    
    public String getNombreCompleto() {
    		return nombres + " " + apellidos;
    	}
    public int getEdad() {
    		return edad;
    	}
    public String getRepresentante() {
    		return representante;
    	}

}
