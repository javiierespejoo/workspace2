package ejercicio24;

import java.util.ArrayList;
import java.util.Scanner;

public class Empleado {
	Scanner teclado = new Scanner(System.in);
	
	private String dni, nombre, apellido1, apellido2;
	private int base, complementos;
	private float irpf;
	
	public Empleado(String dni, String nombre, String apellido1, String apellido2, int base, int complementos, float irpf) {
		this.dni = dni;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.base = base;
		this.complementos = complementos;
		this.irpf = irpf;
		
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public int getBase() {
		return base;
	}

	public void setBase(int base) {
		this.base = base;
	}

	public int getComplementos() {
		return complementos;
	}

	public void setComplementos(int complementos) {
		this.complementos = complementos;
	}

	public float getIrpf() {
		return irpf;
	}

	public void setIrpf(float irpf) {
		this.irpf = irpf;
	}

}
