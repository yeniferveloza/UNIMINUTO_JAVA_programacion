package com.uniminuto.taller2;

public abstract class CuentaBancaria {
	
	protected String numeroCuenta;
    protected double saldo;
    protected Client titular;
    
    public CuentaBancaria(String numeroCuenta, double saldoInicial, Client titular) {
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldoInicial;
        this.titular = titular;
    }
    
    public String getNumeroCuenta() {
    		return numeroCuenta;
    	}
    
    public double getSaldo() {
    		return saldo;
    	}
    
    public abstract void depositar(double monto);
    public abstract void cierreMes();
    
    public boolean retirarCajero(double monto, boolean cajeroPropio) {
        double comision = cajeroPropio ? 0 : 4500; 
        if (this.saldo >= (monto + comision)) {
            this.saldo -= (monto + comision);
            System.out.println("Retiro exitoso. Comisión de cajero: $" + comision);
            return true;
        } else {
            System.out.println("Fondos insuficientes para el retiro y la comisión.");
            return false;
        }
    }
    
    public void imprimirEstado() {
        System.out.println("Cuenta: " + numeroCuenta + " | Titular: " + titular.getNombreCompleto() + " | Saldo: $" + String.format("%.2f", saldo));
    }

}
