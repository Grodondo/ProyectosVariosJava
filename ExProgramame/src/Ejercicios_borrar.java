import java.util.Scanner;

public class Ejercicios_borrar {
	
	public static int[] coverArray(int array[], int coverLength ) {
		
		if(coverLength <=  array.length) return array;
		else {
			int[] newArray = new int[coverLength]; 
			
			for (int i = 0; i < array.length; i++) {
				newArray[i] = array[i];
			}
			for (int i = array.length; i < newArray.length; i++) {
				newArray[i] = Integer.MIN_VALUE;
			}
			
			return newArray;
		}
	}
	
	//retorna 0 Si ambos valores están presentes, 1 Si solo el array1 está prsente y 2 Si solo el array2 está presente
	public static int[] compareArray(int array1[], int array2[]) {
		
		int maxArray = Math.max(array1.length, array2.length);
		int newArray[] = new int[maxArray];
		if(maxArray == array1.length) array2 = coverArray(array2, maxArray);
		else array1 = coverArray(array1, maxArray);
		
		for(int i = 0; i<maxArray; i++) {
			
			if(array1[i] == Integer.MIN_VALUE) newArray[i] = -2;
			else if(array2[i] == Integer.MIN_VALUE) newArray[i] = -1;
			else if (array1[i] > array2[i]) newArray[i] = 1;
			else if (array1[i] < array2[i]) newArray[i] = 2;
			else newArray[i] = 0;
			
		}
		
		return newArray;
	}
	
    public static void main(String[] args) {
        System.out.println("Números perfectos entre 0 y 10000:");

        for (int i = 2; i <= 10000; i++) {
            if (esNumeroPerfecto(i)) {
                System.out.println(i);
            }
        }
    }

    public static boolean esNumeroPerfecto(int n) {
        if (n <= 1) {
            return false;
        }

        int sumaDivisores = 1;

        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                sumaDivisores += i;
                if (i != n / i) {
                    sumaDivisores += n / i;
                }
            }
        }

        return sumaDivisores == n;
    }
}
    

