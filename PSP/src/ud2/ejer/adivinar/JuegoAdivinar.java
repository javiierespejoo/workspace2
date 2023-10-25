package ud2.ejer.adivinar;

public class JuegoAdivinar {

	public static void main(String[] args) {
		
		HiloAdivinador[] arrayHilos = new HiloAdivinador[10];
		NumeroDesconocido n = new NumeroDesconocido();
		
		for(int i = 0; i < 10; i++) {
			HiloAdivinador h = new HiloAdivinador(n);
			arrayHilos[i] = h;
			h.start();
		}
		
		for(HiloAdivinador ha: arrayHilos) {
			try {
				ha.join();
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
