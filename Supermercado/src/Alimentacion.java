
public class Alimentacion extends Producto{

	private double peso;
	private String fechaCad;
	private boolean origen; //organico y no Organico
	
	
	public Alimentacion(String nombre, double precio, int cantidad, double peso, String fechaCad, boolean origen) {
		super(nombre, precio, cantidad);
		this.peso = peso;
		this.fechaCad = fechaCad;
		this.origen = origen;
	}
	
	@Override
	public double aplicarDescuento(double coeficiente) {
		if(!this.origen) coeficiente += 0.8;
		
		this.setPrecio(this.getPrecio() / coeficiente);
		return coeficiente;
	}
	
	@Override
	public int getCantidadMinima() {
		return 50;
	}
	
	@Override
	public boolean alertaPocasUnidades() {
		boolean alerta = false;
		
		if(this.getCantidad() < getCantidadMinima()) alerta = true;
		
		return alerta;
	}
	
}
