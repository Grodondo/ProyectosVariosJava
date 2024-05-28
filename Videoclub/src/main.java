import java.util.ArrayList;
import java.util.Scanner;

public class main {

	static Scanner scInt = new Scanner(System.in);
	static Scanner scStr = new Scanner(System.in);
	static Scanner scDouble = new Scanner(System.in);
	
	public static int dia;
	static ArrayList<CdMusica> Cds = new ArrayList<CdMusica>();
	static ArrayList<Pelicula> Peliculas = new ArrayList<Pelicula>();
	static ArrayList<Cliente> Clientes = new ArrayList<Cliente>();
	
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	
	
	public static void introducirNuevoProducto() {
		System.out.println("Que tipo de producto desea introducir (1:Cds o 2:Peliculas)");
		int tipoProducto = scInt.nextInt();
		
		CdMusica cd = null;
		Pelicula pelicula = null;
		boolean introCorrect = false;
		
		switch(tipoProducto) {
		case 1:
			cd = introducirCd();
			introCorrect = true;
			break;
		case 2:
			pelicula = introducirPelicula();
			introCorrect = true;
			break;
		default:
			System.out.println("No se acepta ese valor.");
			break;
		}
		
		if(introCorrect) {
			System.out.println("\n¿CUANTAS UNIDADES?");
			int numUnidades = scInt.nextInt();
			if(cd != null) {
				int cdSize = Cds.size();
				for(int i=cdSize; i<cdSize+(numUnidades); i++) {
					CdMusica newCd = new CdMusica(cd);
					//ArrayList<CdMusica> newCd = new ArrayList(cd);
					Cds.add(newCd);
					CdMusica.newCodeCd++;
				}
			}
			else if(pelicula != null) {
				int peliculaSize = Peliculas.size();
				for(int i=peliculaSize; i<peliculaSize+numUnidades; i++) {
					Pelicula newPeli = new Pelicula(pelicula);
					//ArrayList<Pelicula> newPelicula = new ArrayList(pelicula);
					Peliculas.add(newPeli);
					Pelicula.newCodePeli++;
				}
			}
		}
		
	}
	
	public static void introducirCliente() {
		String nombre, apellidos, dni;
		nombre = apellidos = dni = null;
		try {
			System.out.println("~~NUEVO CLIENTE~~");
			System.out.println("Nombre?");
			nombre = scStr.nextLine();
			System.out.println("Apellidos?");
			apellidos = scStr.nextLine();
			System.out.println("DNI?");
			dni = scStr.nextLine();
		}catch(Exception e) {
			System.out.println(ANSI_RED_BACKGROUND + "Error, valores introducidos no válidos" + ANSI_RESET);
			scStr.next();
		}

		Cliente newCliente = new Cliente(nombre, apellidos, dni);
		
		Clientes.add(newCliente);
	}
	
	public static CdMusica introducirCd() {
		System.out.println("Titulo del creador?");
		String creador = scStr.nextLine();
		System.out.println("titulo del CD?");
		String titulo = scStr.nextLine();
		System.out.println("A que precio se venderá?");
		double precio = scDouble.nextDouble();
		
		CdMusica cd = new CdMusica(creador, titulo, precio);
		
		return cd;
	}
	
	public static Pelicula introducirPelicula() {
		System.out.println("Titulo de la pelicula?");
		String titulo = scStr.nextLine();
		
		Pelicula.TipoPeliculaFecha tipoPelicula;
		while(true) {
			System.out.println("Tipo de pelicula? (Nueva, Semi, Antigua)");
			String tipoPeliculaStr = scStr.nextLine();
			
			if(tipoPeliculaStr.equalsIgnoreCase("Nueva")) {
				tipoPelicula = Pelicula.TipoPeliculaFecha.NOVEDADES;
				break;
			}else if(tipoPeliculaStr.equalsIgnoreCase("Semi")) {
				tipoPelicula = Pelicula.TipoPeliculaFecha.SEMI_NOVEDADES;
				break;
			}else if(tipoPeliculaStr.equalsIgnoreCase("Antigua")) {
				tipoPelicula = Pelicula.TipoPeliculaFecha.ANTIGUAS;
				break;
			}
		}
		
		Pelicula pelicula = new Pelicula(titulo, tipoPelicula);
		
		return pelicula;
	}
	
	public static void mostrarPeliculas(boolean alquiladas, boolean sinAlquilar) {
		for(int i=0; i<Peliculas.size(); i++) {
			if(!alquiladas && sinAlquilar) 
				if(Peliculas.get(i).isAlquilada()) continue;
				
			else if(alquiladas && !sinAlquilar) 
				if(!Peliculas.get(i).isAlquilada()) continue;

			System.out.print(i + ".- ");
			Peliculas.get(i).printValues();
		}
	}
	
