package ast;

public class Cell {
	private String label;
	private Expression expr;
	private Program program;
	public Cell(Program p, Expression e, String label) {
		this.program = p;
		this.expr = e;
		this.label = label;
	}
	public String evaluate() {
		String value = expr.evaluate(this.program);
		
		//System.out.println("ABC");
		return value.replaceAll("\"","");
	}
	
	public String getlabel() {
		return this.label;
	}
}
