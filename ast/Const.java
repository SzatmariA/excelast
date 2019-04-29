package ast;

public class Const extends Expression {
	private double value;
	public Const(double value) {
		this.value = value;
	}
	public double evaluate(Program p) {
		return this.value;
	}
}
