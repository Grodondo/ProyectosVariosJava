import java.util.Comparator;

public class OrdenarPorDiasAnt implements Comparator<Empleado>{
	
	@Override
	public int compare(Empleado e1, Empleado e2) {
		return Integer.compare(e1.getDiasAntiguedad(), e2.getDiasAntiguedad());
	}
}
