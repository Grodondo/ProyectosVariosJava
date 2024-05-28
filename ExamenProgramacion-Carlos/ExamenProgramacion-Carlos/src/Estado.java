
public enum Estado {
	DISPONIBLE("Disponible"),
    AVERIADO("Averiado"),
   	EN_REPARACION("En reparacion"),
	VENDIDO("Vendido");

	String nombre;

	private Estado(String nombre) {
		this.nombre = nombre;
	}
	
	
}
