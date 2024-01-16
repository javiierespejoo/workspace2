package datos;
// Generated 11 ene 2024 12:21:02 by Hibernate Tools 6.0.2.Final

import java.util.HashSet;
import java.util.Set;

/**
 * Cliente generated by hbm2java
 */
public class Cliente implements java.io.Serializable {

	private int id;
	private String nombre;
	private String direccion;
	private String poblacion;
	private String telef;
	private String nif;
	private Set ventas = new HashSet(0);

	public Cliente() {
	}

	public Cliente(int id) {
		this.id = id;
	}

	public Cliente(int id, String nombre, String direccion, String poblacion, String telef, String nif, Set ventas) {
		this.id = id;
		this.nombre = nombre;
		this.direccion = direccion;
		this.poblacion = poblacion;
		this.telef = telef;
		this.nif = nif;
		this.ventas = ventas;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getPoblacion() {
		return this.poblacion;
	}

	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
	}

	public String getTelef() {
		return this.telef;
	}

	public void setTelef(String telef) {
		this.telef = telef;
	}

	public String getNif() {
		return this.nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public Set getVentas() {
		return this.ventas;
	}

	public void setVentas(Set ventas) {
		this.ventas = ventas;
	}

}