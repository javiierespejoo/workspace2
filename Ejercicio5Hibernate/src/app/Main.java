package app;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner teclado = new Scanner(System.in);
		String opcion = "5";
		int op = Integer.parseInt(opcion);
		
		while(op != 0) {
			System.out.println("Elige una opcion: ");
			System.out.println("0.- Salir.");
			System.out.println("1.- Modificar el precio de todos los productos para que incluyan el 21% de IVA.");
			System.out.println("2.- Eliminar las ventas realizadas por un cliente a elegir");
			while(true) {
				try {
					opcion = teclado.nextLine();
					op = Integer.parseInt(opcion);
					break;
				} catch (NumberFormatException e) {
					System.err.println("Error: Debes introducir un numero entero");
				}
			}
			
			switch(op) {
			case 0:
				
			break;
			case 1:
				
			break;
			}
		}
		
		

	}
	
	public void ModificarPrecio() {
		
	}

}
