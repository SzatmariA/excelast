package ast;

public class Unary extends Expression {
	private String op;
	private Expression node;
	public Unary() {
		this.op = null;
		this.node = null;
	}
	public Unary(String op, Expression e) {
		this.op = op;
		this.node = e;
	}
	public String evaluate(Program p) {
		/*if (this.node == null) {
			return p.getMemory();
		}
		double val = this.node.evaluate(p);
		switch (this.op) {
			case "+": return val;
			case "-": return -val;
			case "abs": return (val < 0) ? -val : val;
		}*/
		return "0.0";
	}
}
