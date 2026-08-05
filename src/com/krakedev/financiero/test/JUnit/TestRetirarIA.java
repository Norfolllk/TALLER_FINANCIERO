package com.krakedev.financiero.test.JUnit;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.krakedev.financiero.entidades.Cliente;
import com.krakedev.financiero.entidades.Cuenta;
import com.krakedev.financiero.servicios.Banco;

public class TestRetirarIA {

	@Test
	public void testRetiroValidoDisminuyeElSaldo() {
		Banco banco = new Banco();
		Cliente cliente = new Cliente("2026081020", "Ana", "Perez");
		Cuenta cuenta = banco.crearCuenta(cliente);
		banco.depositar(100.0, cuenta);

		boolean resultado = banco.retirar(40.0, cuenta);

		assertTrue(resultado);
		assertEquals(60.0, cuenta.getSaldoActual(), 0.0001);
	}

	@Test
	public void testRetiroDelSaldoExactoDejaCuentaEnCero() {
		Banco banco = new Banco();
		Cliente cliente = new Cliente("2026081021", "Luis", "Gomez");
		Cuenta cuenta = banco.crearCuenta(cliente);
		banco.depositar(80.0, cuenta);

		boolean resultado = banco.retirar(80.0, cuenta);

		assertTrue(resultado);
		assertEquals(0, cuenta.getSaldoActual(), 0.0001);
	}

	@Test
	public void testRetiroMayorAlSaldoDisponible() {
		Banco banco = new Banco();
		Cliente cliente = new Cliente("2026081022", "Marta", "Diaz");
		Cuenta cuenta = banco.crearCuenta(cliente);
		banco.depositar(30.0, cuenta);

		boolean resultado = banco.retirar(50.0, cuenta);

		assertFalse(resultado);
		assertEquals(30.0, cuenta.getSaldoActual(), 0.0001); // no debe cambiar
	}

	@Test
	public void testRetiroConMontoCero() {
		Banco banco = new Banco();
		Cliente cliente = new Cliente("2026081023", "Pedro", "Lopez");
		Cuenta cuenta = banco.crearCuenta(cliente);
		banco.depositar(50.0, cuenta);

		boolean resultado = banco.retirar(0, cuenta);

		assertFalse(resultado);
		assertEquals(50.0, cuenta.getSaldoActual(), 0.0001);
	}

	@Test
	public void testRetiroConMontoNegativo() {
		Banco banco = new Banco();
		Cliente cliente = new Cliente("2026081024", "Sofia", "Ramirez");
		Cuenta cuenta = banco.crearCuenta(cliente);
		banco.depositar(50.0, cuenta);

		boolean resultado = banco.retirar(-10.0, cuenta);

		assertFalse(resultado);
		assertEquals(50.0, cuenta.getSaldoActual(), 0.0001);
	}

	@Test
	public void testRetiroSinDepositoPrevio() {
		Banco banco = new Banco();
		Cliente cliente = new Cliente("2026081025", "Carlos", "Vera");
		Cuenta cuenta = banco.crearCuenta(cliente); // saldo en 0

		boolean resultado = banco.retirar(10.0, cuenta);

		assertFalse(resultado);
		assertEquals(0, cuenta.getSaldoActual(), 0.0001);
	}

	@Test
	public void testRetiroNoAfectaOtrasCuentas() {
		Banco banco = new Banco();
		Cliente cliente1 = new Cliente("2026081026", "Elena", "Nunez");
		Cliente cliente2 = new Cliente("2026081027", "Diego", "Torres");
		Cuenta cuenta1 = banco.crearCuenta(cliente1);
		Cuenta cuenta2 = banco.crearCuenta(cliente2);
		banco.depositar(100.0, cuenta1);
		banco.depositar(100.0, cuenta2);

		banco.retirar(60.0, cuenta1);

		assertEquals(40.0, cuenta1.getSaldoActual(), 0.0001);
		assertEquals(100.0, cuenta2.getSaldoActual(), 0.0001);
	}
}
