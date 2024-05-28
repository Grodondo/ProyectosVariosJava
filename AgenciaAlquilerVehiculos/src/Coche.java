
public class Coche extends Vehiculo{

	static int nCochesDis;
	static final int nCochesMax = 7;

	private final String nombre = "Coche";
	private int numPuertas;

	public Coche(String matricula, String marca, String modelo, int caballos, int numPuertas) {
		super(matricula, marca, modelo, caballos);
		this.numPuertas = numPuertas;
		
		this.precioAlquiler = 80;
	}
	
	@Override
	public void addVehiculoDis() {
		if(nCochesDis >= nCochesMax) {
			System.out.println("no se pueden añadir más furgonetas");
		}else
			nCochesDis++;
	}
	
	@Override
	public void remVehiculoDis() {
		if(nCochesDis <= 0) {
			System.out.println("no se pueden alquilar más coches en este momento");
		}else
			nCochesDis--;
	}
	
	@Override
	public void queSoy() {
		System.out.println("Un coche");
	}
}
