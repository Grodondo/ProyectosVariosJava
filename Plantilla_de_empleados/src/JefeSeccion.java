
public class JefeSeccion extends Empleado implements Cobros{
	
	private int categoria;
	
	public JefeSeccion(String nombre, String apellido, String dni) {
		super(nombre, apellido, dni);
	
		addSueldoBruto();
	}

	public int getCategoria() {
		return categoria;
	}

	public void setCategoria(int categoria) {
		this.categoria = categoria;
	}
	
	@Override
	public double sueldoNeto() {
		double sueldoNeto = (this.getSueldoBruto() / 100) * 80;
		this.setSueldoNeto(sueldoNeto);
		
		return sueldoNeto;
	}

	@Override
	public double indemnizacion() {
		double indemnizacion;
		
		if(this.getDiasAntiguedad() <= 90) {
			indemnizacion = 0;
		}
		else {
			indemnizacion = this.getSueldoBruto() * 3;
		}
		indemnizacion += 400 * this.categoria;
		
		return indemnizacion;
	}

	@Override
	public void subidaSueldo() {
		// TODO Auto-generated method stub
		
	}

	public void addSueldoBruto() {
		double sueldoBruto = 1700;
		sueldoBruto += (50 * this.categoria);
		
		setSueldoBruto(sueldoBruto);
	}


	
}
