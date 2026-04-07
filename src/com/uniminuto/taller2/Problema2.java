package com.uniminuto.taller2;

import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

public class Problema2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner leer = new Scanner(System.in);
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.println("=== SISTEMA DE PUERTO - ALQUILER DE AMARRES ===\n");
        
        System.out.print("Nombre del cliente: ");
        String nombre = leer.nextLine();
        System.out.print("Identificación del cliente: ");
        String id = leer.nextLine();
        Cliente miCliente = new Cliente(nombre, id);

        System.out.print("\nMatrícula del barco: ");
        String matricula = leer.nextLine();
        System.out.print("Eslora (en metros, ej: 15.5): ");
        double eslora = leer.nextDouble();
        System.out.print("Año de fabricación: ");
        int anio = leer.nextInt();
        leer.nextLine();
        Barco miBarco = new Barco(matricula, eslora, anio);

        System.out.print("\nPosición del amarre (Ej: A-12): ");
        String posicion = leer.nextLine();
        
        System.out.print("Fecha inicial (dd/mm/aaaa): ");
        String fechaIniStr = leer.nextLine();
        LocalDate fechaInicio = LocalDate.parse(fechaIniStr, formatoFecha);
        
        System.out.print("Fecha final (dd/mm/aaaa): ");
        String fechaFinStr = leer.nextLine();
        LocalDate fechaFin = LocalDate.parse(fechaFinStr, formatoFecha);

        Alquiler nuevoAlquiler = new Alquiler(miCliente, miBarco, fechaInicio, fechaFin, posicion);
        
        miCliente.agregarAlquiler(nuevoAlquiler);

        nuevoAlquiler.imprimirRecibo();
        
        leer.close();
	}

}
