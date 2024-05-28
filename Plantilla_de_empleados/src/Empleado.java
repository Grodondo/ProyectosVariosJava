
public class Empleado {

	private String nombre;
	private String apellido;
	private String dni;
	private int diasAntiguedad;
	
	private double sueldoBruto;
	private double sueldoNeto;
	
	public Empleado(String nombre, String apellido, String dni) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.diasAntiguedad = 0;
	}

	public double getSueldoBruto() {
		return sueldoBruto;
	}

	public void setSueldoBruto(double sueldoBruto) {
		this.sueldoBruto = sueldoBruto;
	}

	public double getSueldoNeto() {
		return sueldoNeto;
	}

	public void setSueldoNeto(double sueldoNeto) {
		this.sueldoNeto = sueldoNeto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public int getDiasAntiguedad() {
		return diasAntiguedad;
	}

	public void addDiasAntiguedad(int diasAntiguedad) {
		this.diasAntiguedad += diasAntiguedad;
	}

	public void subirSueldoBruto(int porcentaje) {
		this.sueldoBruto += ((this.sueldoBruto*porcentaje)/100);
	}
	
	@Override
	public String toString() {
		String ret = (this.nombre + " " + this.apellido + " - " + this.dni);
		return ret;
	}
	
}
