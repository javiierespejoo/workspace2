package hiloscooperantes;

public class Contador {

	private int cuenta = 0;

	synchronized public int getCuenta() {
		return cuenta;
	}
	
	synchronized public int incrementarCuenta() {
		this.cuenta++;
		return cuenta;
	}
}
