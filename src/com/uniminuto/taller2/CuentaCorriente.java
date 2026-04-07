package com.uniminuto.taller2;

public class CuentaCorriente extends CuentaBancaria {
	
	public CuentaCorriente(String numeroCuenta, double saldoInicial, Client titular) {
        super(numeroCuenta, saldoInicial, titular);
    }
	
	@Override
    public void depositar(double monto) {
        double comision = 0;
        if (monto < 500000) comision = 7000;
        else if (monto < 2000000) comision = 5000 + (monto * 0.02);
        else if (monto <= 10000000) comision = 4000 + (monto * 0.02);
        else comision = monto * 0.033;

        this.saldo += monto;
        this.saldo -= comision;
        System.out.println("Depósito a Corriente exitoso. Comisión descontada: $" + comision);
    }

    @Override
    public void cierreMes() {
        double mantenimiento = this.saldo * 0.015; 
        this.saldo -= mantenimiento;
        System.out.println("Cierre Cuenta Corriente " + numeroCuenta + ". Mantenimiento cobrado: $" + mantenimiento);
    }

    
    public void emitirCheque(double monto) {
        if (this.saldo >= (monto + 3000)) {
            this.saldo -= (monto + 3000);
            System.out.println("Cheque emitido por $" + monto + ". Cobro de $3000 aplicado.");
        } else {
            System.out.println("Fondos insuficientes para emitir el cheque.");
        }
    }

}
