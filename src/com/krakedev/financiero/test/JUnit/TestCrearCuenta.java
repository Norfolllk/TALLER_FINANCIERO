package com.krakedev.financiero.test.JUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import com.krakedev.financiero.entidades.Cliente;
import com.krakedev.financiero.entidades.Cuenta;
import com.krakedev.financiero.servicios.Banco;

public class TestCrearCuenta {

	// Caso 1: Se crea la primera cuenta. Debe recibir el código inicial (1000),
	// tipo "A" (Ahorros), saldo en 0, y el cliente correcto como propietario.
	@Test
	public void testCrearPrimeraCuenta() {
		Banco banco = new Banco();
		Cliente cliente = new Cliente("2026081001", "Ana", "Perez");

		Cuenta cuenta = banco.crearCuenta(cliente);

		assertEquals("1000", cuenta.getId());
		assertEquals("A", cuenta.getTipo());
		assertEquals(0, cuenta.getSaldoActual(), 0.0001);
		assertEquals(cliente, cuenta.getPropietario());
	}

	// Caso 2: Se crean varias cuentas seguidas. Los códigos deben ser
	// consecutivos: 1000, 1001, 1002...
	@Test
	public void testCodigosConsecutivos() {
		Banco banco = new Banco();
		Cliente cliente1 = new Cliente("2026081002", "Luis", "Gomez");
		Cliente cliente2 = new Cliente("2026081003", "Marta", "Diaz");
		Cliente cliente3 = new Cliente("2026081004", "Pedro", "Lopez");

		Cuenta cuenta1 = banco.crearCuenta(cliente1);
		Cuenta cuenta2 = banco.crearCuenta(cliente2);
		Cuenta cuenta3 = banco.crearCuenta(cliente3);

		assertEquals("1000", cuenta1.getId());
		assertEquals("1001", cuenta2.getId());
		assertEquals("1002", cuenta3.getId());
	}

}