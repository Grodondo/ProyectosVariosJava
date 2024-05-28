
public class Directivo extends Empleado implements Cobros{

	private boolean perteneceConsejo;
	
	public Directivo(String nombre, String apellido, String dni) {
		super(nombre, apellido, dni);
		this.perteneceConsejo = false;
		
		this.setSueldoBruto(4500);
	}

	public boolean isPerteneceConsejo() {
		return perteneceConsejo;
	}

	public void setPerteneceConsejo(boolean perteneceConsejo) {
		this.perteneceConsejo = perteneceConsejo;
	}

	@Override
	public double sueldoNeto() {
		double sueldoNeto = ((this.getSueldoBruto() / 100) * 70);
		if(this.perteneceConsejo) sueldoNeto += 200;
		
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
		
		if(this.perteneceConsejo) indemnizacion += 15000;
		else indemnizacion += 5000;
		
		return indemnizacion;
		
	}

	@Override
	public void subidaSueldo() {
		// TODO Auto-generated method stub
		
	}
	
}
