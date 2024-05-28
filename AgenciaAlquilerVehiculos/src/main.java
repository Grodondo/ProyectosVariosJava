import java.util.ArrayList;
import java.util.Scanner;

public class main {

	static Scanner scInt = new Scanner(System.in);
	static Scanner scStr = new Scanner(System.in);
	static Scanner scDouble = new Scanner(System.in);
	
	static final int nEmpresas = 6;
	static Empresa Empresas[] = new Empresa[nEmpresas];
	static ArrayList<Vehiculo> Vehiculos = new ArrayList<Vehiculo>();
	//static ArrayList<Vehiculo> VehAlquilado = new ArrayList<Vehiculo>();
	//static ArrayList<Vehiculo> VehSinAlquilar = new ArrayList<Vehiculo>();
	static int dia;
	static double gananciasM, gananciasF, gananciasC;
	
	static public Empresa introducirEmpresa() {
		int idEmpresa;
		while(true) {
			System.out.println("\nDIA " + dia);
			System.out.println("Que empresa desea alquilar un vehiculo?");
			try {
				idEmpresa = scInt.nextInt();
			}catch(Exception e) {
				System.out.println("Valor introducido no válido, intentelo de nuevo:\n");
				scInt.next();
				continue;
			}
			if(idEmpresa == -1) 
			{
				informeVehiculosSinAlq();
				informeVehiculosAlq();
				informeGanancias();
				pasarDia();
				continue;
			}
			else if(idEmpresa == -3) {
				informeVehiculosSinAlq();
				informeVehiculosAlq();
				informeGanancias();
				informeEmpresas();
				continue;
			}
			else if(idEmpresa == -4) {
				informeVehiculosSinAlq();
				informeVehiculosAlqExtra();
				informeGanancias();
				devolverVehiculo();
			}
			else if(idEmpresa < 0 || idEmpresa >= nEmpresas) 
			{
				System.out.println("Valor introducido fuera de rango, intentelo de nuevo:\n");
				continue;
			}
			else break;
			
		}
		return Empresas[idEmpresa];

	}
	
	static public ArrayList<Vehiculo> ordenarArrayAlquiler() {
		ArrayList<Vehiculo> VehAlq = new ArrayList<Vehiculo>();
		ArrayList<Vehiculo> VehAlqOrd = new ArrayList<Vehiculo>();
		
		for(int i=0; i<Vehiculos.size(); i++) {
			if(Vehiculos.get(i).isAlquilado())
				VehAlq.add(Vehiculos.get(i));
		}
		while(VehAlq.size() >= 1) {
			int alqMayor = 0;
			int indexAlqMayor = 0;
			for(int i=0; i<VehAlq.size(); i++) {
				if(VehAlq.get(i).getDiasAlquiler()>alqMayor) {
					alqMayor = VehAlq.get(i).getDiasAlquiler();
					indexAlqMayor = i;
				}
			}
			VehAlqOrd.add(VehAlq.get(indexAlqMayor));
			VehAlq.remove(indexAlqMayor);
		}

		return VehAlqOrd;
	}
	
	static public void informeVehiculosSinAlq() {
		System.out.println("VEHICULOS SIN ALQUILAR");
		for(int i=0; i<Vehiculos.size(); i++) {
			if(!Vehiculos.get(i).isAlquilado())
				System.out.println(Vehiculos.get(i));
		}
	}
	static public void informeVehiculosAlq() {
		
		ArrayList<Vehiculo> VehAlqOrd = ordenarArrayAlquiler();
		System.out.println("\nVEHICULOS ALQUILADOS");
		
		for(int i=0; i<VehAlqOrd.size(); i++) {
			System.out.println(VehAlqOrd.get(i));
		}	
	}
	
	static public void informeVehiculosAlqExtra() {
		
		System.out.println("\nVEHICULOS ALQUILADOS");
		
		for(int i=0; i<Vehiculos.size(); i++) {
			if(Vehiculos.get(i).isAlquilado()) {
				System.out.println(i + ".- " + Vehiculos.get(i));
			}
		}	
	}
	
	public static void informeGanancias() {
		System.out.println(
				"\nGANANCIAS OBTENIDAS"
				+ "\nTotales: " + (gananciasC + gananciasF + gananciasM)
				+ "\nCoches: " + gananciasC
				+ "\nFurgonetas: " + gananciasF
				+ "\nMotos: " + gananciasM
				);
	}
	
