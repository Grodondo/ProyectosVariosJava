import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Vuelo {

	private static int nVuelo;
	
	private int avion;
	private String codigo;
	private Ciudad ciudOrigen;
	private Ciudad ciudDestino;
	private LocalDateTime horaPartida;
	private LocalDateTime  horaLlegada;
	
	private int duracion;
	
	DateTimeFormatter formatterH = DateTimeFormatter.ofPattern("HH:mm");
	
	public Vuelo(int avion, Ciudad ciudOrigen, Ciudad ciudDestino, LocalDateTime horaPartida) {
		super();
		nVuelo++;
		
		this.avion = avion;
		this.ciudOrigen = ciudOrigen;
		this.ciudDestino = ciudDestino;
		this.horaPartida = horaPartida;
		
		this.codigo = setCodigo();
	}
	
	//Constructor de copia
    public Vuelo(Vuelo original) {
        this.avion = original.avion;
        this.ciudOrigen = original.ciudOrigen;
        this.ciudDestino = original.ciudDestino;
        this.horaPartida = original.horaPartida;
        this.codigo = original.codigo;
    }
	
	public Ciudad getCiudOrigen() {
		return ciudOrigen;
	}

	public void setCiudOrigen(Ciudad ciudOrigen) {
		this.ciudOrigen = ciudOrigen;
	}

	public Ciudad getCiudDestino() {
		return ciudDestino;
	}

	public void setCiudDestino(Ciudad ciudDestino) {
		this.ciudDestino = ciudDestino;
	}


	private String setCodigo() {
		String code;
		code = this.avion + this.ciudOrigen.getTitulo() + this.ciudDestino.getTitulo() +  nVuelo;
		
		return code;
	}

	
	public int getDuracion() {
		return duracion;
	}
	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public String ShowHoraPartida() {
		return horaPartida.format(formatterH);
	}
	
	public LocalDateTime getHoraPartida() {
		return horaPartida;
	}
	
	public void setHoraPartida(LocalDateTime horaPartida) {
		this.horaPartida = horaPartida;
	}

	public LocalDateTime getHoraLLegada() {
		return horaLlegada;
	}
	
	public String showHoraLLegada() {
		return horaLlegada.format(formatterH);
	}

	public void setHoraLLegada(LocalDateTime horaLlegada) {
		this.horaLlegada = horaLlegada;
	}

	@Override
	public String toString() {
		String vuelo;
		
		if(ciudDestino != null) {
			vuelo = codigo + " : " + ciudOrigen.getNombre() + " - " + ciudDestino.getNombre() + "      " + horaPartida.format(formatterH);
		}else {
			vuelo = codigo + " : " + ciudOrigen.getNombre() + " - " + ciudDestino.getNombre() + "      " + horaPartida.format(formatterH) + " - " + horaLlegada.format(formatterH);
		}
		return vuelo;
	}
	
	
	
}
