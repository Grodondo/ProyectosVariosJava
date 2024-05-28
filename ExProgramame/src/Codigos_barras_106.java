import java.math.BigInteger;
import java.util.Scanner;


public class Codigos_barras_106 {

	public static int codigoControl(String code) {

		try {

			BigInteger icode = new BigInteger(code);
			//long icode = Long.parseLong(code, 10);
			String pais = "";
			boolean EAN_13 = false;

			int sum = 0;
			icode = icode.divide(BigInteger.TEN);
			//icode = icode / 10; // quito el digito de la derecha

			int nchars = code.length()-1;

			String datoControl = code.substring(nchars);
			int control = Integer. parseInt(datoControl);

			if(nchars > 13 || code.equals("0")) {

				return 0;

			//---------------------EAN-13-------------

			}else if(nchars>8) {

				EAN_13 = true;

				if(pais == "") {

					String prefix1 = code.substring(0, 1);
					String prefix2 = code.substring(0, 2);
					String prefix3 = code.substring(0, 3);

					if(prefix1.equals("0")) {
						pais = "EEUU";

					}else if(prefix2.equals("50")) {
						pais = "Inglaterra";

					}else if(prefix2.equals("70")) {
						pais = "Noruega";

					}else if(prefix3.equals("380")) {
						pais = "Bulgaria";

					}else if(prefix3.equals("539")) {
						pais = "Irlanda";

					}else if(prefix3.equals("560")) {

						pais = "Portugal";
						
					}else if(prefix3.equals("759")) {
						pais = "Venezuela";

					}else if(prefix3.equals("850")) {
						pais = "Cuba";

					}else if(prefix3.equals("890")) {
						pais = "India";

					}else {
						pais = "Desconocido";
					}			
				}

				for (int i = 0; i<nchars; i++) {

					String firstDigit = code.substring(i, i+1);
					int digit = Integer. parseInt(firstDigit);

					if (nchars % 2 == 0){
						if (i % 2 == 0){
							sum += digit;
						}else {			
							sum += digit*3;
						}		
					}else {
						if (i % 2 == 0){
						sum += digit*3;

						}else {
							sum += digit;
						}
					}
				}

			//---------------------EAN-8--------------

			}else {
				//int n = (int) icode;
				for (int i = 0; i<nchars; i++) {

					String firstDigit = code.substring(i, i+1);
					int digit = Integer. parseInt(firstDigit);

//					int firstDigit = Integer.parseInt(Integer.toString(n).substring(0, 1));

//					//Transforms n into n - (first number to the left)

//					n = n % (int) Math.pow(10, (int) Math.log10(n));
					if (nchars % 2 == 0){
						if (i % 2 == 0){
							sum += digit;

						}else {			
							sum += digit*3;

							}
					}else {
						if (i % 2 == 0){
						sum += digit*3;
						}else {
							sum += digit;

						}
					}
				}
			}

			if((sum+control) % 10 == 0 || (sum-control) % 10 == 0) {
				if(EAN_13) {	
					System.out.println("SI " + pais);

				}else { System.out.println("SI"); }	

			}else {

			System.out.println("NO");

			}
			return sum;

		}catch(Exception e){
			System.out.println("NO");
			return 0;
		}
	}

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		System.out.println(codigoControl("65839522"));   //sum = 88	
		System.out.println(codigoControl("65839521"));
		System.out.println(codigoControl("8414533043847"));
		System.out.println(codigoControl("5029365779425"));
		System.out.println(codigoControl("5129365779425"));
		 while (true) {
		        String codigoBarras = in.nextLine();
		        if(codigoBarras.equals("0")) {
		        	break;
		        }
		        codigoControl(codigoBarras);
		 }
	}

}