package com.uniminuto.taller2;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Alquiler {
	
	private Cliente cliente;
    private Barco barco;
    private LocalDate fechaInicial;
    private LocalDate fechaFinal;
    private String posicionAmarre;

    public Alquiler(Cliente cliente, Barco barco, LocalDate fechaInicial, LocalDate fechaFinal, String posicionAmarre) {
        this.cliente = cliente;
        this.barco = barco;
        this.fechaInicial = fechaInicial;
        this.fechaFinal = fechaFinal;
        this.posicionAmarre = posicionAmarre;
    }
    
    public double calcularCostoTotal() {
        long dias = ChronoUnit.DAYS.between(fechaInicial, fechaFinal);
        if (dias <= 0) dias = 1;

        double costoBaseDiario = 25000;
        double impuesto = 0.19;
        double costoTotalDiario = costoBaseDiario + (costoBaseDiario * impuesto);

        return dias * costoTotalDiario;
    }
    
    public void imprimirRecibo() {
        long dias = ChronoUnit.DAYS.between(fechaInicial, fechaFinal);
        
        System.out.println("\n========================================");
        System.out.println("      RECIBO DE ALQUILER DE AMARRE      ");
        System.out.println("========================================");
        System.out.println("--- DATOS DEL CLIENTE ---");
        System.out.println(cliente.obtenerInfo());
        
        System.out.println("\n--- DATOS DEL BARCO ---");
        System.out.println(barco.getDetallesBarco());
        
        System.out.println("\n--- DETALLES DEL ALQUILER ---");
        System.out.println("Posición del Amarre: " + posicionAmarre);
        System.out.println("Fecha Inicio: " + fechaInicial);
        System.out.println("Fecha Fin: " + fechaFinal);
        System.out.println("Días facturados: " + (dias <= 0 ? 1 : dias));
        System.out.println("----------------------------------------");
        System.out.println("TOTAL A PAGAR (Inc. Impuestos): $" + calcularCostoTotal());
        System.out.println("========================================");
    }

}
