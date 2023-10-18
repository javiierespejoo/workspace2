package hiloscooperantes;

public class Hilo implements Runnable{
	
	int numHilo, miParte, miCuenta = 0;
	private Contador cont;
	
	public int getMiCuenta() {
		return miCuenta;
	}
	
	Hilo(int numHilo, int miParte, Contador cont) {
		this.numHilo = numHilo;
		this.miParte = miParte;
		this.cont = cont;
	}
	
	public void run() {
		for(int i = 0; i < miParte; i++) {
			this.cont.incrementarCuenta();
			miCuenta++;
		}
		System.out.printf("Hilo %d terminado, cuenta: %d\n",numHilo,getMiCuenta());
	}

}
