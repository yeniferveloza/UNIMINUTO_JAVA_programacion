package com.uniminuto.taller2;

import java.util.Scanner;

public class Problema1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);

        // Menú de carreras
        System.out.println("Seleccione Carrera (Digita solo el numero que corresponda):\n1. Sistemas\n2. Derecho\n3. Medicina");
        int op = sc.nextInt();
        String carreraElegida = (op == 1) ? "Sistemas" : (op == 2) ? "Derecho" : "Medicina";
        sc.nextLine();

        // Captura de datos
        System.out.print("Nombres: "); String nom = sc.nextLine();
        System.out.print("Apellidos: "); String ape = sc.nextLine();
        System.out.print("Documento: "); String doc = sc.nextLine();
        System.out.print("Dirección: "); String dir = sc.nextLine();
        System.out.print("Teléfono: "); String tel = sc.nextLine();
        System.out.print("Semestre: "); int sem = sc.nextInt();
        sc.nextLine();
        
        System.out.print("¿Es curso en línea? (s/n): ");
        boolean enLinea = sc.nextLine().equalsIgnoreCase("s");

        // Instanciación del objeto (POO)
        Matricula miMatricula = new Matricula(nom, ape, doc, dir, tel, sem, carreraElegida, enLinea);

        int creditos = 0;
        if (!enLinea) {
            System.out.print("Ingrese número de créditos perdidos: ");
            creditos = sc.nextInt();
        }

        // Ejecución de método void
        miMatricula.imprimirRecibo(creditos);
	}

}
