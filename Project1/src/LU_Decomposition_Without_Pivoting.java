//-----------------------------------------//
// MATH226 - Numerical Methods for EE
// Project 01
//
// Name-Surname: Utku Mutlu
// Student ID: 041502031
//-----------------------------------------// 
import java.io.File;
import java.util.Scanner;

public class LU_Decomposition_Without_Pivoting {

	private static Scanner input;//I implement our input for scanning.


	public static void main(String[] args) throws Exception {
		float a[][] = null;//I implement our A matrix.
		int i=0; int j=0; int k=0;
		float x=0;
		int size;
		float det=1;
        File file = new File("A.txt");//Define file=matrixFile.txt
		
        if (!file.exists())//This "if" check matrixFile.txt exist or not. If matrixFile.txt does not exist, prints message.
			System.out.println("File does not exists\n");
		input = new Scanner(file);//Create input which is equal to file.
		
		
		
		while (input.hasNextLine()) {//This while loop continues when input has more line.

			String[] s = input.nextLine().split(" ");//I created string array which takes input's line.
			if(i==0) {//I splitted creating A matrix because I want to know a.lenght in first part and create size of A matrix.
				a = new float[s.length][s.length];
				for(j=0; j<s.length; j++) {
					a[0][j] = Float.parseFloat(s[j]);
				}
			}
			else {
				for(j=0;j<s.length;j++) {
					a[i][j] = Float.parseFloat(s[j]);
				}

			}
			i++;
		}
		
		
		size=a.length;
		float l_without_pivoting[][]= new float [size][size];//Create L matrix.
		float u_without_pivoting[][]= new float [size][size];//Create Create U matrix.
		
		for(i=1; i<=size; i++) {//This for loop do lu decomposition with using doolittle algorithm.
			for(j=1; j<=i-1; j++) {
				x = a[i-1][j-1];
				for(k=1; k<=j-1; k++) {
					x = x-a[i-1][k-1]*a[k-1][j-1];
				}
				a[i-1][j-1]=x/a[j-1][j-1];
			}
			for(j=i; j<=size; j++) {
				x = a[i-1][j-1];
				for(k=1; k<=i-1; k++) {
					x=x-a[i-1][k-1]*a[k-1][j-1];
				}
				a[i-1][j-1]=x;
			}
		}


		for(i=0; i<size; i++) {//This for loops split L and U Matrices.
			for(j=0; j<size; j++) {
				if(i>j) {
					l_without_pivoting[i][j]=a[i][j];
					u_without_pivoting[i][j]=0;
				}
				else if(i==j) {
					l_without_pivoting[i][j]=1;
					u_without_pivoting[i][j]=a[i][j];
				}
				else {
					l_without_pivoting[i][j]=0;
					u_without_pivoting[i][j]=a[i][j];
				}
			}
		}

		for(i=0;i<a.length;i++) {// This loop complutes determinant of U.
			det=det*u_without_pivoting[i][i];
		}
		
		
		if(det==0) {//This "if" checks matrix is singular or not singular.
			System.out.println("This matrix is singular.");
		}
		
		else {
			System.out.println("L Matrix (Solved without partial pivoting) =");
			for(i=0; i<size; i++) {//This loop prints L matrix.
				for(j=0; j<size; j++) {
					System.out.print(l_without_pivoting[i][j] + "  ");
				}
				System.out.println();
			}

			System.out.println();
			System.out.println("U Matrix (Solved without partial pivoting) =");
			for(i=0; i<size; i++) {//This loop prints U matrix.
				for(j=0; j<size; j++) {
					System.out.print(u_without_pivoting[i][j] + "  ");
				}
				System.out.println();
			}
		}
	}

}