	public static void mostrarCds() {
		for(int i=0; i<Cds.size(); i++){
			System.out.print(i + ".- ");
			Cds.get(i).printValues();
		}
	}
	
	public static void eliminarProducto() {
		
		int[] arrayProductos = new int[Peliculas.size() + Cds.size()];
		
		for(int i=0; i<arrayProductos.length; i++) {
			if(i < Peliculas.size()) {
				System.out.print(i + ".- ");
				Peliculas.get(i).printValues();
			}
			else {
				System.out.print(i + ".- ");
				Cds.get(i-Peliculas.size()).printValues();
			}
		}
		
		System.out.println("Introducir ID de producto a eliminar->");
		int idProd = scInt.nextInt();

		if(idProd < Peliculas.size()) Peliculas.remove(idProd);
		else {
			idProd -= (Peliculas.size());
			Cds.remove(idProd);
		}
	
	}
	
	public static void alquilarPelicula() {
		if(Peliculas.size() == 0) System.out.println("\nNo hay peliculas ha alquilar.");
		else if(Clientes.size() == 0) System.out.println("\nNo hay clientes que puedan alquilar");
		else {
			mostrarPeliculas(false, true);
			while(true) {
				try {
					System.out.println("Elige el numero de pelicula a alquilar (-1 para salir)->");
					int nPelicula = scInt.nextInt();
					if(nPelicula == -1) break;
					
					System.out.println("Cual es tu numero de socio?");
					int nSocio = scInt.nextInt();
					
					int idCliente = -1;
					for(int i=0; i<Clientes.size(); i++) 
						if(Clientes.get(i).getNumeroSocio() == nSocio) idCliente = i;
					
					
					if(Peliculas.get(nPelicula).isAlquilada()) System.out.println("Esta pelicula ya está alquilada");
					else if(idCliente == -1) System.out.println("Este Cliente no existe");
					else {
						Clientes.get(idCliente).addPelicula(Peliculas.get(nPelicula));
						Peliculas.get(nPelicula).alquilar(true);
						System.out.println("La película " + Peliculas.get(nPelicula).getTitulo() + " ha sido alquilada por " + Peliculas.get(nPelicula).getDiasAlquiler() + " días. ");
					}
				}catch(Exception e) {
					System.out.println(ANSI_RED_BACKGROUND + "Error, Intentelo de nuevo->" + ANSI_RESET);
					scInt.next();
				}

			}
		}
	}
	
	public static void devolverPelicula() {
		printPeliculasAlquiladas();
		boolean exit = false;
		
		while(!exit) {
			try {
				System.out.println("Peliculas para devolver (-1 para salir):");
				int idPeli = scInt.nextInt();
				if(idPeli == -1) exit = true;
				
				else if(Peliculas.get(idPeli).getDiasAlquiler() >= 0) 
				{
					System.out.println("Pelicula " + Peliculas.get(idPeli).getTitulo() + " devuelta con exito");
					Peliculas.get(idPeli).getCliente().deletePelicula(Peliculas.get(idPeli));
					exit = true;
				}else 
				{
					System.out.println("PELICULA DEVUELTA CON RETRASO");
					int nDiasRetraso = Math.abs(Peliculas.get(idPeli).getDiasAlquiler());
					
					double cobrar = Peliculas.get(idPeli).cobrarRetraso(nDiasRetraso);
					Pelicula.retrasoTotal += cobrar;
					
					System.out.println("Pelicula " + Peliculas.get(idPeli).getTitulo() + " devuelta con exito");
					Peliculas.get(idPeli).getCliente().deletePelicula(Peliculas.get(idPeli));
					
					exit = true;
				}
			}catch(Exception e) {
				System.out.println(ANSI_RED_BACKGROUND + "Error, Intentelo de nuevo->" + ANSI_RESET);
				scInt.next();
			}
		}
	}
	
	public static void venderDisco() {
		mostrarCds();
		System.out.println("Elige el numero de CD correspondiente->");
		int nCd = scInt.nextInt();
	
		CdMusica.gananciasTotales += Cds.get(nCd).getPrecio();
		System.out.println("El disco " + Cds.get(nCd).getTitulo() + " de " + Cds.get(nCd).getNombreCreador() + " ha sido vendido por " + Cds.get(nCd).getPrecio() + " euros");
		Cds.remove(nCd);
	}
	
