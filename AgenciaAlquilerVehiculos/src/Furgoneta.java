
public class Furgoneta extends Vehiculo{

	static int nFurgosDis;
	static final int nFurgosMax = 3;
	
	private final String nombre = "Furgoneta";
	private int capacidad; //litros

	public Furgoneta(String matricula, String marca, String modelo, int caballos, int capacidad) {
		super(matricula, marca, modelo, caballos);
		this.capacidad = capacidad;
		
		this.precioAlquiler = 220;
	}
	
	@Override
	public void addVehiculoDis() {
		if(nFurgosDis >= nFurgosMax) {
			System.out.println("no se pueden añadir más furgonetas");
		}else
			nFurgosDis++;
	}
	
	@Override
	public void remVehiculoDis() {
		if(nFurgosDis <= 0) {
			System.out.println("no se pueden alquilar más furgonetas en este momento");
		}else
			nFurgosDis--;
	}
	
	@Override
	public void queSoy() {
		System.out.println("Una furgoneta");
	}
}
