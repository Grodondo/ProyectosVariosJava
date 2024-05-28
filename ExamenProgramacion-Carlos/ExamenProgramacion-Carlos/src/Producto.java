import java.time.LocalDate;
import java.util.Objects;

public class Producto implements Informacion{
	
	private String id;
	private String nombre;
	private double precio;
	private Estado estado;
	private LocalDate finReparacion;
	
	public Producto(String id, String nombre, double precio, Estado estado) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.precio = precio;
		this.estado = estado;
		this.finReparacion = null;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public LocalDate getFinReparacion() {
		return finReparacion;
	}
	public void setFinReparacion(LocalDate finReparacion) {
		this.finReparacion = finReparacion;
	}

	@Override
	public void mostrarInformacion() {
		System.out.println(id + " - " + nombre + ". Estado:  " + estado + " ");
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Producto other = (Producto) obj;
		return Objects.equals(id, other.id);
	}

	
}
