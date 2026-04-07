package com.uniminuto.taller2;

public class Matricula extends Estudiante {
	
	private int semestre;
    private String carrera;
    private boolean esEnLinea;

    public Matricula(String nom, String ape, String doc, String dir, String tel, int sem, String car, boolean enLinea) {
        super(nom, ape, doc, dir, tel); // Herencia
        this.semestre = sem;
        this.carrera = car;
        this.esEnLinea = enLinea;
    }

    // Método con retorno: Calcula el costo del crédito según el semestre
    public double calcularPrecioCredito() {
        if (semestre <= 3) return 20;
        if (semestre <= 6) return 25;
        return 30;
    }

    // Método void: Imprime el recibo detallado
    public void imprimirRecibo(int creditosPerdidos) {
        double total = 0;
        System.out.println("\n--- RECIBO OFICIAL DE MATRÍCULA ---");
        System.out.println(super.getInfoPersonal());
        System.out.println("Carrera: " + carrera);
        System.out.println("Semestre: " + semestre);

        if (esEnLinea) {
            total = 1500;
            if (semestre >= 5) total *= 1.05; // Aumento 5%
            System.out.println("Modalidad: En Línea");
        } else {
            double costoSemestre = 2000; // Valor base ejemplo para presencial
            double costoCreditos = creditosPerdidos * calcularPrecioCredito();
            total = costoSemestre + costoCreditos;
            System.out.println("Modalidad: Presencial (Materias aplazadas)");
            System.out.println("Costo créditos extra: $" + costoCreditos);
        }

        System.out.println("TOTAL A PAGAR: $" + total);
        System.out.println("------------------------------------");
    }

}
