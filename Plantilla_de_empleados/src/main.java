import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class main {

	static Scanner scInt = new Scanner(System.in);
	static Scanner scStr = new Scanner(System.in);
	static Scanner scDouble = new Scanner(System.in);
	
	static ArrayList<Empleado> Empleados = new ArrayList<>();
	
	public static Mozo nuevoMozo() {
		System.out.println("Nombre del nuevo empleado?");
		String newName = scStr.nextLine();
		System.out.println("Apellido del nuevo empleado?");
		String newApellido = scStr.nextLine();
		System.out.println("DNI del nuevo empleado?");
		String newDni = scStr.nextLine();
		
		Mozo newMozo = new Mozo(newName, newApellido, newDni);
		
		return newMozo;
	}
	
	public static JefeSeccion nuevoJefe() {
		System.out.println("Nombre del nuevo jefe?");
		String newName = scStr.nextLine();
		System.out.println("Apellido del nuevo jefe?");
		String newApellido = scStr.nextLine();
		System.out.println("DNI del nuevo jefe?");
		String newDni = scStr.nextLine();
		
		JefeSeccion newJefe = new JefeSeccion(newName, newApellido, newDni);
		
		int newCateg = 0;
		while(newCateg > 3 || newCateg <= 0) {
			System.out.println("Categoria (1, 2 o 3)?");
			newCateg = scInt.nextInt();
		}
		newJefe.setCategoria(newCateg);
		
		return newJefe;
	}
	
	public static Directivo nuevoDir() {
		System.out.println("Nombre del nuevo directivo?");
		String newName = scStr.nextLine();
		System.out.println("Apellido del nuevo directivo?");
		String newApellido = scStr.nextLine();
		System.out.println("DNI del nuevo directivo?");
		String newDni = scStr.nextLine();
		
		Directivo newDirectivo = new Directivo(newName, newApellido, newDni);
		
		System.out.println("Pertenece al consejo de Administracion? (s/n)");
		String sino = scStr.nextLine();
		if(sino.equalsIgnoreCase("s") || sino.equalsIgnoreCase("si")) newDirectivo.setPerteneceConsejo(true);	;
			
		
		return newDirectivo;
	}
	
	public static void introducirTrabajador() {
		System.out.println("~INTRODUCIR TRABAJADOR~");
		boolean exit = false;
		
		System.out.println("Puesto del trabajador? (1:Mozo | 2:Jefe | 3:Directivo)");
		int puesto = scInt.nextInt();
		
		while(!exit) {
			try {
				switch(puesto) {
				case 1:
					Mozo mozo = nuevoMozo();
					
					Empleados.add(mozo);
					System.out.println("El Empleado " + mozo.getNombre() + " ha sido añadido exitosamente");
					
					exit = true;
					break;
				case 2:
					JefeSeccion jefe = nuevoJefe();					
					
					Empleados.add(jefe);
					System.out.println("El Jefe de Seccion " + jefe.getNombre() + " ha sido añadido exitosamente");
					
					exit = true;
					break;
				case 3:
					Directivo directivo = nuevoDir();
				
					Empleados.add(directivo);
					System.out.println("El Directivo " + directivo.getNombre() + " ha sido añadido exitosamente");
					
					exit = true;
					break;
				default:
					System.out.println("Opcion no valida, intentelo de nuevo:");
				}
			}
			catch(Exception e) {
				System.out.println("Los datos introducidos no han sido correctos, intentelo de nuevo");
				scStr.next();
				scInt.next();
			}
		}
		
	}
	
	public static void mostrarEmpleados() {
		for(int i=0; i<Empleados.size(); i++) {
			System.out.print(i + ".- ");
			System.out.println(Empleados.get(i)); 
		}
	}
	
	public static void eliminarTrabajador() {
		System.out.println("~ELIMINAR TRABAJADOR~");
		mostrarEmpleados();
		
		System.out.println("ID del trabajador a eliminar?");
		int idTrab = scInt.nextInt();
		
		if(idTrab < Empleados.size() && idTrab >= 0) {
			
			System.out.println("El Empleado " + Empleados.get(idTrab).getNombre() + " ha sido eliminado exitosamente");
			Empleados.remove(idTrab);
			
		}else System.out.println("Id no válido");
		
	}
	
	public static void subirSueldoBruto() {
		System.out.println("~SUBIR SUELDO BRUTO TRABAJADOR~");
		mostrarEmpleados();
		
		System.out.println("ID del trabajador a subir sueldo?");
		int idTrab = scInt.nextInt();
		
		if(idTrab < Empleados.size() && idTrab >= 0) 
		{
			if(Empleados.get(idTrab) instanceof Mozo) {
				Empleados.get(idTrab).subirSueldoBruto(5);
				System.out.println("Sueldo de "+ Empleados.get(idTrab).getNombre() + " ampliado a " + Empleados.get(idTrab).getSueldoBruto());
			}
			else if(Empleados.get(idTrab) instanceof JefeSeccion) {
				Empleados.get(idTrab).subirSueldoBruto(10);
				System.out.println("Sueldo de "+ Empleados.get(idTrab).getNombre() + " ampliado a " + Empleados.get(idTrab).getSueldoBruto());
			}
			else if(Empleados.get(idTrab) instanceof Directivo) {
				Empleados.get(idTrab).subirSueldoBruto(15);
				System.out.println("Sueldo de "+ Empleados.get(idTrab).getNombre() + " ampliado a " + Empleados.get(idTrab).getSueldoBruto());
			}
			else System.out.println("No se le puede subur el sueldo");
				
			
		}else System.out.println("Id no válido");
	}

	public static void mostrarDatosTrabajador() {
		System.out.println("~MOSTRAR DATOS TRABAJADOR~");
		mostrarEmpleados();
		
		System.out.println("ID del trabajador a mostrar datos?");
		int idTrab = scInt.nextInt();
		
		
		if(Empleados.get(idTrab) instanceof Mozo) {
			Mozo mozo = (Mozo) Empleados.get(idTrab);
			
			System.out.println(mozo);
			System.out.println("Sueldo Neto: " +  mozo.sueldoNeto());
			System.out.println("Indemnizacion en caso de despido: " + mozo.indemnizacion());
			System.out.println("Antiguedad (dias): " + mozo.getDiasAntiguedad());
		}
		if(Empleados.get(idTrab) instanceof JefeSeccion) {
			JefeSeccion jefe = (JefeSeccion) Empleados.get(idTrab);
			
			System.out.println(jefe);
			System.out.println("Sueldo Neto: " +  jefe.sueldoNeto());
			System.out.println("Indemnizacion en caso de despido: " + jefe.indemnizacion());
			System.out.println("Antiguedad (dias): " + jefe.getDiasAntiguedad());
		}
		if(Empleados.get(idTrab) instanceof Directivo) {
			Directivo dir = (Directivo) Empleados.get(idTrab);
			
			System.out.println(dir);
			System.out.println("Sueldo Neto: " +  dir.sueldoNeto());
			System.out.println("Indemnizacion en caso de despido: " + dir.indemnizacion());
			System.out.println("Antiguedad (dias): " + dir.getDiasAntiguedad());
		}
		else System.out.println("Nope");
		
	}
	
	public static void pasarMes() {
		for(int i=0; i<Empleados.size(); i++) {
			Empleados.get(i).addDiasAntiguedad(30);
		}
		System.out.println("Mes ha pasado.");
	}
	
	public static void ordenarLista() {
		System.out.println("~ORDENAR LISTA~");
		
		System.out.println("Ordenar lista por: (1)Nombre y apellido, (2)sueldo bruto, o (3)días de antigüedad.");
		int tipoOrden = scInt.nextInt();
		
		switch(tipoOrden) {
		case 1:
			Collections.sort(Empleados, new OrdenarPorNomAp());
			break;
		case 2:
			Collections.sort(Empleados, new OrdenarPorSueldoBruto());
			break;
		case 3:
			Collections.sort(Empleados, new OrdenarPorDiasAnt());
			break;
		default:
			System.out.println("El valor introducido no es valido");
		}
		
	}
	
	public static void main(String[] args) {
		
		Empleados.add(new Directivo("Dir1", "Ramon", "89898989d"));
		Empleados.add(new JefeSeccion("Jefe1", "La tortuga ninja", "89898989d"));
		Empleados.add(new JefeSeccion("Jefe2", "Charless", "89898989d"));
		Empleados.add(new Mozo("Mozo1", "Juan", "89898989d"));
		Empleados.add(new Mozo("Mozo2", "Pedro", "89898989d"));
		Empleados.add(new Mozo("Mozo3", "Rodolfo", "89898989d"));
		
		while(true) {
			System.out.println("\nMENU PRINCIPAL\n"
					+ "1.- Introducir Trabajador\n"
					+ "2.- Eliminar trabajador\n"
					+ "3.- Subir sueldo trabajador\n"
					+ "4.- Sueldo neto, despido y antiguedad\n"
					+ "5.- Ordenar listas\n"
					+ "6.- Pasar mes"
					);
			int menuPrincipal = scInt.nextInt();
			switch(menuPrincipal) {
			case 1:
				introducirTrabajador();
				break;
			case 2:
				eliminarTrabajador();
				break;
			case 3:
				subirSueldoBruto();
				break;
			case 4:
				mostrarDatosTrabajador();
				break;
			case 5:
				ordenarLista();
				break;
			case 6:
				pasarMes();
				break;
			default:
				System.out.println("Valor fuera de rango, intentelo de nuevo: ");
				break;
			}
		}

	}

}
