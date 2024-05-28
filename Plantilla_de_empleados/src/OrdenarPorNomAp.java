import java.util.Comparator;

public class OrdenarPorNomAp implements Comparator<Empleado>{
	
	@Override
	public int compare(Empleado e1, Empleado e2) {
		 int comp = e1.getNombre().compareTo(e2.getNombre());
		 if(comp == 0) comp = e1.getApellido().compareTo(e2.getApellido());
		 return comp;
	}
}
