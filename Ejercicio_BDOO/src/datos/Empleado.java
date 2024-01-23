package datos;

import java.sql.Date;
import java.util.Objects;

public class Empleado{
	private int id_emp;
	private String apellido;
	private String oficio;
	private Date fecha_alta;
	private float salario;
	private float comision;
	private Empleado jefe;
	private Departamento departamento;
	
	public Empleado(int id_emp, String apellido, String oficio, Date fecha_alta, float salario, float comision,
			Empleado jefe, Departamento departamento) {
		super();
		this.id_emp = id_emp;
		this.apellido = apellido;
		this.oficio = oficio;
		this.fecha_alta = fecha_alta;
		this.salario = salario;
		this.comision = comision;
		this.jefe = jefe;
		this.departamento = departamento;
	}

	public int getId_emp() {
		return id_emp;
	}

	public void setId_emp(int id_emp) {
		this.id_emp = id_emp;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getOficio() {
		return oficio;
	}

	public void setOficio(String oficio) {
		this.oficio = oficio;
	}

	public Date getFecha_alta() {
		return fecha_alta;
	}

	public void setFecha_alta(Date fecha_alta) {
		this.fecha_alta = fecha_alta;
	}

	public float getSalario() {
		return salario;
	}

	public void setSalario(float salario) {
		this.salario = salario;
	}

	public float getComision() {
		return comision;
	}

	public void setComision(float comision) {
		this.comision = comision;
	}

	public Empleado getJefe() {
		return jefe;
	}

	public void setJefe(Empleado jefe) {
		this.jefe = jefe;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	@Override
	public String toString() {
		return "Empleado [id_emp=" + id_emp + ", apellido=" + apellido + ", oficio=" + oficio + ", fecha_alta="
				+ fecha_alta + ", salario=" + salario + ", comision=" + comision + ", jefe=" + jefe + ", departamento="
				+ departamento + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(apellido, comision, departamento, fecha_alta, id_emp, jefe, oficio, salario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Empleado other = (Empleado) obj;
		return Objects.equals(apellido, other.apellido)
				&& Float.floatToIntBits(comision) == Float.floatToIntBits(other.comision)
				&& Objects.equals(departamento, other.departamento) && Objects.equals(fecha_alta, other.fecha_alta)
				&& id_emp == other.id_emp && Objects.equals(jefe, other.jefe) && Objects.equals(oficio, other.oficio)
				&& Float.floatToIntBits(salario) == Float.floatToIntBits(other.salario);
	}
	
	
	
} 

