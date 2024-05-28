
public enum Ciudad {
	MADRID("MA", "Madrid"),
	TENERIFE("TE", "Tenerife"),
	ALICANTE("AL", "Alicante"),
	BILBAO("BI", "Bilbao"),
	VALENCIA("VA", "Valencia"),
	SEVILLA("SE", "Sevilla");
	
	private String titulo;
	private String nombre;
	
	
	private Ciudad(String titulo, String nombre) {
		this.titulo = titulo;
		this.nombre = nombre;
	}
	
	public String getTitulo() {
		return titulo;
	}

	public String getNombre() {
		return nombre;
	}
	
	
	
}
