
public class Alcohol extends Producto{

	private double volumen;
	private double grado; 
	private boolean origen; //Organico o no organico
	
	
	public Alcohol(String nombre, double precio, int cantidad, double volumen, double grado, boolean origen) {
		super(nombre, precio, cantidad);
		this.volumen = volumen;
		this.grado = grado;
		this.origen = origen;
	}
	
	
	@Override
	public double aplicarDescuento(double coeficiente) {
		if(this.grado >= 25) coeficiente += 1.3;
		
		this.setPrecio(this.getPrecio() / coeficiente);
		return coeficiente;
	}
	
	@Override
	public int getCantidadMinima() {
		return 150;
	}
	
	@Override
	public boolean alertaPocasUnidades() {
		boolean alerta = false;
		
		if(this.getCantidad() < getCantidadMinima()) alerta = true;
		
		return alerta;
	}
	
}
