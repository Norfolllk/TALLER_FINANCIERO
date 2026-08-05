package com.krakedev.financiero.test;

import com.krakedev.financiero.entidades.Cliente;
import com.krakedev.financiero.entidades.Cuenta;
import com.krakedev.financiero.servicios.Banco;

public class TestCuenta {

	public static void main(String[] args) {
		Banco banco = new Banco();

		Cliente cliente1 = new Cliente("2026081001", "Ana", "Perez");
		Cuenta cuenta1 = banco.crearCuenta(cliente1);
		cuenta1.imprimir();

		Cliente cliente2 = new Cliente("2026081002", "Luis", "Gomez");
		Cuenta cuenta2 = banco.crearCuenta(cliente2);
		cuenta2.imprimir();
	}

}