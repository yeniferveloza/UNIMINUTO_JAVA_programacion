package com.uniminuto.taller2;

import java.util.List;
import java.util.ArrayList;

public class Cliente extends DatosBasicos {
	
	private List<Alquiler> historialAmarres; 

    public Cliente(String nombre, String identificacion) {
        super(nombre, identificacion); // Llama al constructor de Persona
        this.historialAmarres = new ArrayList<>();
    }
    
    public void agregarAlquiler(Alquiler alquiler) {
        this.historialAmarres.add(alquiler);
    }
    
    @Override
    public String obtenerInfo() {
        return super.obtenerInfo() + " | Amarres alquilados: " + historialAmarres.size();
    }

}
