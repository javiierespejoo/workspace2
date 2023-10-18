package lanzahilosyesperaqueterminen;

import java.util.Random;

public class Hilo implements Runnable{
	
	private String nombre;
	
	Hilo(String nombre){
		this.nombre = nombre;
	}
	
	public void run() {
		System.out.println("Hola, soy el hilo: " + this.nombre);
		Random r = new Random();
		for(int i = 0; i < 5; i++) {
			int pausa = 10 + r.nextInt(500-10);
			System.out.printf("El hilo %s hace una pausa de %d ms\n", this.nombre, pausa);
			
			try {
				Thread.sleep(pausa);
			}catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.printf("El hilo %s ha terminado\n", this.nombre);
	}
}
