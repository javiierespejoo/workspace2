package ud2.ejer.adivinar;

import java.util.Random;

public class NumeroDesconocido {

	private int numero;
	private boolean adivinado = false;
	
	public NumeroDesconocido() {
		Random r = new Random();
		this.numero = r.nextInt(101);
	}
	
	public synchronized int candidato(int propuesta, long numHilo) {
		
		if(this.adivinado) {
			return -1;
		}
		if(this.numero == propuesta) {
			System.out.println("El hilo " + numHilo + " ha encontrado el numero y ha finalizado");
			this.adivinado = true;
			return 1;
		}
		
		return 0;
	}
	
	
	
	

}
