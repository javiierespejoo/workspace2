package ejercicio20;

public class Departamentos {
	
	private int numero;
	private String nombre; // 15 caracteres
	private String localidad; // 15 caracteres
	
	public Departamentos(int numero, String nombre, String localidad) {
		super();
		this.numero = numero;
		this.nombre = nombre;
		this.localidad = localidad;
	}
	
	public Departamentos() {
		
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getLocalidad() {
		return localidad;
	}
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	
}
