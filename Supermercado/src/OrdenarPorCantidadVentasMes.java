import java.util.Comparator;

public class OrdenarPorCantidadVentasMes implements Comparator<Producto>{
	
	@Override
	public int compare(Producto p1, Producto p2) {
		return Integer.compare(p1.getCantidadVendidaMes(main.mes), p2.getCantidadVendidaMes(main.mes));
	}
}