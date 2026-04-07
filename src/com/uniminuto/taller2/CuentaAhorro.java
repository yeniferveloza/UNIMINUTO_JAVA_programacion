package com.uniminuto.taller2;

public class CuentaAhorro extends CuentaBancaria {
	
	public CuentaAhorro(String numeroCuenta, double saldoInicial, Client titular) {
        super(numeroCuenta, saldoInicial, titular);
    }

    @Override
    public void depositar(double monto) {
        double comision = 0; 
        if (monto >= 500000 && monto < 2000000) comision = 3000 + (monto * 0.01);
        else if (monto >= 2000000 && monto <= 10000000) comision = 2000 + (monto * 0.005);
        else if (monto > 10000000 && monto < 100000000) comision = monto * 0.018;
        else if (monto >= 100000000) comision = monto * 0.02;

        this.saldo += monto;
        this.saldo -= comision;
        System.out.println("Depósito a Ahorros exitoso. Comisión descontada: $" + comision);
    }

    @Override
    public void cierreMes() {
        double rendimiento = this.saldo * (0.022 / 12);
        this.saldo += rendimiento;
        System.out.println("Cierre Cuenta Ahorro " + numeroCuenta + ". Rendimiento abonado: $" + String.format("%.2f", rendimiento));
    }

}
