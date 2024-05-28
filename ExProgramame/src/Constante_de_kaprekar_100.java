import java.util.ArrayList;
import java.util.List;

public class Constante_de_kaprekar_100 {

	public static void Constante_Kaprekar(String num) {
		
		final int cte = 6174;
		int res = 0;
		String temp_res;
		int numAsc = 0;
		int numDes = 0;
		
		int[] num_asc = new int[4];
		int[] num_des = new int[4];
		List<Integer> list = new ArrayList<>();
		for(int i = 0; i<4; i++) {
			list.add((Integer. parseInt(num.substring(i, i+1))));
		}
		
		if(list.get(0) != list.get(1) || list.get(0) != list.get(2)  || list.get(0)  != list.get(3) ) {
			while(res != cte) {
				int cont = 0;
				int cont_i = 0;
				while(list.isEmpty() == false) {
					int n = 0;
					for(int i = 0; i<list.size(); i++) {
						if(list.get(i) > n) {
							cont_i = i;
							num_des[cont] = list.get(i);
							n = list.get(i);
						}
					}
					cont++;
					list.remove(cont_i);
				}
				
				int mult = 1000;
				int c = 3;
				for(int i = 0; i<4; i++) {
					num_asc[i] = num_des[c];
					numAsc = numAsc + (num_asc[i] * mult);
					numDes = numDes + (num_des[i] * mult);
					mult /= 10;
					c--;
				}
				res = numDes - numAsc;
				temp_res = Integer.toString(res);
				for(int i = 0; i < 4; i++) {
					list.add((Integer. parseInt(temp_res.substring(i, i+1))));
				}
				
			}
		}
	}
	
	public static void main(String[] args) {
		//Constante_Kaprekar("6983");
		Constante_Kaprekar("7690");

	}

}
