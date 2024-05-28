
public class Vehiculo {

	private final String nombre = "Vehiculo";
	
	private String matricula;
	private String marca;
	private String modelo;
	private int caballos;
	
	private Empresa empresa;
	private int diasAlquiler;
	protected double precioAlquiler;
	private boolean alquilado;
	
	public Vehiculo(String matricula, String marca, String modelo, int caballos) {
		super();
		this.matricula = matricula;
		this.marca = marca;
		this.modelo = modelo;
		this.caballos = caballos;
		
		this.empresa = null;
		diasAlquiler = 0;
		alquilado = false;
		
		addVehiculoDis();
	}
	
	public double getPrecioAlquiler() {
		return this.precioAlquiler;
	}
	
	public boolean isAlquilado() {
		return alquilado;
	}
	
	public void setAlquilado(boolean alquilado) {
		this.alquilado = alquilado;
	}
	
	public int getDiasAlquiler() {
		return diasAlquiler;
	}
	
	public void setDiasAlquiler(int diasAlquiler) {
		this.diasAlquiler = diasAlquiler;
	}


	public void alquilarVehiculo(Empresa empresa, int dias) {
		this.setAlquilado(true);
		
		this.empresa = empresa;
		this.setDiasAlquiler(dias);
	}
	
	public void devolver() {
		alquilado = false;
		empresa.removeVehicule(this);
		diasAlquiler = 0;
		addVehiculoDis();
	}
	
	public void pasarDia() {
		if(alquilado) {
			diasAlquiler--;
			if(diasAlquiler < 0) {
				devolver();
			}
		}
	}
	
	public void addVehiculoDis() {
		System.out.println("ADD");
	}
	public void remVehiculoDis() {
		System.out.println("REM");
	}
	
	public void queSoy() {
		System.out.println("Un vehiculo");
	}
	
	@Override
	public String toString() {
		String vehiculo = (this.nombre + " " + this.marca + " " + this.matricula + " " + this.modelo + " ");
		if(this.alquilado) {
			vehiculo += this.empresa.getNombre() + " " + this.diasAlquiler + " dias restantes";
		}
		
		return vehiculo;
	}
}
