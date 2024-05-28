
public class CdMusica {

	private int codigo;
	private String nombreCreador;
	private String titulo;
	private double precio;
	static int newCodeCd = 20000;
	static double gananciasTotales;
	
	public CdMusica(String nombreCreador, String titulo, double precio) {
		super();
		this.codigo = newCodeCd;
		this.nombreCreador = nombreCreador;
		this.titulo = titulo;
		this.precio = precio;
		
		newCodeCd++;
	}
	//Copy Constructor
	public CdMusica(CdMusica otro) {
		super();
		this.codigo = newCodeCd;
		this.nombreCreador = otro.nombreCreador;
		this.titulo = otro.titulo;
		this.precio = otro.precio;
		
		newCodeCd++;
	}
	
	public int getCodigo() {
		return codigo;
	}
	
	
	
	public String getNombreCreador() {
		return nombreCreador;
	}

	public String getTitulo() {
		return titulo;
	}

	public double getPrecio() {
		return precio;
	}

	
	public void printValues() {
		System.out.println(
					"Codigo: " + this.codigo +
					" - Nombre Autor: " + this.nombreCreador + 
					" - Titulo: " + this.titulo + 
					" - precio: " + this.precio
					);
	}
	
	
}
