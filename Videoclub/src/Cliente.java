import java.util.ArrayList;

public class Cliente {

	static int numeroSocioGlobal=100;
	
	private String nombre;
	private String apellidos;
	private String dni;
	private int numeroSocio;
	private ArrayList<Pelicula> PeliculasAlquiladas= new ArrayList<Pelicula>();
	private ArrayList<Pelicula> HistoricoPeliculas= new ArrayList<Pelicula>();
	
	public Cliente(String nombre, String apellidos, String dni) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.dni = dni;
		
		this.numeroSocio = numeroSocioGlobal;
		numeroSocioGlobal++; 
	}

	public String getNombre() {
		return nombre;
	}
	
	public String getApellidos() {
		return apellidos;
	}

	public String getDni() {
		return dni;
	}

	public int getNumeroSocio() {
		return numeroSocio;
	}
	
	public void addPelicula(Pelicula pelicula) {
		PeliculasAlquiladas.add(pelicula);
		pelicula.setCliente(this);
	}
	
	//Eliminacion del alquilaje de la pelicula
	public void deletePelicula(Pelicula pelicula) {
		HistoricoPeliculas.add(pelicula);
		for(int i=0; i<PeliculasAlquiladas.size(); i++) {
			if(PeliculasAlquiladas.get(i).getCodigo() == pelicula.getCodigo()) 
			{
				PeliculasAlquiladas.get(i).setCliente(null);
				PeliculasAlquiladas.get(i).alquilar(false);
				PeliculasAlquiladas.remove(i);
				break;
			}
		}
	}
	
	public void printHistorico() {
		System.out.println("Historico:");
		for(int i=0; i<HistoricoPeliculas.size(); i++) 
		{
			HistoricoPeliculas.get(i).printValues();
		}
		System.out.println("Alquiladas actualmente");
		for(int i=0; i<PeliculasAlquiladas.size(); i++) 
		{
			PeliculasAlquiladas.get(i).printValues();
		}
	}
	
}
