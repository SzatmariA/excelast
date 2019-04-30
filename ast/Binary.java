package ast;

public class Binary extends Expression {
	private String op;
	private Expression lhsNode;
	private Expression rhsNode;
	public Binary(String op, Expression lhs, Expression rhs) {
		this.op = op;
		this.lhsNode = lhs;
		this.rhsNode = rhs;
	}
	public String evaluate(Program p) {
		
		try {
			double lhs = Double.parseDouble(this.lhsNode.evaluate(p));
			double rhs = Double.parseDouble(this.rhsNode.evaluate(p));
			switch (this.op) {
				case "+": return String.valueOf(lhs + rhs);
				case "-": return String.valueOf(lhs - rhs);
				case "*": return String.valueOf(lhs * rhs);
				case "/": return String.valueOf(lhs / rhs);
			}
		}catch(NumberFormatException e) {
			String lhs = this.lhsNode.evaluate(p);
			double rhs = Double.parseDouble(this.rhsNode.evaluate(p));
			switch (this.op) {
				case "+": return lhs +rhs;
			}
		}
		
		return "0.0";
	}
}