	public static void printPeliculasAlquiladas() {
		for(int i=0; i<Peliculas.size(); i++) {
			if(Peliculas.get(i).isAlquilada()) {
				System.out.print(i + ".- ");
				Peliculas.get(i).printValues();
				System.out.print("->Dias de alquiler restantes: " + Peliculas.get(i).getDiasAlquiler() + "\n");
			}
		}
	}
	
	public static ArrayList<Pelicula> ordenarPeliculas(ArrayList<Pelicula> Peliculas) {
		 ArrayList<Pelicula> NewPeliculas = new ArrayList<Pelicula>();
		 ArrayList<Pelicula> PeliculasOrdenadas = new ArrayList<Pelicula>();
		 
		 for(int i=0; i<Peliculas.size(); i++) {
			 if(Peliculas.get(i).getDiasAlquiler() < 0) {
				 NewPeliculas.add(Peliculas.get(i));
			 }
		 }

		 while(NewPeliculas.size() > 0) {
			 int lowestValue = 0;
			 int getPos = 0;
			 for(int i=0; i<NewPeliculas.size(); i++) {
				 if(NewPeliculas.get(i).getDiasAlquiler() < lowestValue) {
					 lowestValue = NewPeliculas.get(i).getDiasAlquiler();
					 getPos = i;
				 }
			 }
			 PeliculasOrdenadas.add(NewPeliculas.get(getPos));
			 NewPeliculas.remove(getPos);
					
		 }
		 return PeliculasOrdenadas;
	}
	
	public static void verRetrasos() {
		 ArrayList<Pelicula> PeliculasOrdenadas = ordenarPeliculas(Peliculas);

		for(int i=0; i<PeliculasOrdenadas.size(); i++) {
			PeliculasOrdenadas.get(i).printValues();
		}
	}
	
	public static void buscarCliente() {
		try {
			boolean clienteEncontrado = false;
			System.out.println("Codigo de cliente?");
			int codCliente = scInt.nextInt();
			
			for(int i=0; i<Clientes.size(); i++) {
				if(Clientes.get(i).getNumeroSocio() == codCliente) {
					clienteEncontrado = true;
					Clientes.get(i).printHistorico();
				}
			}
			
		}catch(Exception e) {
			System.out.println(ANSI_RED_BACKGROUND + "Error, Intentelo de nuevo->" + ANSI_RESET);
			scInt.next();
		}
	}
	
	public static void gananciasTotales() {
		System.out.println(
				"Alquileres: " + Pelicula.alquilerTotal + " euros" +
				"Ventas Recargos: " + Pelicula.retrasoTotal + " euros" +
				"\nVentas de discos: " + CdMusica.gananciasTotales + " euros" +
				"\nganancias Totales: " + (Pelicula.alquilerTotal + CdMusica.gananciasTotales + Pelicula.retrasoTotal) + " euros"
				);
	}
	
	public static void pasarDia() {
		dia++;
		for(int i=0; i<Peliculas.size(); i++) {
			if(Peliculas.get(i).isAlquilada()) {
				Peliculas.get(i).pasarDia();
			}
		}
	}
	
	public static void main(String[] args) {
		
		while(true) {
			System.out.println(
					"\nMENU: DIA " + dia +"\n"
					+ "1.- Introducir nuevo producto.\n"
					+ "2.- Dar de alta cliente\n" 
					+ "3.- Eliminar producto\n"
					+ "4.- Ver listado de películas\n"
					+ "5.- Ver listado de CDs\n"
					+ "6.- Alquilar película\n"
					+ "7.- Devolver pelicula\n"
					+ "8.- Vender disco\n"
					+ "9.- Ver películas en alquiler\n"
					+ "10.- Ver retraso\n"
					+ "11.- Buscar Cliente\n"
					+ "12.- Ver ganancias\n"
					+ "13.- Pasar al día siguiente"
					);
			int menuPrincipal = scInt.nextInt();
			switch(menuPrincipal) {
			case 1:
				introducirNuevoProducto();
				break;
			case 2:
				introducirCliente();
				break;
			case 3:
				eliminarProducto();
				break;
			case 4: 
				mostrarPeliculas(true, true);
				break;
			case 5:
				mostrarCds();
				break;
			case 6:
				alquilarPelicula();
				break;
			case 7:
				devolverPelicula();
				break;
			case 8:
				venderDisco();
				break;
			case 9:
				printPeliculasAlquiladas();
				break;
			case 10:
				verRetrasos();
				break;
			case 11:
				buscarCliente();
				break;
			case 12:
				gananciasTotales();
				break;
			case 13:
				pasarDia();
				break;			
			default:
				System.out.println("Numero introducido fuera de rango.\n");
				break;
			}
		}

	}

}
