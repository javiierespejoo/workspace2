package datos;

import java.util.Objects;

public class Departamento{
	private int id_dep;
	private String nombre;
	private String localidad;
	 
	public Departamento(int id_dep, String nombre, String localidad) {
		super();
		this.id_dep = id_dep;
		this.nombre = nombre;
		this.localidad = localidad;
	}

	public int getId_dep() {
		return id_dep;
	}

	public void setId_dep(int id_dep) {
		this.id_dep = id_dep;
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

	@Override
	public String toString() {
		return "Departamento [id_dep=" + id_dep + ", nombre=" + nombre + ", localidad=" + localidad + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id_dep, localidad, nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Departamento other = (Departamento) obj;
		return id_dep == other.id_dep && Objects.equals(localidad, other.localidad)
				&& Objects.equals(nombre, other.nombre);
	}
	 
	
} 

