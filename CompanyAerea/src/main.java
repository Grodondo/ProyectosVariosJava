import java.sql.Time;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class main {

	// LocalDateTime idt1 = LocalDateTime.of(1958, 5, 23, 12, 45, 0) -> es obvio que hacer
	
	static Scanner scInt = new Scanner(System.in);
    static Random random = new Random();
    
	final static int NUM_AVIONES = 4;
	public static DateTimeFormatter formatterDH = DateTimeFormatter.ofPattern("dd-MM-YYYY HH:mm");
	public static DateTimeFormatter formatterD = DateTimeFormatter.ofPattern("dd-MM-YYYY");
	public static DateTimeFormatter formatterH = DateTimeFormatter.ofPattern("HH:mm");

	static ArrayList<Vuelo> AllVuelos = new ArrayList<>();
	static ArrayList<Vuelo> Vuelos = new ArrayList<>();
	
	static int aviones[] = new int[NUM_AVIONES];
	
	public static void main(String[] args) {
		
		for(int i=0; i<aviones.length; i++) {
			aviones[i] = (i+1);
			LocalDateTime randomHour = getRandomHour();
			Vuelo newVuelo;
			do {
				newVuelo = new Vuelo((i+1), Ciudad.MADRID, Ciudad.values()[1+random.nextInt(Ciudad.values().length-1)], randomHour);
			}while(checkVueloRepetido(newVuelo));
			Vuelos.add(newVuelo);
		}
		
		LocalDateTime diaActual = LocalDateTime.now();
		
		while(true) {
			System.out.println(diaActual.format(formatterD) + "\n"
					+ "1.-Lista de vuelos\r\n"
					+ "2.-Vuelos día siguiente"
					);
			int menu = scInt.nextInt();
			switch(menu) {
			case 1:
				for(Vuelo v : AllVuelos) System.out.println(v);
				break;
			case 2:
				mostrarVuelos();
				for(int i=0; i<Vuelos.size(); i++) {
					Vuelo vt = new Vuelo(Vuelos.get(i));
					introducirDuracionVuelo(Vuelos.get(i));
					AllVuelos.add(vt);
					setNextVuelo(Vuelos.get(i));
				}
				break;
			default:
				System.err.println("Nope");
				break;
			}
		}
			
	}
	
	public static LocalDateTime getRandomHour() {
		 LocalDateTime now = LocalDateTime.now();
		 int randomHour = 7+random.nextInt(24-7);
		 int randomMinute = 5*random.nextInt(6);
		 
		 LocalDateTime newHour = now.withHour(randomHour).withMinute(randomMinute);
		 
		 return newHour;
	}
	
	public static void mostrarVuelos() {
		for(Vuelo v : Vuelos) System.out.println(v);
	}
	
	public static int vueloRepetido(Vuelo v) {
		int dur = 0;
		
		for(Vuelo vuelo : AllVuelos) {
			if(vuelo.getCiudDestino() == v.getCiudDestino() && vuelo.getCiudOrigen() == v.getCiudOrigen()) {
				dur = vuelo.getDuracion();
				break;
			}
		}
		
		return dur;
	}
	
	public static void introducirDuracionVuelo(Vuelo v) {
		int durMin;
		
		if(vueloRepetido(v)!=0) {
			System.out.println("\nDuración del vuelo en minutos " + v.getCiudOrigen() + " - " + v.getCiudDestino() + " -> " + vueloRepetido(v));
			durMin = vueloRepetido(v);
		}else {
		
			System.out.println("\nDuración del vuelo en minutos " + v.getCiudOrigen() + " - " + v.getCiudDestino() + "?");
			durMin = scInt.nextInt();
			
			if(durMin > 300) {
				System.out.println("El vuelo no puede ser tan largo.");
				durMin = 300;
			}
		}
		LocalDateTime horaLlegada = v.getHoraPartida().plusMinutes(durMin);
		v.setHoraLLegada(horaLlegada);
		v.setDuracion(durMin);
		
		System.out.println("Hora de llegada " + horaLlegada.format(formatterH));
	}
	
	public static boolean checkVueloRepetido(Vuelo v) {
		boolean repetido = false;
		
		for(int i=0; i<Vuelos.size(); i++) {
			if(Vuelos.get(i) == null) continue;
			else if(Vuelos.get(i).getCiudDestino() == v.getCiudDestino() && Vuelos.get(i).getCiudOrigen() == v.getCiudOrigen()) {
				repetido = true;
				break;
			}
		}
		return repetido;
	}
	
	public static void setNextVuelo(Vuelo v) {
		Ciudad ciudadOrigen = v.getCiudDestino();
		Ciudad ciudadDestino;
		Vuelo vt = new Vuelo(v);
		
		do {
			do {
				ciudadDestino = Ciudad.values()[1+random.nextInt(Ciudad.values().length-1)];
				vt.setCiudDestino(ciudadDestino);
			}while (ciudadDestino == ciudadOrigen);
			
		}while(checkVueloRepetido(vt));
		
			v.setCiudDestino(ciudadDestino);
			v.setCiudOrigen(ciudadOrigen);
			v.setHoraPartida(getRandomHour());
			v.setHoraLLegada(null);
			
	}

}
