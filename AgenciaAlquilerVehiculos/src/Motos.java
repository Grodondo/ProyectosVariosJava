
public class Motos extends Vehiculo{

	private int nMotosDis;
	private final int nMotosMax = 2;
	
	private final String nombre = "Moto";
	private boolean tieneMaletin;


	public Motos(String matricula, String marca, String modelo, int caballos, boolean tieneMaletin) {
		super(matricula, marca, modelo, caballos);
		this.tieneMaletin = tieneMaletin;
		
		this.precioAlquiler = 40;
	}
	
	@Override
	public void addVehiculoDis() {
		if(nMotosDis >= nMotosMax) {
			System.out.println("no se pueden añadir más motos");
		}else
			nMotosDis++;
	}
	
	@Override
	public void remVehiculoDis() {
		if(nMotosDis <= 0) {
			System.out.println("no se pueden alquilar más motos en este momento");
		}else
			nMotosDis--;
	}
	
	@Override
	public void queSoy() {
		System.out.println("Una moto");
	}
	
}
