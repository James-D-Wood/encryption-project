import java.util.Random;
import java.text.DecimalFormat;

public class Matrix {
	private int x;
	private int y;
	private String msg;
	public int[][] myMatrix;
	public double[][] doubleMatrix;
	private DecimalFormat output = new DecimalFormat("0.000000000");
	
	/*----------------- Constructors -----------------*/
	
	//Constructs empty matrix of given dimensions
	public Matrix(int rows, int columns){
		y = rows;
		x = columns;
		int[][] blankMatrix = new int[y][x];
		myMatrix = blankMatrix;
	}
	
	public Matrix(int rows, int columns, boolean placeholder){
		y = rows;
		x = columns;
		double[][] blankMatrix = new double[y][x];
		doubleMatrix = blankMatrix;
	}
	
	//Constructs square matrix filled with integer values between 1 and 9
	public Matrix(int randRowCol) {
		x = randRowCol;
		y = randRowCol;
		int[][] randMatrix = new int[y][x];
		myMatrix = randMatrix;
		Random randGen = new Random();
		for (int j = 0; j < y; j++){
			for (int k = 0; k < x; k++) {
				myMatrix[j][k] = randGen.nextInt(9)+1;
			}
		}
	}
	
	//Constructs matrix and fills with string
	public Matrix(int rows, int columns, String myMsg) {
		x = columns; //sets columns
		y = rows; //sets matrix rows
		msg = myMsg; 
		int[][] encodeMatrix = new int[y][x]; //builds matrix
		myMatrix = encodeMatrix;
		int[] brokenMsg = new int[msg.length()]; //declares array for message to fill 
		for (int i = 0; i<msg.length(); i++) {
			brokenMsg[i] = (int)msg.charAt(i);			
		}
		int count = 0;
		for (int j = 0; j < y; j++){
			for (int k = 0; k < x; k++) {
				myMatrix[j][k] = brokenMsg[count];
				count++;
			}
		}	
	}
	
	//Constructs matrix and fills with mes
	public Matrix(int rows, int columns, String myMsg, Boolean alt) {
		x = columns; //sets columns
		y = rows; //sets matrix rows
		msg = myMsg; 
		int[][] encodeMatrix = new int[y][x]; //builds matrix
		myMatrix = encodeMatrix;
		int[] brokenMsg = new int[msg.length()]; //declares array for message to fill 
		for (int i = 0; i<msg.length(); i++) {
			brokenMsg[i] = msg.charAt(i)-48;			
		}
		int count = 0;
		String[][] pieceTogether = new String[y][x];
		/*System.out.println("\nMessage:");*/
		for (int j = 0; j < y; j++){
			for (int k = 0; k < x; k++) {
				pieceTogether[j][k] = brokenMsg[4*count]+""+brokenMsg[4*count+1]+""+
						brokenMsg[4*count+2]+""+brokenMsg[4*count+3];
				/*System.out.println(j + " " + k+ " " + pieceTogether[j][k]);*/
				count++;
				myMatrix[j][k] = Integer.parseInt(pieceTogether[j][k]);
			}
		}	
	}
	
	//Constructs matrix and fills with key
	public Matrix(int rows, int columns, String myMsg, int alt) {
		x = columns; //sets columns
		y = rows; //sets matrix rows
		msg = myMsg; 
		int[][] encodeMatrix = new int[y][x]; //builds matrix
		myMatrix = encodeMatrix;
		int[] brokenMsg = new int[msg.length()]; //declares array for message to fill 
		for (int i = 0; i<msg.length(); i++) {
			brokenMsg[i] = msg.charAt(i)-48;			
		}
		int count = 0;
		String[][] pieceTogether = new String[y][x];
		/*System.out.println("\nKey:");*/
		for (int j = 0; j < y; j++){
			for (int k = 0; k < x; k++) {
				myMatrix[j][k] = brokenMsg[count];
				count++;
				/*System.out.println(j + " " + k + " " + myMatrix[j][k]);*/
			}
		}	
	}
				
		
	//helper constructor for inverse
	public Matrix(int rows, int columns, int[] myNum) {
		x = columns; //sets columns
		y = rows; //sets matrix rows 
		int[][] encodeMatrix = new int[y][x]; //builds matrix
		myMatrix = encodeMatrix; 
		int count = 0;
		for (int j = 0; j < y; j++){
			for (int k = 0; k < x; k++) {
				myMatrix[j][k] = myNum[count];
				count++;
			}
		}	
	}
	
	//Constructs a matrix based on the multiplication of current matrix and given matrix
	public Matrix matrixMult(Matrix cofactor) {
		Matrix product = new Matrix(this.getRows(), cofactor.getColumns());
		for (int e = 0; e < cofactor.getColumns(); e++){
			for (int f = 0; f < this.getRows(); f++) {
				for(int g = 0; g < this.getColumns(); g++) {
					product.myMatrix[f][e] += this.myMatrix[f][g]*cofactor.myMatrix[g][e];
					/*System.out.println(f+" "+e+" "+product.myMatrix[f][e]);*/
				}
			}
		}
		return product;
	}
	
	public Matrix matrixMultDouble(Matrix cofactor) {
		Matrix product = new Matrix(this.getRows(), cofactor.getColumns(), true);
		for (int e = 0; e < 3; e++){
			for (int f = 0; f < this.getRows(); f++) {
				for(int g = 0; g < this.getColumns(); g++) {
					product.doubleMatrix[f][e] += (double)this.myMatrix[f][g]*cofactor.doubleMatrix[g][e];
					/*System.out.println(f+" "+e+" "+product.doubleMatrix[f][e]);*/
				}
			}
		}
		Matrix productInt = new Matrix(product.getRows(), product.getColumns());
		for (int f = 0; f < product.getRows(); f++){
			for (int e = 0; e < product.getColumns(); e++) {
				productInt.myMatrix[f][e]=(int)Math.round(product.doubleMatrix[f][e]);
			}
		}
		/*System.out.println(productInt.readMatrix());*/
		return productInt;
	}
	
