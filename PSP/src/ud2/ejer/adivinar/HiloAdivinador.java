package ud2.ejer.adivinar;

import java.util.Random;

public class HiloAdivinador extends Thread{
	
	NumeroDesconocido juego;
	
	public HiloAdivinador(NumeroDesconocido n) {
		this.juego = n;
	}
	
	public void run() {
		Random r = new Random();
		int resultado = 0;
		
		while(true) {
			int numero = r.nextInt(101);
		
			resultado = this.juego.candidato(numero, this.getId());
		
			if(resultado == 1) {
				//System.out.printf("El hilo %d ha adivinado el numero\n", this.getId());
				return;
			}
			if(resultado == -1) {
				System.out.printf("El hilo %d ha finalizado\n", this.getId());
				return;
			}
		}
		
	}

}
