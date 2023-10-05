package ejercicio15;

import java.io.Serializable;

public class Empleados implements Serializable{
	private String nombre;
	private int sueldo;
	private int anoNacimiento;
	private int antiguedad;
	
	public Empleados(String nombre, int sueldo, int anoNacimiento, int antiguedad) {
		this.nombre = nombre;
		this.sueldo = sueldo;
		this.anoNacimiento = anoNacimiento;
		this.antiguedad = antiguedad;
	}
	
	@Override
	public String toString() {
		return "Empleado [Nombre: " + nombre + ", Sueldo: " + sueldo + ", Año de nacimiento: " + anoNacimiento + ", Antigüedad: " + antiguedad +"]";
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String  nombre) {
		this.nombre = nombre;
	}
	
	public int getSueldo() {
		return sueldo;
	}
	
	public void setSueldo(int sueldo) {
		this.sueldo = sueldo;
	}
	
	public int getAnoNacimiento() {
		return anoNacimiento;
	}
	
	public void setAnoNacimiento(int anoNacimiento) {
		this.anoNacimiento = anoNacimiento;
	}
	
	public int getAntiguedad() {
		return antiguedad;
	}
	
	public void setAntiguedad(int antiguedad) {
		this.antiguedad = antiguedad;
	}
}
