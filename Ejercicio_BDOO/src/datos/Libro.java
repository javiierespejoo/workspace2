package datos;



import java.util.Objects;
import java.util.Set;

public class Libro {
	private String titulo;
	private int anyo;
	private String editorial;
	private int paginas;
	private Set<Autor>autores;
	
	public Libro(String titulo, int anyo, String editorial, int paginas, Set<Autor> autores) {
		super();
		this.titulo = titulo;
		this.anyo = anyo;
		this.editorial = editorial;
		this.paginas = paginas;
		this.autores = autores;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getAño() {
		return anyo;
	}

	public void setAño(int año) {
		this.anyo = año;
	}

	public String getEditorial() {
		return editorial;
	}

	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}

	public int getPaginas() {
		return paginas;
	}

	public void setPaginas(int paginas) {
		this.paginas = paginas;
	}

	public Set<Autor> getAutores() {
		return autores;
	}

	public void setAutores(Set<Autor> autores) {
		this.autores = autores;
	}

	@Override
	public String toString() {
		return "Libro [titulo=" + titulo + ", año=" + anyo + ", editorial=" + editorial + ", paginas=" + paginas
				+ ", autores=" + autores + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(autores, anyo, editorial, paginas, titulo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Libro other = (Libro) obj;
		return Objects.equals(autores, other.autores) && anyo == other.anyo && Objects.equals(editorial, other.editorial)
				&& paginas == other.paginas && Objects.equals(titulo, other.titulo);
	}
	
	
} 
