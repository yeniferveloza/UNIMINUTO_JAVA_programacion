package com.uniminuto.taller2;

public class Barco {
	
	private String matricula;
    private double eslora;
    private int anioFabricacion;

    public Barco(String matricula, double eslora, int anioFabricacion) {
        this.matricula = matricula;
        this.eslora = eslora;
        this.anioFabricacion = anioFabricacion;
    }
    
    public String getDetallesBarco() {
        return "Matrícula: " + matricula + " | Eslora: " + eslora + " metros | Año: " + anioFabricacion;
    }

}
