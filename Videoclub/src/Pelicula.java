
public class Pelicula {

	static enum TipoPeliculaFecha {
		NOVEDADES("Novedades"), SEMI_NOVEDADES("Semi Novedades"), ANTIGUAS("Antiguas");
		
		private String tipoPelicula;
		
		TipoPeliculaFecha(String tipoPelicula){
			this.tipoPelicula = tipoPelicula;
		}
		
		@Override
		public String toString() {
			return tipoPelicula;
		}
		
	}

	static int alquilerTotal;
	static double retrasoTotal;
	static int newCodePeli = 10000;
	
	private int codigo;
	private String titulo;
	private TipoPeliculaFecha tipoPelicula;
	private int alquilerDia[];
	private boolean alquilada;
	private Cliente cliente;

	
	public Pelicula(String titulo, Pelicula.TipoPeliculaFecha tipoPelicula) {
		super();
		this.codigo = newCodePeli;
		this.titulo = titulo;
		this.tipoPelicula = tipoPelicula;
		this.alquilada = false;
		this.cliente = null;
		
		this.alquilerDia = new int[2];
		
		newCodePeli++;
	}
	//Copy Constructor
	public Pelicula(Pelicula otra) {
		this.codigo = newCodePeli;
		this.titulo = otra.titulo;
		this.tipoPelicula = otra.tipoPelicula;
		this.alquilada = false;
		
		this.alquilerDia = new int[2];
		
		//newCodePeli++;
	}
	
	public int getCodigo() {
		return codigo;
	}
	
	
	public boolean isAlquilada() {
		return alquilada;
	}
	
	public void alquilar(boolean alquilar) {
		this.alquilada = alquilar;
		if(alquilar) setAlquiler();

	}
	
	public String getTitulo() {
		return titulo;
	}

	public int getDiasAlquiler() {
		return alquilerDia[1];
	}
	
	

	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public void pasarDia() {
		alquilerDia[1] -= 1;
//		IF(ALQUILERDIA[1] <= 0) {
//			THIS.ALQUILADA = FALSE;
//		}
	}

	public void setAlquiler() {
		if(this.tipoPelicula == TipoPeliculaFecha.NOVEDADES) {
			this.alquilerDia[0] = 3;
			this.alquilerDia[1] = 1;
		}
		else if(this.tipoPelicula == TipoPeliculaFecha.SEMI_NOVEDADES) {
			this.alquilerDia[0] = 2;
			this.alquilerDia[1] = 2;
		}
		else if(this.tipoPelicula == TipoPeliculaFecha.ANTIGUAS) {
			this.alquilerDia[0] = 1;
			this.alquilerDia[1] = 4;
		}else {
			this.alquilerDia[0] = 0;
			this.alquilerDia[1] = 0;
		}
		alquilerTotal += this.alquilerDia[0];
	}
	
	public void printValues() {
		System.out.print(
					"Codigo: " + this.codigo +
					" - Titulo: " + this.titulo + 
					" - Tipo Pelicula: " + this.tipoPelicula +
					" - Alquiler: " + this.alquilerDia[0] + " euros por cada " + this.alquilerDia[1] + " dia"
				);
		if(this.cliente !=null) System.out.print(" - Cliente-> Num: " + this.cliente.getNumeroSocio() + " - Nombre: " + this.cliente.getNombre());
		System.out.println();
	}

	public double cobrarRetraso(int diasRetraso) {
		double cobrar = 0;
		double multiplicadorDia = 0;
		if(this.tipoPelicula == TipoPeliculaFecha.NOVEDADES) 
			multiplicadorDia = 2;
		else if(this.tipoPelicula == TipoPeliculaFecha.SEMI_NOVEDADES) 
			multiplicadorDia = 1;

		else if(this.tipoPelicula == TipoPeliculaFecha.ANTIGUAS) 
			multiplicadorDia = 0.5;
		
		cobrar = multiplicadorDia * diasRetraso;
		return cobrar;
	}

	
}
