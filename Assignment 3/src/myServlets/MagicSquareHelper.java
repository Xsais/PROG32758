package myServlets;

class MagicSquareHelper {

	// method for creating magic square with user defined size
	public static int[][] createMagicSquare(int size) {

		int n = size;
		int i, j, k, l;
		int magicSquare[][] = new int[n][n]; 

		// Set all fields to zero
		for (i = 0; i < n; i++) {
			for (j = 0; j < n; j++) {
				magicSquare[i][j] = 0;
			}
		}

		// Create Odd size Magic Square
		if (n % 2 != 0) {
			i = 0;
			j = n / 2;
			k = 1;
			
			while (k <= n * n) {
				magicSquare[i][j] = k++;
				i--; // step upward
				j++; // step to the right

				if (i < 0 && j > n - 1) { // Condition for the top-right corner element
					i = i + 2;
					j--;
				}

				if (i < 0) // Wrapping around the row
					i = n - 1;

				if (j > n - 1) // Wrapping around the column
					j = 0;

				if (magicSquare[i][j] > 0) { // Cell is already full
					i = i + 2;
					j--;
				}
			}
		}

		// Create even size Magic Square
		else {
			k = 1;

			// Fill magic square from 1 till n*n 
			for (i = 0; i < n; i++) {
				for (j = 0; j < n; j++) {
					magicSquare[i][j] = k++;
				}
			}

			j = n - 1;

			for (i = 0; i < n / 2; i++) {
				// Swapping corner cells of primary diagonal
				l = magicSquare[i][i];
				magicSquare[i][i] = magicSquare[j][j];
				magicSquare[j][j] = l;

				// Swapping corner cells of secondary diagonal
				l = magicSquare[i][j];
				magicSquare[i][j] = magicSquare[j][i];
				magicSquare[j][i] = l;

				j--;
			}
		}
		return magicSquare;
	}
}
