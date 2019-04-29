package ast;

public class Sor {
	private boolean assign;
	private Expression expr;
	private Program program;
	public Sor(Program p, Expression e, boolean a) {
		this.program = p;
		this.expr = e;
		this.assign = a;
	}
	public void evaluate() {
		double value = expr.evaluate(this.program);
		if (this.assign) {
			program.setMemory(value);
		}
		System.out.println(value);
	}
}
