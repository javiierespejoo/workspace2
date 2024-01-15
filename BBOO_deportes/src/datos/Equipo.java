package datos;

import java.util.Arrays;
import java.util.Objects;
import java.util.Set;

public class Equipo {
	
	private String nombre;
	private String categoria;
	private Set<Jugador> jugadores;
	
	
	
	public Equipo(String nombre, String categoria, Set<Jugador> jugadores) {
		super();
		this.nombre = nombre;
		this.categoria = categoria;
		this.jugadores = jugadores;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public Set<Jugador> getJugadores() {
		return jugadores;
	}
	public void setJugadores(Set<Jugador> jugadores) {
		this.jugadores = jugadores;
	}
	

	@Override
	public String toString() {
		return "Equipo [nombre=" + nombre + ", categoria=" + categoria + ", jugadores=" + jugadores + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(categoria, jugadores, nombre);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Equipo other = (Equipo) obj;
		return Objects.equals(categoria, other.categoria) && Objects.equals(jugadores, other.jugadores)
				&& Objects.equals(nombre, other.nombre);
	}

	
	
	
}
