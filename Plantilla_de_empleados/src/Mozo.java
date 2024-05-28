
public class Mozo extends Empleado implements Cobros{	
	
	public Mozo(String nombre, String apellido, String dni) {
		super(nombre, apellido, dni);
		
		setSueldoBruto(1200);
	}

	@Override
	public double sueldoNeto() {
		double sueldoNeto = (this.getSueldoBruto() / 100) * 85;
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
		
		return indemnizacion;
	}

	@Override
	public void subidaSueldo() {
		//this.sueldoBruto += ((this.sueldoBruto*5)/100);
		
	}

}
