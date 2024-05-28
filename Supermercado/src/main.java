import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class main {

	static Scanner scInt = new Scanner(System.in);
	static Scanner scStr = new Scanner(System.in);
	static Scanner scDouble = new Scanner(System.in);

	public static int mes;
	
	private static ArrayList<Producto> Productos = new ArrayList<Producto>();
	
	public static void main(String[] args) {

		mes = 1;
		
		Limpieza l1 = new Limpieza("escoba", 12, 500, 34, true, null);
		
		ArrayList<Alimentacion> comida = new ArrayList<Alimentacion>();
		comida.add(new Alimentacion("manzana", 1.5, 500, 4, "24-3-04", true));
		comida.add(new Alimentacion("pera", 1.5, 30, 4, "24-3-04", true));
		comida.add(new Alimentacion("platano", 1.5, 80, 4, "24-3-04", true));
		
		Productos.add(l1);
		Productos.addAll(comida);
		
		while (true) {
			System.out.println(
					"\nMenú Principal: " + devolverMes(mes) + "\n"
					+ "1.- Compra de productos genérica\r\n"
					+ "2.- Compra de productos que están bajo mínimos\r\n"
					+ "3.- Venta aleatoria de productos\r\n"
					+ "4.- Ordenar elementos\r\n"
					+ "5.- Cambiar de mes"
					);

			int menu = scInt.nextInt();
			switch (menu) {
			case 1:
				compraProducto();
				break;
			case 2:
				compraProductoBajoMinimo();
				break;
			case 3:
				ventaAleatoria();
				break;
			case 4:
				ordenarElementos();
				break;
			case 5:
				pasarMes();
				break;
			default:
				System.out.println("Valor introducido incorrecto, fuera de rango.");
				break;
			}
		}

	}


	private static String devolverMes(int mes) {
		
		String meses[] = {"enero", "febrero", "marzo", "abril", "mayo", "junio", "julio", "agosto", "septiembre", "octubre", "noviembre", "diciembre"};
		String mesStr = "None";
		
		if(mes <= 0 || mes > 12) {
			System.err.println("El mes indicado, no es válido");
		}else {
			mesStr = meses[mes-1];
		}
		
		return mesStr;
	}

	private static void mostrarProductos() {
	
		for(int i=0; i<Productos.size(); i++) {
			System.out.println(i + ".- " + Productos.get(i));
		}
		
	}
	
	private static void compraProducto() {
		System.out.println("Productos:");
		mostrarProductos();
		
		int prodID;
		do{
			System.out.println("\nElige el producto a comprar");
			prodID = scInt.nextInt();
			
			if(prodID<0 || prodID > Productos.size()) System.err.println("Producto no valido, pruebe de nuevo:");
		}while(prodID<0 || prodID > Productos.size());
		
		System.out.println("Numero de unidades?");
		int cantidad = scInt.nextInt();
		
		Productos.get(prodID).addCantidad(cantidad);
		System.out.println("La cantidad ha sido añadida correctamente");
		
		//------------------------------------------------
		System.out.println("Cambiar precio de venta? (s/n)");
		String sino = scStr.nextLine();
		
		if(sino.equalsIgnoreCase("s") || sino.equalsIgnoreCase("si")) {
			System.out.println("Nuevo precio?");
			double newPrecio = scDouble.nextDouble();
			
			Productos.get(prodID).setPrecio(newPrecio);
			System.out.println("Precio cambiado de " + Productos.get(prodID).getNombre() + " a " + newPrecio + " exitosamente");
		}
		
	}

	private static void compraProductoBajoMinimo() {
		int nProdMin = 0;
		
		System.out.println("\nProductos bajo minimos:");
		
		for(int i=0; i<Productos.size(); i++) {
			if(Productos.get(i).alertaPocasUnidades()) {
				System.out.println(Productos.get(i).getNombre() + " ->");
				System.out.println("	Cantidad actual: " + Productos.get(i).getCantidad());
				System.out.println("	Cantidad minima: " + Productos.get(i).getCantidadMinima());
				System.out.println("Cuantos deseas comprar?");
				int cantidad = scInt.nextInt();
				Productos.get(i).addCantidad(cantidad);
				
				System.out.println(cantidad + " " + Productos.get(i).getNombre() + " añadidas\n");
				
				nProdMin++;
			}
		}
		
		if(nProdMin <= 0) System.out.println("No hay ningun producto bajo minimos actualmente.");	
	}

	private static void ventaAleatoria() {
		
		for(int i = 0; i<Productos.size(); i++) {
			int randCant = (int) (Math.random()*Productos.get(i).getCantidad());
			Productos.get(i).subCantidad(randCant, (mes-1));
			
			System.out.println(Productos.get(i).getNombre() + " - Cantidad Vendida: " + randCant + " - Cantidad restante: " + Productos.get(i).getCantidad());
			if(Productos.get(i).alertaPocasUnidades()) System.out.println("		Actualmente en mínimos");
		}
	
	}

	private static void ordenarElementos() {
		System.out.println("~ORDENAR PRODUCTOS~");
		ArrayList<Producto> ProductosOrdenados = Productos;;
		
		System.out.println("Ordenar productos por: (1)Ventas Año, (2)Ventas Mes.");
		int menuOrdenarProductos = scInt.nextInt();
		
		switch(menuOrdenarProductos) {
		case 1:
			Collections.sort(ProductosOrdenados, new OrdenarPorCantidadTotalVentas());
			System.out.println("Ordenado por Cantidad Total de Ventas:");
			break;
		case 2:
			System.out.println("En base a que mes quieres ordearlo (1-12)?");
			int mesAnt = mes;
			mes = scInt.nextInt();
			
			Collections.sort(ProductosOrdenados, new OrdenarPorCantidadVentasMes());
			System.out.println("Ordenado por mes -> " + devolverMes(mes));
			
			mes = mesAnt;
			break;
		default:
			System.out.println("El valor introducido no es valido");
		}
		
		for(int i=0; i<ProductosOrdenados.size(); i++) {
			System.out.print(i + ".- ");
			System.out.println(ProductosOrdenados.get(i)); 
		}
		
	}
	
	private static void pasarMes() {
		mes++;
		if(mes == 13) {
			mes = 1;
			for(Producto p : Productos) p.pasarAnio();
		}
		
		double coedAlco = 0, coedAlim = 0, coedLimp = 0;
		boolean coefCorrect;
		
		do {
			coefCorrect = true;
			
			System.out.println("Coeficientes del siguiente mes? (0.7 - 1.2)");
			System.out.print("Limpieza: ");
			coedLimp = scInt.nextDouble();
			if(coedLimp < 0.7 || coedLimp > 1.2) {
				System.out.println("Valor no válido, prueba de nuevo");
				coefCorrect = false;
				continue;
			}
			
			System.out.print("Alimentacion: ");
			coedAlim = scInt.nextDouble();
			if(coedAlim < 0.7 || coedAlim > 1.2) {
				System.out.println("Valor no válido, prueba de nuevo");
				coefCorrect = false;
				continue;
			}
			
			System.out.print("Alcohol: ");
			coedAlco = scInt.nextDouble();
			if(coedAlco < 0.7 || coedAlco > 1.2) {
				System.out.println("Valor no válido, prueba de nuevo");
				coefCorrect = false;
				continue;
			}
		}while(!coefCorrect);
		
		for(int i=0; i<Productos.size(); i++) {
			
			if(Productos.get(i) instanceof  Limpieza) Productos.get(i).aplicarDescuento(coedLimp);
			else if(Productos.get(i) instanceof  Alimentacion) Productos.get(i).aplicarDescuento(coedAlim);
			else if(Productos.get(i) instanceof  Alcohol) Productos.get(i).aplicarDescuento(coedAlco);
		}
		
	}
	
}

