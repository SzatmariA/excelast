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
	public double evaluate(Program p) {
		double lhs = this.lhsNode.evaluate(p);
		double rhs = this.rhsNode.evaluate(p);
		switch (this.op) {
			case "+": return lhs + rhs;
			case "-": return lhs - rhs;
			case "*": return lhs * rhs;
			case "/": return lhs / rhs;
			case "^": return Math.pow(lhs, rhs);
			case "min": return lhs < rhs ? lhs : rhs;
			case "max": return lhs > rhs ? lhs : rhs;
		}
		return 0.0;
	}
}
