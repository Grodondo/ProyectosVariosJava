import java.util.Comparator;

public class OrdenarPorSueldoBruto implements Comparator<Empleado>{

	@Override
	public int compare(Empleado e1, Empleado e2) {
		return Double.compare(e1.getDiasAntiguedad(), e2.getDiasAntiguedad());
	}
}
