import java.util.Comparator;

public class OrdenarPorCantidadTotalVentas implements Comparator<Producto>{
	
	@Override
	public int compare(Producto p1, Producto p2) {
		return Integer.compare(p1.getCantidadVendidaAnio(), p2.getCantidadVendidaAnio());
	}
}