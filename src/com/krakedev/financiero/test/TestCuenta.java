package com.krakedev.financiero.test;

import com.krakedev.financiero.entidades.Cuenta;

public class TestCuenta {

	public static void main(String[] args) {
		Cuenta cuenta1 = new Cuenta("1000");
		cuenta1.imprimir();
	}

}