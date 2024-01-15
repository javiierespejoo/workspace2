package datos;

import java.util.Date;
import java.util.Objects;

public class Jugador {
	
	
	private String nombre;
	private String apellidos;
	private String deporte;
	private Date fechaNac;
	
	public Jugador(String nombre, String apellidos, String deporte, Date fechaNac) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.deporte = deporte;
		this.fechaNac = fechaNac;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getDeporte() {
		return deporte;
	}
	public void setDeporte(String deporte) {
		this.deporte = deporte;
	}
	public Date getFechaNac() {
		return fechaNac;
	}
	public void setFechaNac(Date fechaNac) {
		this.fechaNac = fechaNac;
	}
	
	@Override
	public String toString() {
		return "Jugador [nombre=" + nombre + ", apellidos=" + apellidos + ", deporte=" + deporte + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(apellidos, deporte, nombre);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Jugador other = (Jugador) obj;
		return Objects.equals(apellidos, other.apellidos) && Objects.equals(deporte, other.deporte)
				&& Objects.equals(nombre, other.nombre);
	}
}
