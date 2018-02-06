
public class Code {
	
	private String encMes;
	private String encKey;
	private int encRows;
	//private int shift;
	
	public Code(String encMes, String encKey, int encRows) {
		this.encMes = encMes;
		this.encKey = encKey;
		this.encRows = encRows;
		//this.shift = shift;
	}
	
	public String getMes(){
		return encMes;
	}
	
	public String getKey(){
		return encKey;
	}
	
	
	public int getRows(){
		return encRows;
	}
	
}
