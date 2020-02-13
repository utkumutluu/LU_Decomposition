//-----------------------------------------//
// MATH226 - Numerical Methods for EE
// Project 01
//
// Name-Surname: Utku Mutlu
// Student ID: 041502031
//-----------------------------------------// 

import java.io.File;
import java.util.Scanner;

public class LU_Decomposition_With_Partial_Pivoting {

	private static Scanner input;//I implement our input for scanning.
	public static void main(String[] args) throws Exception {
		
		float a[][] = null;//I implement our A matrix.
		int largest_row=0;//I used largest row to determine which rows are changed.
		float memory;    
		int i=0; int j=0; int k=0;
		float pivot;
		float elementary[][];
		float M[][]; //I used M for product out elementary matrix.
		float product[][];  //I used product for product out elementary matrix and A matrix.
        File file = new File("A.txt");//Define file=matrixFile.txt		
        
        
        if (!file.exists())//This "if" check matrixFile.txt exist or not. If matrixFile.txt does not exist, prints message.
			System.out.println("File does not exists\n");
		input = new Scanner(file);//Create input which is equal to file.


		while (input.hasNextLine()) { //This while loop continues when input has more line.

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
		
		
		elementary = new float[a.length][a.length];
		M = new float[a.length][a.length];
		product = new float[a.length][a.length];
		
		
		for(i=0;i<a.length; i++) { //I created unit matrix in this loop
			for(j=0; j<a.length; j++) {
				if(i==j) {
					elementary[i][j]=1;
				}
				else {
					elementary[i][j]=0;
				}
			}
		}

		for(j=0; j<a.length;j++) {  //I changed maximum value and our pivot on this loop.
			for(i=j; i<a.length; i++) {
				if(Math.abs(a[j][j])<Math.abs(a[i][j])) {
					largest_row=i;
					for(k=0; k<a.length; k++) {
						memory=a[j][k];
						a[j][k]=a[largest_row][k];
						a[largest_row][k]=memory;
					}
				}

			}
			
			
			//I tried to do find U matrix on this part, but I only change first row of A.
			
			/*pivot= a[j][j];
			for(k=j+1; k<a.length;k++) {
				elementary[k][j]=(-1)*a[k][j]/pivot;
				M[k][j]=elementary[k][j];
			}

			for(i=0;i<a.length;i++){
				for(j=0;j<a.length;j++){
					for(k=0;k<a.length;k++){
						product[i][j]=product[i][j]+elementary[i][k]*a[k][j];
					}
					a[i][j]=product[i][j];
				}
			}*/


		}
		for(i=0; i<a.length; i++) {//This for loop is printing our A matrix.
			for(j=0;j<a.length; j++) {
				System.out.print(a[i][j] + " ");
			}
			System.out.println();
		}

	}
}
