import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
import java.util.Scanner;


public class Cryptography {
	
	private static String fileName = "output.txt";
	private static FileWriter fileWriter; 
	private static BufferedWriter bufferedWriter; 
	
	public static Code encryptor(String msg) {
		if (msg.length()%3!=0){
			for(int r=0; r<msg.length()%3; r++){
				msg = msg + ".";
			}
		}
		System.out.println("I read your message as: " + "\"" + msg + "\"");
		int xColumns = 3;
		int yRows = msg.length()/3;
		Matrix encode = new Matrix(yRows, xColumns, msg);
		Matrix random = new Matrix(3);
		Matrix encrypt = encode.matrixMult(random);
		String hiddenMes = encrypt.readEncMatrix();
		String key = random.readMatrix();
		Code myCode = new Code(hiddenMes,key,yRows);
		return myCode;
	}
	
	public static String decryptor(Code givenCode){
		Matrix inputMessage = new Matrix(givenCode.getRows(),3,givenCode.getMes(),true);//need column dimension as variable
		Matrix key = new Matrix(3,3,givenCode.getKey(),1);
		Matrix decrypt = inputMessage.matrixMultDouble(key.matrixInverse());
		String outputMessage = decrypt.decodeMatrix();
		return outputMessage;
	}
	
	public static void main(String[] args){
		try {
			fileWriter = new FileWriter(fileName);
			bufferedWriter = new BufferedWriter(fileWriter);
		} catch (IOException e) {
			System.out.println("Failed to open FileWriter");
			e.printStackTrace();
		}
		
		System.out.println("--------------------------------------------------" +
				"\nWelcome to the Alberti Machine" + "\n\nPlease select and option from the menu" +
				"\n1) Encrypt a message" + "\n2) Decrypt a message" + "\n\nEnter selection here:");
		Scanner userInput = new Scanner(System.in);
		int select = userInput.nextInt();
		if (select == 1){
			System.out.println("\n--------------------------------------------------");
			System.out.println("\nYou have selected to encrypt a message!");
			System.out.println("Please enter your message here:");
			userInput.nextLine(); // clear newline character
			String inputMessage = userInput.nextLine();
			Code myCode = encryptor(inputMessage);
			String message = myCode.getMes();
			String key = myCode.getKey();
			int rows = myCode.getRows();
			System.out.println("\n--------------------------------------------------");
			System.out.println("\nEncryption: " + message + "\nKey: " + key + "\nRows: " + rows);
			try {
				bufferedWriter.write(message);
				bufferedWriter.newLine();
				bufferedWriter.write(key);
				bufferedWriter.newLine();
				bufferedWriter.write("" + rows);
				bufferedWriter.close();
			} catch (IOException e) {
				System.out.println("Write error");
				e.printStackTrace();
			}
				
			//test
		} else if (select == 2){
			System.out.println("\n--------------------------------------------------");
			System.out.println("\nYou have selected to decrypt a message!");
			System.out.println("Please enter your message here:");
			String clearInput = userInput.nextLine();
			String inputMessage = userInput.nextLine();
			System.out.println("Please enter your key here:");
			String inputKey = userInput.nextLine();
			System.out.println("Please enter your rows here:");
			int inputRows = userInput.nextInt();
			System.out.println("I read your input as: " + inputMessage + ", " + inputKey + ", " + inputRows);
			Code myCode = new Code(inputMessage, inputKey, inputRows);
			String outputMessage = decryptor(myCode);
			System.out.println("\n--------------------------------------------------");
			System.out.println("\nDecryption: " + outputMessage);
			try {
				bufferedWriter.write(outputMessage);
				bufferedWriter.close();
			} catch (IOException e) {
				System.out.println("Write error");
				e.printStackTrace();
			}
		}
	}
}
