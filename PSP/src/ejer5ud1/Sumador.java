package ejer5ud1;

public class Sumador {

	public static void main(String[] args) {
		
		if (args.length < 2) {
			System.out.println("Hay que introducir 2 sumandos");
		} else {
			int a = Integer.parseInt(args[0]);
			int b = Integer.parseInt(args[1]);
			System.out.println(suma(a,b));
		}

	}
	
	public static int suma(int a, int b) {
		int resultado = 0;
		int aux = 0;
		if(a > b) {
			aux = b;
			b = a;
			a = aux;
		}
		
		for (int i = a; i <= b; i++) {
			resultado += i;
		}
		return resultado;
	}

}
