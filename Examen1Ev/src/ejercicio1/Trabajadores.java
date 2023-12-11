package ejercicio1;

public class Trabajadores {

	private int id;
	private String nombre;
	private double salario;
	private int id_sindicato;
	
	public Trabajadores() {
		
	}
	
	public Trabajadores(int id, String nombre, double salario, int id_sindicato) {
		this.id = id;
		this.nombre = nombre;
		this.salario = salario;
		this.id_sindicato = id_sindicato;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public int getId_sindicato() {
		return id_sindicato;
	}

	public void setId_sindicato(int id_sindicato) {
		this.id_sindicato = id_sindicato;
	}
	
	

}
