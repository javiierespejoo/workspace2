package hiloscooperantes;

public class HilosCooperantes {

	private static final int NUM_HILOS = 10;
	
	private static final int CUENTA_TOTAL = 10000;
	
	public static void main(String[] args) {
		Contador cont = new Contador();
		Thread[] hilos = new Thread[NUM_HILOS];
		
		for(int i = 0; i < NUM_HILOS; i++) {
			Thread th = new Thread(new Hilo(i, (CUENTA_TOTAL/NUM_HILOS), cont));
			th.start();
			
			hilos[i] = th;
		}
		
		for(Thread h : hilos) {
			try {
				h.join();
			}catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.printf("Cuenta global: %s\n", cont.getCuenta());
	}
	
}