	public static void informeEmpresas() {
		boolean algEmpAlq = false;
		
		System.out.println("\nINFORME EMPRESAS");
		for(int i=0; i<Empresas.length; i++) {
			if(Empresas[i].haAlquilado()) {
				Empresas[i].printDatosEmpresa();
				algEmpAlq = true;
			}
		}
		if(!algEmpAlq) System.out.println("Ninguna empresa ha alquilado vehiculos");
	}
	
	static public void devolverVehiculo() {
		boolean continuarDevolviendo = true;
		while(continuarDevolviendo) {
			System.out.println("\nQue vehiculo desea devolver?");
			int vehDev = scInt.nextInt();
			
			if(Vehiculos.get(vehDev).isAlquilado()) {
				if(Vehiculos.get(vehDev) instanceof Coche) {
					gananciasC -= (Vehiculos.get(vehDev).getDiasAlquiler() * Vehiculos.get(vehDev).getPrecioAlquiler());
					Vehiculos.get(vehDev).devolver();
				}
				else if(Vehiculos.get(vehDev) instanceof Furgoneta) {
					gananciasF -= (Vehiculos.get(vehDev).getDiasAlquiler() * Vehiculos.get(vehDev).getPrecioAlquiler());
					Vehiculos.get(vehDev).devolver();
				}
				else if(Vehiculos.get(vehDev) instanceof Motos) {
					gananciasM -= (Vehiculos.get(vehDev).getDiasAlquiler() * Vehiculos.get(vehDev).getPrecioAlquiler());
					Vehiculos.get(vehDev).devolver();
				}
				else System.out.println("Vehiculo inadecuado");
			}else System.out.println("Este vehiculo no está alquilado");
			
			System.out.println("Desea devolver más vehiculos (s/n)?");
			String sino = scStr.nextLine();
			
			if(sino.equalsIgnoreCase("n") || sino.equalsIgnoreCase("no")) continuarDevolviendo=false;
		}
	}
	
	static public void pasarDia() {
		for(int i=0; i<Vehiculos.size(); i++) {
			Vehiculos.get(i).pasarDia();
		}
		dia++;
	}
	
	static public void mostrarVehSinAlq(int tipoVeh) {
		
		for(int i=0; i<Vehiculos.size(); i++) {
			if(
				(Vehiculos.get(i) instanceof Coche && tipoVeh == 1 && !Vehiculos.get(i).isAlquilado()) ||
				(Vehiculos.get(i) instanceof Furgoneta && tipoVeh == 2 && !Vehiculos.get(i).isAlquilado()) ||
				(Vehiculos.get(i) instanceof Motos && tipoVeh == 3 && !Vehiculos.get(i).isAlquilado()) 
				) 
			{
				System.out.print(i + ".- ");
				System.out.println(Vehiculos.get(i));
			}
		}
	}
	
