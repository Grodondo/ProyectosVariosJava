import java.util.ArrayList;

public class Empresa {

	private ArrayList<Vehiculo> Vehiculos = new ArrayList<Vehiculo>();
	private String nombre;
	private String CIF;
	
	
	public Empresa(String nombre, String cIF) {
		super();
		this.nombre = nombre;
		CIF = cIF;
	}


	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCIF() {
		return CIF;
	}

	public void setCIF(String cIF) {
		CIF = cIF;
	}
	
	public boolean haAlquilado() {
		boolean haAlquilado;
		
		if(this.Vehiculos.size()>=1) haAlquilado = true;
		else haAlquilado = false;
		
		return haAlquilado;
	}
	
	public void addVehiculo(Vehiculo vehiculo, int dias) {
		if(vehiculo.isAlquilado()) {
			System.out.println("El vehiculo ya se encuentra alquilado");
		}else {
			this.Vehiculos.add(vehiculo);
			vehiculo.alquilarVehiculo(this, dias);
		}
	}
	
	public void removeVehicule(Vehiculo vehiculo) {
		for(int i=0; i<Vehiculos.size(); i++) {
			if(Vehiculos.get(i) == vehiculo) {
				Vehiculos.remove(i);
				break;
			}
		}
	}
	
	public void printDatosEmpresa() {
		System.out.println(this.nombre + " " + this.CIF + " ha alquilado " + this.Vehiculos.size() + " vehiculos: ");
		for(int i=0; i<Vehiculos.size(); i++) {
			System.out.println("       " + Vehiculos.get(i));
		}
	}
	
}
