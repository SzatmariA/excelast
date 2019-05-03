package ast;

public class Const extends Expression {
	
	private String sValue;
	
	public Const(String value) {
		this.sValue = value;
	}
	public String evaluate(Program p) {
		return this.sValue;
	}
}