	static public void alquilarVehiculo(Empresa empresa) {
		System.out.println("Que vehiculo?");
		int tipoVehiculo = scInt.nextInt();
		
		//Imprime la lista de los vehiculos dispoibles de su tipo.
		mostrarVehSinAlq(tipoVehiculo);
		
		System.out.println("Que vehiculo desea Alquilar?");
		int indVeh = scInt.nextInt();
		
		System.out.println("Durante cuantos dias?");
		int diasAlquiler = alquilarDias();
		
		switch(tipoVehiculo) {
		case 1:
			if(Vehiculos.get(indVeh) instanceof Coche && !Vehiculos.get(indVeh).isAlquilado()) {
				empresa.addVehiculo(Vehiculos.get(indVeh), diasAlquiler);
				Vehiculos.get(indVeh).remVehiculoDis();
				gananciasC += Vehiculos.get(indVeh).getPrecioAlquiler() * diasAlquiler;
			}
			else if(!(Vehiculos.get(indVeh) instanceof Coche)) System.out.println("El ID de vehiculo no es el mismo al tipo introducido.");
			else if(Vehiculos.get(indVeh).isAlquilado()) System.out.println("Este vehiculo ya esta alquilado");
			else System.out.println("No hay coches disponibles en este momento");
			
//			for(int i=0; i<Vehiculos.size(); i++) {
//				if(Vehiculos.get(i) instanceof Coche && !Vehiculos.get(i).isAlquilado()) {
//					empresa.addVehiculo(Vehiculos.get(i), diasAlquiler);
//					Vehiculos.get(i).remVehiculoDis();
//					gananciasC += Vehiculos.get(i).getPrecioAlquiler();
//					break;
//				}
//			}
			break;
			
		case 2:
			if(Vehiculos.get(indVeh) instanceof Furgoneta && !Vehiculos.get(indVeh).isAlquilado()) {
				empresa.addVehiculo(Vehiculos.get(indVeh), diasAlquiler);
				Vehiculos.get(indVeh).remVehiculoDis();
				gananciasF += Vehiculos.get(indVeh).getPrecioAlquiler() * diasAlquiler;
			}
			else if(!(Vehiculos.get(indVeh) instanceof Furgoneta)) System.out.println("El ID de vehiculo no es el mismo al tipo introducido.");
			else if(Vehiculos.get(indVeh).isAlquilado()) System.out.println("Este vehiculo ya esta alquilado");
			else System.out.println("No hay furgonetas disponibles en este momento");
			
			break;
		case 3:
			if(Vehiculos.get(indVeh) instanceof Motos && !Vehiculos.get(indVeh).isAlquilado()) {
				empresa.addVehiculo(Vehiculos.get(indVeh), diasAlquiler);
				Vehiculos.get(indVeh).remVehiculoDis();
				gananciasM += Vehiculos.get(indVeh).getPrecioAlquiler() * diasAlquiler;
			}
			else if(!(Vehiculos.get(indVeh) instanceof Motos)) System.out.println("El ID de vehiculo no es el mismo al tipo introducido.");
			else if(Vehiculos.get(indVeh).isAlquilado()) System.out.println("Este vehiculo ya esta alquilado");
			else System.out.println("No hay motos disponibles en este momento");
			
			break;
		default:
			System.out.println("Valor fuera de rango admitido.\n");
			break;
		}
	}
	
	static public int alquilarDias() {
		int dias;
		while(true) {
			try {
			dias = scInt.nextInt();
			
			if(dias <= 0)  System.out.println("Valor no válido");
			else break;
			
			}catch(Exception e) {
				System.out.println("No es un Integer, intentelo de nuevo:");
			}
		}
		return dias;
	}
	
	public static void main(String[] args) {
		
		gananciasM = gananciasF = gananciasC = 0;
		
		Empresas[0] = new Empresa("Empresa1", "Cf1a");
		Empresas[1] = new Empresa("Empresa2", "Cf2a");
		Empresas[2] = new Empresa("Empresa3", "Cf3a");
		Empresas[3] = new Empresa("Empresa4", "Cf4a");
		Empresas[4] = new Empresa("Empresa5", "Cf5a");
		Empresas[5] = new Empresa("Empresa6", "Cf6a");
		
		Coche coches[] = new Coche[7];
		coches[0] = new Coche("Coche2", "107", "541c", 130, 4);
		coches[1] = new Coche("Coche3", "207", "542c", 150, 4);
		coches[2] = new Coche("Coche4", "307", "543c", 30, 4);
		coches[3] = new Coche("Coche5", "117", "544c", 90, 2);
		coches[4] = new Coche("Coche6", "127", "545c", 220, 4);
		coches[5] = new Coche("Coche7", "257", "546c", 230, 6);
		coches[6] = new Coche("Coche8", "309", "547c", 330, 4);
		
		Furgoneta furgonetas[] = new Furgoneta[3];
		furgonetas[0] = new Furgoneta("Furgo1", "317", "143c", 130, 2);
		furgonetas[1] = new Furgoneta("Furgo2", "327", "243c", 130, 4);
		furgonetas[2] = new Furgoneta("Furgo3", "337", "343c", 130, 2);
		
		Motos motos[] = new Motos[2];
		motos[0] = new Motos("moto1", "407", "343c", 190, true);
		motos[1] = new Motos("moto2", "417", "443c", 130, false);
		
		for(Coche c : coches) Vehiculos.add(c);
		for(Furgoneta f : furgonetas) Vehiculos.add(f);
		for(Motos m : motos) Vehiculos.add(m);
		
		while(true) {
	
			Empresa empresa = introducirEmpresa();
			
			alquilarVehiculo(empresa);
			
			
		}

	}

}
