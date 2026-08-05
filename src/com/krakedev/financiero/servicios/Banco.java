package com.krakedev.financiero.servicios;

import com.krakedev.financiero.entidades.Cliente;
import com.krakedev.financiero.entidades.Cuenta;

public class Banco {

	private int ultimoCodigo = 1000;

	public Banco() {
	}

	public int getUltimoCodigo() {
		return ultimoCodigo;
	}

	public void setUltimoCodigo(int ultimoCodigo) {
		this.ultimoCodigo = ultimoCodigo;
	}
	
	
	public Cuenta crearCuenta(Cliente cliente) {

		String codigoStr = ultimoCodigo + "";
		ultimoCodigo++;

		Cuenta cuenta = new Cuenta(codigoStr);
		cuenta.setPropietario(cliente);
		return cuenta;
	}
	
	public boolean depositar(double monto, Cuenta cuenta) {

		if (monto > 0) {
			cuenta.setSaldoActual(cuenta.getSaldoActual() + monto);
			return true;
		} else {
			return false;
		}
	}
	
	public boolean retirar(double monto, Cuenta cuenta) {
		if (monto <= 0) {
			return false;
		}
		if (monto > cuenta.getSaldoActual()) {
			return false;
		}
		cuenta.setSaldoActual(cuenta.getSaldoActual() - monto);
		return true;
	}
	
	public boolean transferir(double monto, Cuenta origen, Cuenta destino) {
		boolean retiroExitoso = retirar(monto, origen);
		if (!retiroExitoso) {
			return false;
		}
		boolean depositoExitoso = depositar(monto, destino);
		if (!depositoExitoso) {
			// revertir el retiro si el deposito fallara (caso defensivo)
			depositar(monto, origen);
			return false;
		}
		return true;
	}
}