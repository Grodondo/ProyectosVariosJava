
public class Producto implements Control{

	private String nombre;
	private double precio;
	private int cantidad;
	private int cantidadVendidaAnio;
	private int cantidadVendidaMes[] = new int[12];
	
	public Producto(String nombre, double precio, int cantidad) {
		super();
		this.nombre = nombre;
		this.precio = precio;
		this.cantidad = cantidad;
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

	public int getCantidadVendidaAnio() {
		return cantidadVendidaAnio;
	}

	public int getCantidadVendidaMes(int mes) {
		return cantidadVendidaMes[mes];
	}
	
	public void pasarAnio() {
		for(int i=0; i<this.cantidadVendidaMes.length; i++) {
			this.cantidadVendidaMes[i] = 0;
		}
		this.cantidadVendidaAnio = 0;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	public void addCantidad(int cantidad) {
		if(cantidad < 0) cantidad = 0;
		this.cantidad += cantidad;
	}
	
	public void subCantidad(int cantidad, int mes) {
		if(cantidad < 0) cantidad = 0;
		if(cantidad > this.cantidad) cantidad = this.cantidad;
	
		this.cantidad -= cantidad;
		this.cantidadVendidaAnio += cantidad;
		this.cantidadVendidaMes[mes] += cantidad;
	}
	
	public int getCantidadMinima() {
		return 0;
	}

	@Override
	public double aplicarDescuento(double coef) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean alertaPocasUnidades() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String toString() {
		return "nombre = " + nombre + ", precio = " + precio + ", cantidad = " + cantidad + " ";
	}
	
	
	
}
