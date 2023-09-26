package ejerEstudiante;

public class Estudiante{
	private String nombre, matricula;
	private int edad;
	
	public Estudiante(String nombre, int edad, String matricula) {
		this.nombre = nombre;
		this.edad = edad;
		this.matricula = matricula;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setEdad(int edad) {
		this.edad = edad;
	}
	
	public int getEdad() {
		return edad;
	}
	
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public String getMatricula() {
		return matricula;
	}
}
