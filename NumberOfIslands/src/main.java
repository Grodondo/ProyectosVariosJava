import java.util.ArrayList;
import java.util.List;

public class main {
	
	static List<Integer> countIslas = new ArrayList<>();

	public static void main(String[] args) {
		
		String Islas[][] = {
			    {"1", "1", "1", "1", "0"},
			    {"1", "1", "0", "1", "0"},
			    {"1", "1", "0", "0", "0"},
			    {"0", "0", "0", "0", "0"},
			    {"0", "0", "0", "1", "1"}
			};
		
		int ni = numIslas(arrayToInteger(Islas));
		
		System.out.println("Nº islas: " + ni);
		for(int i=0; i<countIslas.size(); i++) {
			System.out.print(countIslas.get(i) + " - ");
		}
	}
	
	public static int numIslas (int grid[][]) {
		int numIslas = 0;
		//copia del array grid
		int[][] dGrid = new int[grid.length][grid[0].length];
		
		for(int i=0; i<grid.length; i++) 
			for(int j=0; j<grid[0].length; j++) 
				dGrid[i][j] = grid[i][j];
		
		for(int i=0; i<grid.length; i++) {
			for(int j=0; j<grid[0].length; j++) 
			{				
				if(grid[i][j] == 1) {
					countIslas.add(numTierras(i, j, dGrid));
					if(numTierras(i, j, grid) != 0) {
						numIslas++;
						
					}
				}
			}
		}
		
		return numIslas;
	}
	
	public static int numTierras(int i, int j, int grid[][]) {

	    if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 0) {
	        return 0;
	    }
	    
		int countTierras = 1;
		int[][] newGrid = grid;
		
		newGrid[i][j] = 0;
		
		countTierras += numTierras(i+1, j, newGrid);
		countTierras += numTierras(i, j+1, newGrid);
		countTierras += numTierras(i-1, j, newGrid);
		countTierras += numTierras(i, j-1, newGrid);
	
		return countTierras;
	}
	
	
	//Transforma un array de Strings n*n a uno de Inetegers 
	public static int[][] arrayToInteger(String array[][]) {
        int rows = array.length;
        int cols = array[0].length;
        int grid[][] = new int[rows][cols];
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = Integer.parseInt(array[i][j]);
            }
        }
        return grid;
	}
}
