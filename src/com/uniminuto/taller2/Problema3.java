package com.uniminuto.taller2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Problema3 {
	
	static List<CuentaBancaria> baseDeDatosCuentas = new ArrayList<>();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner leer = new Scanner(System.in);
        int opcion = 0;

        do {
            System.out.println("\n=== SISTEMA BANCARIO ===");
            System.out.println("1. Apertura de Cuentas");
            System.out.println("2. Transferencias");
            System.out.println("3. Cajero Automático");
            System.out.println("4. Cierre de Mes (Estado de Cuenta)");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = leer.nextInt();

            switch (opcion) {
                case 1:
                    abrirCuenta(leer);
                    break;
                case 2:
                    realizarTransferencia(leer);
                    break;
                case 3:
                    usarCajero(leer);
                    break;
                case 4:
                    ejecutarCierreMes();
                    break;
                case 5:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 5);
        
        leer.close();
	}
	
	private static void abrirCuenta(Scanner leer) {
        System.out.println("\n--- APERTURA DE CUENTA ---");
        System.out.print("Nombres: "); String nombres = leer.nextLine();
        System.out.print("Apellidos: "); String apellidos = leer.nextLine();
        System.out.print("Edad: "); int edad = leer.nextInt();
        leer.nextLine();

        String representante = "";
        if (edad < 18) {
            System.out.print("Nombres y apellidos del representante legal: ");
            representante = leer.nextLine(); // 
        }

        Client nuevoCliente = new Client(nombres, apellidos, edad, representante);

        System.out.println("Tipo de cuenta (1. Ahorro | 2. Corriente): ");
        int tipo = leer.nextInt();
        leer.nextLine();

        System.out.print("Número de cuenta a asignar: ");
        String numCuenta = leer.nextLine();

        System.out.print("Monto de apertura: $");
        double montoApertura = leer.nextDouble();

        if (tipo == 2) {
            if (montoApertura < 200000) {
                System.out.println("ERROR: La cuenta corriente exige un mínimo de $200.000 para apertura.");
                return;
            }
            baseDeDatosCuentas.add(new CuentaCorriente(numCuenta, montoApertura, nuevoCliente));
            System.out.println("Cuenta Corriente creada exitosamente.");
        } else { // Ahorros 
            baseDeDatosCuentas.add(new CuentaAhorro(numCuenta, montoApertura, nuevoCliente));
            System.out.println("Cuenta de Ahorros creada exitosamente.");
        }
    }

    private static void realizarTransferencia(Scanner leer) {
        System.out.print("Número de cuenta ORIGEN: ");
        String numOrigen = leer.next();
        System.out.print("Número de cuenta DESTINO: ");
        String numDestino = leer.next();
        System.out.print("Monto a transferir: $");
        double monto = leer.nextDouble();

        CuentaBancaria origen = buscarCuenta(numOrigen);
        CuentaBancaria destino = buscarCuenta(numDestino);

        if (origen != null && destino != null) {
            if (origen.retirarCajero(monto, true)) {
                System.out.println("Transfiriendo fondos...");
                destino.depositar(monto);
                System.out.println("Transferencia completada.");
            }
        } else {
            System.out.println("Alguna de las cuentas no existe.");
        }
    }

    private static void usarCajero(Scanner leer) {
        System.out.print("Número de cuenta: ");
        String numCuenta = leer.next();
        System.out.print("Monto a retirar: $");
        double monto = leer.nextDouble();
        System.out.print("¿Es cajero del mismo banco? (true/false): ");
        boolean mismoBanco = leer.nextBoolean();

        CuentaBancaria cuenta = buscarCuenta(numCuenta);
        if (cuenta != null) {
            cuenta.retirarCajero(monto, mismoBanco);
        } else {
            System.out.println("Cuenta no encontrada.");
        }
    }

    private static void ejecutarCierreMes() {
        System.out.println("\n--- EJECUTANDO CIERRE DE MES ---");
        for (CuentaBancaria cuenta : baseDeDatosCuentas) {
            cuenta.cierreMes();
            cuenta.imprimirEstado();
        }
        System.out.println("Cierre completado.");
    }

    private static CuentaBancaria buscarCuenta(String numero) {
        for (CuentaBancaria cuenta : baseDeDatosCuentas) {
            if (cuenta.getNumeroCuenta().equals(numero)) {
                return cuenta;
            }
        }
        return null;
    }

}