	//Constructs matrix based on inverse of current matrix
	//Only works for 3x3 matrices, will have to be rewritten if key size is changed
	public Matrix matrixInverse() {
		Matrix minor;
		int a,b,c,d = -1;
		int count = 0;
		int[] minorNum = new int[10];
		for (int i=0; i<3; i++) {
			for (int j=0; j<3; j++){
				a = -1;
				b = -1;
				c = -1;
				d = -1;
				int[] rowIndex = {0,1,2};
				int[] columnIndex = {0,1,2};
				removeInteger(rowIndex, i);
				if(stillThere(rowIndex, 0) && stillThere(rowIndex, 1)){
					a = 0;
					b = 1;
				} else if (stillThere(rowIndex, 0) && stillThere(rowIndex, 2)) {
					a = 0;
					b = 2;
				} else if (stillThere(rowIndex, 1) && stillThere(rowIndex, 2)) {
					a = 1;
					b = 2;
				}
				removeInteger(columnIndex, j);
				if(stillThere(columnIndex, 0) && stillThere(columnIndex, 1)){
					c = 0;
					d = 1;
				} else if (stillThere(columnIndex, 0) && stillThere(columnIndex, 2)) {
					c = 0;
					d = 2;
				} else if (stillThere(columnIndex, 1) && stillThere(columnIndex, 2)) {
					c = 1;
					d = 2;
				}
				minorNum[count]= this.myMatrix[a][c]*this.myMatrix[b][d]
						-this.myMatrix[a][d]*this.myMatrix[b][c];
				count++;
			}
		}
		for(int k=1; k<minorNum.length; k+=2) {
			minorNum[k] *= -1;
		}
		minor = new Matrix(3,3,minorNum);
		Matrix cofactor = new Matrix(3,3);
		for(int m=0; m<3; m++){
			for(int n=0; n<3; n++){
				cofactor.myMatrix[m][n] = minor.myMatrix[n][m];
			}
		}
		double detFactor = 1.0/((this.myMatrix[0][0]*minor.myMatrix[0][0]) +
				(this.myMatrix[0][1]*minor.myMatrix[0][1])+
				(this.myMatrix[0][2]*minor.myMatrix[0][2]));
		/*System.out.println("Determinant Factor: " + output.format(detFactor));*/
		Matrix inverse = new Matrix(3,3,true);
		for(int q=0; q<3; q++){
			for(int r=0; r<3; r++){
				inverse.doubleMatrix[q][r] = detFactor*cofactor.myMatrix[q][r];
				if (Double.isNaN(inverse.doubleMatrix[q][r])){
					System.out.println("Invalid key - please try again!");
					System.exit(0);
				}
			}
		}
		/*System.out.println("Inverse: " + inverse.readDoubleMatrix());*/
		return inverse;
	}
	
	public String decodeMatrix(){
		char[][] decodedMatrix = new char[this.y][this.x];
		for (int i=0; i<this.y; i++){
			for (int j=0; j<this.x; j++){
				int a = 0;
				a = this.myMatrix[i][j];
				decodedMatrix[i][j]=(char)a;
			}
		}
		String write2String = ""; 
		for (int j = 0; j < this.y; j++){
			for (int k = 0; k < this.x; k++) {
				write2String += decodedMatrix[j][k];
			}
		}
		return write2String;
		
	}
	
	public void removeInteger(int[] array, int intToRemove) {
		array[indexOf(array, intToRemove)] = -1;
	}
	
	private int indexOf(int[] array, int searchInt){
		int index = -1;
		for (int j = 0; j<array.length; j++) {
			if (array[j] == searchInt) {
				index = j;
			}
		}
		return index;
	}
	
	private boolean stillThere(int[] array, int searchInt) {
		boolean found = false;
		for (int j=0; j<array.length; j++){
			if(array[j] == searchInt){
				found = true;
			}
		}
		return found;
	}
	
	
	/*----------------- Access Methods -----------------*/
	public int getValue(int row, int column){
		return myMatrix[row][column];
	}
	
	public int getColumns(){
		return x;
	}
	
	public int getRows(){
		return y;
	}
	
	/*----------------- Mutator Method -----------------*/
	public void setValue(int row, int column, int value){
		myMatrix[row][column]=value;
	}
	
	/*----------------- Additional Methods -----------------*/
	//Converts matrix to string
	public String readMatrix() {
		String write2 = ""; 
		for (int j = 0; j < this.getRows(); j++){
			for (int k = 0; k < this.getColumns(); k++) {
				write2 += this.myMatrix[j][k];
			}
		}
		return write2;
	}
	
	public String readEncMatrix() {
		String write2 = "";
		String[][] placeholder = new String[this.getRows()][this.getColumns()];
		for (int j = 0; j < this.getRows(); j++){
			for (int k = 0; k < this.getColumns(); k++) {
				placeholder[j][k] = ""+this.myMatrix[j][k];
				while (placeholder[j][k].length()<4){
					placeholder[j][k] = "0"+placeholder[j][k];
				}
				/*System.out.println(placeholder[j][k]);*/
				write2 += placeholder[j][k];
			}
		}
		
		return write2;
	}
	
	public String readDoubleMatrix() {
		String write2 = ""; 
		for (int j = 0; j < this.getRows(); j++){
			for (int k = 0; k < this.getColumns(); k++) {
				write2 += this.doubleMatrix[j][k];
			}
		}
		return write2;
	}
}
