
public class Limpieza extends Producto{

	private double volumen;
	private boolean esBiodegradable;
	private String usoRec;  //(baño, cocina, ropa…)
	
	public Limpieza(String nombre, double precio, int cantidad, double volumen, boolean esBiodegradable,
			String usoRec) {
		super(nombre, precio, cantidad);
		this.volumen = volumen;
		this.esBiodegradable = esBiodegradable;
		this.usoRec = usoRec;
	}
	
	@Override
	public double aplicarDescuento(double coeficiente) {
		if(this.esBiodegradable) coeficiente += 0.7;
		
		this.setPrecio(this.getPrecio() / coeficiente);
		return coeficiente;
	}

	@Override
	public int getCantidadMinima() {
		return 100;
	}
	
	@Override
	public boolean alertaPocasUnidades() {
		boolean alerta = false;
		
		if(this.getCantidad() < getCantidadMinima()) alerta = true;
		
		return alerta;
	}
	
}
