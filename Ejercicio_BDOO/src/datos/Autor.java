package datos;

import java.util.List;
import java.util.Objects;

public class Autor {
	private String nombre;
	private String apellido;
	private String nacionalidad;
	private int edad;
	private List<Libro> libros;
	
	public Autor(String nombre, String apellido, String nacionalidad, int edad, List<Libro> libros) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.nacionalidad = nacionalidad;
		this.edad = edad;
		this.libros = libros;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public List<Libro> getLibros() {
		return libros;
	}

	public void setLibros(List<Libro> libros) {
		this.libros = libros;
	}

	@Override
	public String toString() {
		return "Autor [nombre=" + nombre + ", apellido=" + apellido + ", nacionalidad=" + nacionalidad + ", edad="
				+ edad + ", libros=" + libros + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(apellido, edad, libros, nacionalidad, nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Autor other = (Autor) obj;
		return Objects.equals(apellido, other.apellido) && edad == other.edad && Objects.equals(libros, other.libros)
				&& Objects.equals(nacionalidad, other.nacionalidad) && Objects.equals(nombre, other.nombre);
	}
	
	
}
