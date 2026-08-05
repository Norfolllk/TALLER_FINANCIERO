package com.krakedev.financiero.test.JUnit;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.krakedev.financiero.entidades.Cliente;
import com.krakedev.financiero.entidades.Cuenta;
import com.krakedev.financiero.servicios.Banco;

public class TestDepositarIA {

	@Test
	public void testDepositoValidoAumentaElSaldo() {
		Banco banco = new Banco();
		Cliente cliente = new Cliente("2026081010", "Ana", "Perez");
		Cuenta cuenta = banco.crearCuenta(cliente);

		boolean resultado = banco.depositar(100.0, cuenta);

		assertTrue(resultado);
		assertEquals(100.0, cuenta.getSaldoActual(), 0.0001);
	}

	@Test
	public void testDepositosAcumuladosSumanCorrectamente() {
		Banco banco = new Banco();
		Cliente cliente = new Cliente("2026081011", "Luis", "Gomez");
		Cuenta cuenta = banco.crearCuenta(cliente);

		banco.depositar(50.0, cuenta);
		banco.depositar(25.5, cuenta);

		assertEquals(75.5, cuenta.getSaldoActual(), 0.0001);
	}

	@Test
	public void testDepositoConMontoCero() {
		Banco banco = new Banco();
		Cliente cliente = new Cliente("2026081012", "Marta", "Diaz");
		Cuenta cuenta = banco.crearCuenta(cliente);

		boolean resultado = banco.depositar(0, cuenta);

		assertFalse(resultado);
		assertEquals(0, cuenta.getSaldoActual(), 0.0001);
	}

	@Test
	public void testDepositoConMontoNegativo() {
		Banco banco = new Banco();
		Cliente cliente = new Cliente("2026081013", "Pedro", "Lopez");
		Cuenta cuenta = banco.crearCuenta(cliente);

		boolean resultado = banco.depositar(-20.0, cuenta);

		assertFalse(resultado);
		assertEquals(0, cuenta.getSaldoActual(), 0.0001);
	}

	@Test
	public void testDepositoInvalidoNoAlteraSaldoPrevio() {
		Banco banco = new Banco();
		Cliente cliente = new Cliente("2026081014", "Sofia", "Ramirez");
		Cuenta cuenta = banco.crearCuenta(cliente);

		banco.depositar(200.0, cuenta);
		banco.depositar(-50.0, cuenta); // invalido, no debe cambiar el saldo

		assertEquals(200.0, cuenta.getSaldoActual(), 0.0001);
	}

	@Test
	public void testDepositoNoAfectaOtrasCuentas() {
		Banco banco = new Banco();
		Cliente cliente1 = new Cliente("2026081015", "Carlos", "Vera");
		Cliente cliente2 = new Cliente("2026081016", "Elena", "Nunez");
		Cuenta cuenta1 = banco.crearCuenta(cliente1);
		Cuenta cuenta2 = banco.crearCuenta(cliente2);

		banco.depositar(300.0, cuenta1);

		assertEquals(300.0, cuenta1.getSaldoActual(), 0.0001);
		assertEquals(0, cuenta2.getSaldoActual(), 0.0001);
	}
}
