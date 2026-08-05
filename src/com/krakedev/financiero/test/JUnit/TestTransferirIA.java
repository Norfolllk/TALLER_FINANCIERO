package com.krakedev.financiero.test.JUnit;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.krakedev.financiero.entidades.Cliente;
import com.krakedev.financiero.entidades.Cuenta;
import com.krakedev.financiero.servicios.Banco;

public class TestTransferirIA {

	@Test
	public void testTransferenciaValidaMueveElDinero() {
		Banco banco = new Banco();
		Cuenta origen = banco.crearCuenta(new Cliente("2026081030", "Ana", "Perez"));
		Cuenta destino = banco.crearCuenta(new Cliente("2026081031", "Luis", "Gomez"));
		banco.depositar(100.0, origen);

		boolean resultado = banco.transferir(40.0, origen, destino);

		assertTrue(resultado);
		assertEquals(60.0, origen.getSaldoActual(), 0.0001);
		assertEquals(40.0, destino.getSaldoActual(), 0.0001);
	}

	@Test
	public void testTransferenciaDelSaldoCompleto() {
		Banco banco = new Banco();
		Cuenta origen = banco.crearCuenta(new Cliente("2026081032", "Marta", "Diaz"));
		Cuenta destino = banco.crearCuenta(new Cliente("2026081033", "Pedro", "Lopez"));
		banco.depositar(75.0, origen);

		boolean resultado = banco.transferir(75.0, origen, destino);

		assertTrue(resultado);
		assertEquals(0, origen.getSaldoActual(), 0.0001);
		assertEquals(75.0, destino.getSaldoActual(), 0.0001);
	}

	@Test
	public void testTransferenciaFallaPorSaldoInsuficiente() {
		Banco banco = new Banco();
		Cuenta origen = banco.crearCuenta(new Cliente("2026081034", "Sofia", "Ramirez"));
		Cuenta destino = banco.crearCuenta(new Cliente("2026081035", "Carlos", "Vera"));
		banco.depositar(20.0, origen);

		boolean resultado = banco.transferir(50.0, origen, destino);

		assertFalse(resultado);
		assertEquals(20.0, origen.getSaldoActual(), 0.0001); // no debe cambiar
		assertEquals(0, destino.getSaldoActual(), 0.0001); // no debe cambiar
	}

	@Test
	public void testTransferenciaFallaConMontoCero() {
		Banco banco = new Banco();
		Cuenta origen = banco.crearCuenta(new Cliente("2026081036", "Elena", "Nunez"));
		Cuenta destino = banco.crearCuenta(new Cliente("2026081037", "Diego", "Torres"));
		banco.depositar(30.0, origen);

		boolean resultado = banco.transferir(0, origen, destino);

		assertFalse(resultado);
		assertEquals(30.0, origen.getSaldoActual(), 0.0001);
		assertEquals(0, destino.getSaldoActual(), 0.0001);
	}

	@Test
	public void testTransferenciaFallaConMontoNegativo() {
		Banco banco = new Banco();
		Cuenta origen = banco.crearCuenta(new Cliente("2026081038", "Jorge", "Castro"));
		Cuenta destino = banco.crearCuenta(new Cliente("2026081039", "Paula", "Rios"));
		banco.depositar(30.0, origen);

		boolean resultado = banco.transferir(-10.0, origen, destino);

		assertFalse(resultado);
		assertEquals(30.0, origen.getSaldoActual(), 0.0001);
		assertEquals(0, destino.getSaldoActual(), 0.0001);
	}

	@Test
	public void testVariasTransferenciasConsecutivas() {
		Banco banco = new Banco();
		Cuenta cuentaA = banco.crearCuenta(new Cliente("2026081040", "Vero", "Salas"));
		Cuenta cuentaB = banco.crearCuenta(new Cliente("2026081041", "Nico", "Paredes"));
		Cuenta cuentaC = banco.crearCuenta(new Cliente("2026081042", "Ines", "Molina"));
		banco.depositar(200.0, cuentaA);

		banco.transferir(80.0, cuentaA, cuentaB);
		banco.transferir(30.0, cuentaB, cuentaC);

		assertEquals(120.0, cuentaA.getSaldoActual(), 0.0001);
		assertEquals(50.0, cuentaB.getSaldoActual(), 0.0001);
		assertEquals(30.0, cuentaC.getSaldoActual(), 0.0001);
	}
}
