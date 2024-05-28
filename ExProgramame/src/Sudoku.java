import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class Sudoku {
	public static int[][] transpose (int [][] array){
		
		int[][] newArray = new int [array[0].length][array.length];
		
		for(int i=0; i<newArray.length; i++) {
			for(int j=0; j<newArray[i].length; j++) {
				newArray[i][j] = array[j][i];
			}
		}
		
		return newArray;
	}
	
//	public static int[][] getRidCero(int[][] array){
//		
//		int[][] newArray = {};
//		
//		
//		return newArray;
//	}
	
	//Given a row and col between 0-8 and a 9x9 array, it returns a 3x3 array on those positions
	public static int[][] getSudokuSquare (int[][] sudoku, int row, int col){
		
		int[][] newArray = new int[3][3];
		if(row >= 9 || col >= 9) return newArray;
		
		for(int i = 0; i<newArray.length; i++) {
			for(int j=0; j<newArray[i].length; j++) {
				newArray[i][j] = sudoku[col*3 + i][row*3 + j];
			}
		}
		
		return newArray;
	}
	
	
	public static int[] fillSquare (int[][] sudoku, int row, int col){
		int[][] newSudokuSquare = getSudokuSquare(sudoku, row/3, col/3);
		int[][] transposedSudoku = transpose(sudoku);
		int[] posVal = new int[27];
		int posValCont = 0;
		
		for(int i=0; i<newSudokuSquare.length; i++) {
			for(int j=0; j<newSudokuSquare[i].length; j++) {
				posVal[posValCont] = newSudokuSquare[i][j];
				posValCont++;
			}
		}
		for(int i=0; i<sudoku[row].length; i++) {
			posVal[posValCont] = sudoku[row][i];
			posValCont++;
		}
		for(int i=0; i<sudoku[col].length; i++) {
			posVal[posValCont] = transposedSudoku[col][i];
			posValCont++;
		}
		
		
		return posVal;
	}
	
	public static boolean containedIn(int num, int[] contArray) {
		boolean contained = false;
			for(int j=0; j<contArray.length; j++) {
				if(num == contArray[j] || num == 0) {
					contained = true;
					break;
				}
		}
		return contained;
	}
	
	
//	public static int[] getRidRepeated2(int[] array) {
//	    boolean[] seen = new boolean[10]; // Assuming Sudoku values are 1-9
//	    int count = 0;
//
//	    for (int i = 0; i < array.length; i++) {
//	        int current = array[i];
//	        if (!containedIn(current, seen)) {
//	            seen[current] = true;
//	            count++;
//	        }
//	    }
//
//	    int[] newArray = new int[count];
//	    int index = 0;
//
//	    for (int i = 1; i < seen.length; i++) {
//	        if (seen[i]) {
//	            newArray[index++] = i;
//	        }
//	    }
//
//	    return newArray;
//	}
	
	//Elimina los elementos repetidos y los 0s de una lista unidimensional
	public static int[] getRidRepeated (int[] array) {
		//List<Integer> newArray = new ArrayList<Integer>();
		int[] newArray = {};
		int cont = 0;
		int contNumNoCero = 0;
		int[] finalArray = {};
		for(int i=0; i<array.length; i++) {
			if(!containedIn(array[i], newArray)){
				//newArray.add(array[i]);
				newArray = new int [cont];
				//newArray[cont] = array[i];
				cont++;
			}
		}
		// ISSUE -------------------------------------
		cont = 0;
		for(int i=0; i<array.length; i++) {
			if(!containedIn(array[i], newArray)){
				if(array[i]!=0) contNumNoCero++;
				newArray[cont] = array[i];
				cont++;
			}
		}
		//--------------------------------------------
		finalArray = new int[contNumNoCero];
		
		for(int i=0; i<finalArray.length; i++) {
			finalArray[i] = newArray[i];
		}
		
		return finalArray;
	}
	
	public static int restante (int[] array) {
		
		int sol = 0;
		
		if(array.length == 8) {
			for(int i=1; i<10; i++) {
				if(!containedIn(i, array)) {
					sol = i;
					break;
				}	
			}
		}
		return sol;
	}
	
	
	public static void printArray (int [][] array) {		
		for (int i=0; i<array.length; i++) {
			for (int j=0; j<array[i].length; j++) {
				System.out.print(array[i][j]+" ");
				if((j+1)%3==0) System.out.print("  ");
			}
		if((i+1)%3==0) System.out.println();
		System.out.println();
		}
	}
	
	public static int[][] doSudoku (int[][] sudoku){
		
		boolean finish = false;
		int cont = 0;
		
		while(!finish && cont <= 500) {
	
			finish = true;
				
			for(int i=0; i<sudoku.length; i++) {
				for(int j=0; j<sudoku[i].length; j++) {
					if(sudoku[i][j] == 0) {
						if(sudoku[i][j] == 0) finish = false;
						int rest = restante(getRidRepeated(fillSquare(sudoku, i, j)));
						sudoku[i][j] = rest;
					}
				}
			}
			cont++;
		}
		return sudoku;
	}
	
	public static void main(String[] args) {
		
		int[][] sudoku = {
				{0,2,0, 0,0,9, 8,0,0},
				{0,4,9, 2,5,0, 6,7,0},
				{1,0,5, 7,4,0, 2,0,0},
				
				{0,0,0, 3,7,0, 5,1,8},
				{0,3,0, 8,0,6, 0,4,0},
				{4,7,8, 0,9,5, 0,0,0},
				
				{0,0,6, 0,8,7, 1,0,3},
				{0,1,7, 0,3,2, 4,8,0},
				{0,0,4, 9,0,0, 0,2,0}
				};
		
//		for(int i=0; i<sudoku.length; i++) {
//			for(int j=0; j<sudoku[i].length; j++) {
//				if(sudoku[i][j] == 0) {
//					int rest = restante(getRidRepeated(fillSquare(sudoku, i, j)));
//					System.out.print(rest + " ");
//					sudoku[i][j] = rest;
//				}
//			}System.out.println();
//		}
		
		int[][] newArray = getSudokuSquare(sudoku, 0/3, 1/3);
		//int[] fill = fillSquare(sudoku);
		printArray(newArray);
		
//		System.out.println(Arrays.deepToString(newArray));
//		System.out.println(Arrays.toString(fillSquare(sudoku, 0, 0)));
//		System.out.println(Arrays.toString(getRidRepeated(fillSquare(sudoku, 0, 0))));
//		System.out.println(Arrays.deepToString(sudoku));
		printArray(sudoku);
		System.out.println("------------------------");
		printArray(doSudoku(sudoku));

	}
	
}


