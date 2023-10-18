package lanzahilosyesperaqueterminen;

public class LanzaHilosYEsperaQueTerminen {

	public static void main(String[] args) {
		Thread h1 = new Thread(new Hilo("h1"));
		Thread h2 = new Thread(new Hilo("h2"));
		h1.start();
		h2.start();
		
		try {
			h1.join();
			h2.join();
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("El hilo principal ha finalizado");

	}

}