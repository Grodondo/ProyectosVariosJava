import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class main {

	private static Scanner scInt = new Scanner(System.in);
	private static Scanner scStr = new Scanner(System.in);
	
	//private static HashSet<Producto> productos = new HashSet<>();
	private static HashMap<String, Producto> Productos = new HashMap<>();
	private static HashMap<Producto, String> ProductosEnReparacion = new HashMap<>();
	private static ArrayList<Producto> ProductosVendidos = new ArrayList<Producto>();
	//private static HashSet<Producto> ProductosVendidos = new HashSet<>();
	
	
	private static double gananciasTotales;
	
	private static LocalDate fechaActual = LocalDate.now(); 
	public static DateTimeFormatter formatterD = DateTimeFormatter.ofPattern("dd-MM-YYYY");     
	
	public static void main(String[] args) {
		
		Producto p1 = new Producto("m1", "FairPhone", 301.40, Estado.DISPONIBLE);
		Producto p2 =	new Producto("m2", "Xiaomi", 162.99, Estado.DISPONIBLE);
		Producto p3 = new Producto("m3", "iPhone", 781.90, Estado.DISPONIBLE);
		Productos.put(p1.getId(), p1);
		Productos.put(p2.getId(), p2);
		Productos.put(p3.getId(), p3);

		
		while(true) {
			System.out.println(
					"MENU PRINCIPAL - DIA " + fechaActual.format(formatterD) + "\n" +
					"1.-Mostrar equipos" + "\n" +
					"2.-Crear equipo" + "\n" +
					"3.-Eliminar equipo" + "\n" +
					"4.-Marcar como averiado" + "\n" +
					"5.-Enviar a reparar un equipo" + "\n" +
					"6.-Vender un equipo" + "\n" +
					"7.-Pasar de día" + "\n\n" +
					"Elige una opcion (1-7)"
					);
			int menu = scInt.nextInt();
			switch(menu) {
			case 1:
				mostrarEquipos();
				pulsaEnter();
				break;
			case 2:
				crearEquipo();
				break;
			case 3:
				eliminarEquipo();
				break;
			case 4:
				marcarAveriado();
				break;
			case 5:
				enviarReparacion();
				break;
			case 6:
				 venderEquipo();
				break;
			case 7:
				pasarDia();
				break;
			default:
				System.err.println("Valor no válido, puebe de nuevo:\n");
				break;
			}
		}
	
	}
	
	public static void pulsaEnter() {
		System.out.println("\nPulse enter para volver al menú principal");
		scStr.nextLine();
	}
	
	public static void mostrarEquipos() {
		for(Producto p : Productos.values()) {
			p.mostrarInformacion();
		}
	}
	public static void mostrarEquiposDisponibles() {
		for(Producto p : Productos.values()) {
			if(p.getEstado() == Estado.DISPONIBLE) {
				p.mostrarInformacion();
			}
		}
	}
	
	public static void mostrarEquiposAveriados() {
		for(Producto p : Productos.values()) {
			if(p.getEstado() == Estado.AVERIADO) {
				p.mostrarInformacion();
			}
		}
	}

	public static void crearEquipo() {
		System.out.println("ID: ");
		String newId = scStr.nextLine();
		
		if(!Productos.containsKey(newId)) {
			System.out.println("Nombre: ");
			String newName = scStr.nextLine();
			System.out.println("Precio: ");
			double newPrecio = scInt.nextDouble();
			
			Producto newProducto = new Producto(newId, newName, newPrecio, Estado.DISPONIBLE);
			Productos.put(newId, newProducto);
			
			System.out.println("\nEquipo creado");
		}
		else System.out.println("\nYa existe un equipo con ese ID");
		
		pulsaEnter();
	}
	
	public static void eliminarEquipo() {
		mostrarEquipos();
		System.out.println("Cual quieres eliminar: ");
		String id = scStr.nextLine();
		
		if(Productos.containsKey(id)) {
			Productos.remove(id);
			
			System.out.println("\nEquipo eliminado correctamente");
		}
		else System.out.println("\nNo existe ese ID");
		
		pulsaEnter();
	}
	
	public static void marcarAveriado() {
		mostrarEquiposDisponibles();
		System.out.println("ID: ");
		String id = scStr.nextLine();
		
		if(Productos.containsKey(id)) {
			if(Productos.get(id).getEstado() == Estado.DISPONIBLE) {
				Productos.get(id).setEstado(Estado.AVERIADO);
				System.out.println("\nEl equipo ha quedado marcado como averiado");
			}
			else System.out.println("\nEl equipo debe estar disponible");
		}
		else System.out.println("\nNo existe ese ID");
		
		pulsaEnter();
	}
	
	public static void mostrarEnReparacion() {
		
		for(Producto p : Productos.values()){
			if(p.getEstado() == Estado.EN_REPARACION) {
				System.out.println(p.getId() + " - " + p.getNombre() + " - Lo repara " + ProductosEnReparacion.get(p)+ ".  Fin reparación: " + p.getFinReparacion().format(formatterD));
			}
		}
		
	}
	
	public static void enviarReparacion() {
		System.out.println("Equipos averiados:");
		mostrarEquiposAveriados();
		
		System.out.println("ID:");
		String idEquipo = scStr.nextLine();
		if(Productos.containsKey(idEquipo)) {
			if(Productos.get(idEquipo).getEstado() == Estado.AVERIADO) {
				System.out.println("Nombre:");
				String nombrePersona = scStr.nextLine();
				System.out.println("En cuantos días terminara la reparacion: ");
				int diasR = scInt.nextInt();
				
				LocalDate fechaFinR = fechaActual.plusDays(diasR);
				
				Productos.get(idEquipo).setFinReparacion(fechaFinR);
				Productos.get(idEquipo).setEstado(Estado.EN_REPARACION);
				ProductosEnReparacion.put(Productos.get(idEquipo), nombrePersona);
				
				mostrarEnReparacion();
			}	
			else System.out.println("Este equipo no se encuentra averiado");
		}
		else System.out.println("El ID no existe"); 

		pulsaEnter();		
	}
	
	public static void mostrarEquiposVendidos() {
		System.out.println("\nEQUIPOS VENDIDOS");
		Collections.sort(ProductosVendidos, new ordenarPorPrecio());
		
		for(Producto p : ProductosVendidos) {
				System.out.println(p.getId() + " - " + p.getNombre() + ".  Precio " + p.getPrecio()+ ".  Estado  " + p.getEstado());
		}
	}
	
	public static void venderEquipo() {
		mostrarEquipos();
		System.out.println("Cual quieres vender: ");
		String id = scStr.nextLine();
		
		if(Productos.containsKey(id)) 
		{
			if(!(Productos.get(id).getEstado() == Estado.EN_REPARACION)) 
			{
				if(Productos.get(id).getEstado() == Estado.AVERIADO) 
					gananciasTotales += (Productos.get(id).getPrecio()/2);
				else 
					gananciasTotales += (Productos.get(id).getPrecio());
				
				ProductosVendidos.add(Productos.get(id));
				Productos.remove(id);
				
				mostrarEquiposVendidos();
				System.out.println("\nTotal de ganancias:  " + gananciasTotales);
				
			}
			else System.out.println("\nNo se pueden vender equipos en reparación");
		}
		else System.out.println("\nNo existe este equipo");
		
		pulsaEnter();
	}
	
	public static void pasarDia(){
		fechaActual = fechaActual.plusDays(1);
		
		System.out.println("Pasamos al dia " + fechaActual.format(formatterD));
		
		for(Producto p : Productos.values()) {
			if(p.getEstado() == Estado.EN_REPARACION) {
				if(fechaActual.compareTo(p.getFinReparacion()) == 0) {
					ProductosEnReparacion.remove(p);
					p.setEstado(Estado.DISPONIBLE);
					//p.setFinReparacion(null);
				}
				System.out.println(p.getId() + " - " + p.getNombre() + ". Precio " + p.getPrecio() + ". Estado " + p.getEstado() + ". Fecha reparacion: " + p.getFinReparacion().format(formatterD));
			}
		}
		pulsaEnter();
		
	}
	
}
