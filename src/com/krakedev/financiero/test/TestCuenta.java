package com.krakedev.financiero.test;

import com.krakedev.financiero.entidades.Cliente;
import com.krakedev.financiero.entidades.Cuenta;
import com.krakedev.financiero.servicios.Banco;

public class TestCuenta {
	public static void main(String[] args) {

		System.out.println("--- Prueba de composicion (Cuenta - Cliente) ---");
		Cuenta cuentaSinCliente = new Cuenta("9999");
		cuentaSinCliente.imprimir();
		System.out.println();
		
		System.out.println("--- Prueba de crearCuenta ---");
		Banco banco = new Banco();
		Cliente cliente1 = new Cliente("2026081001", "Ana", "Perez");
		Cuenta cuenta1 = banco.crearCuenta(cliente1);
		System.out.println("Cuenta creada para cliente1, id -> " + cuenta1.getId()
				+ " , tipo -> " + cuenta1.getTipo()
				+ " , saldo inicial -> " + cuenta1.getSaldoActual());
		cuenta1.imprimir();

		Cliente cliente2 = new Cliente("2026081002", "Luis", "Gomez");
		Cuenta cuenta2 = banco.crearCuenta(cliente2);
		System.out.println("Cuenta creada para cliente2, id -> " + cuenta2.getId()
				+ " , tipo -> " + cuenta2.getTipo()
				+ " , saldo inicial -> " + cuenta2.getSaldoActual());
		cuenta2.imprimir();
		System.out.println();
		
		System.out.println("--- Pruebas de deposito ---");
		boolean depositoValido = banco.depositar(150.0, cuenta1);
		
		System.out.println("Deposito de 150.0 en cuenta1 -> " + depositoValido);
		cuenta1.imprimir();
		boolean depositoInvalido = banco.depositar(-50.0, cuenta2);
		
		System.out.println("Deposito de -50.0 en cuenta2 -> " + depositoInvalido);
		cuenta2.imprimir();
		System.out.println();
		
		System.out.println("--- Pruebas de retiro ---");
		boolean retiroValido = banco.retirar(100.0, cuenta1);
		
		System.out.println("Retiro de 100.0 en cuenta1 -> " + retiroValido);
		cuenta1.imprimir();
		boolean retiroExcedeSaldo = banco.retirar(1000.0, cuenta1);
		
		System.out.println("Retiro de 1000.0 en cuenta1 -> " + retiroExcedeSaldo);
		cuenta1.imprimir();
		System.out.println();
		
		System.out.println("--- Pruebas de transferencia ---");
		boolean transferenciaValida = banco.transferir(30.0, cuenta1, cuenta2);
		
		System.out.println("Transferencia de 30.0 de cuenta1 a cuenta2 -> " + transferenciaValida);
		cuenta1.imprimir();
		cuenta2.imprimir();
		boolean transferenciaFallida = banco.transferir(999.0, cuenta1, cuenta2);
		
		System.out.println("Transferencia de 999.0 de cuenta1 a cuenta2 -> " + transferenciaFallida);
		cuenta1.imprimir();
		cuenta2.imprimir();
	}
}