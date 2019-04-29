package ast;

import java.util.ArrayList;
import java.util.List;

public class Program {
	private List<Sor> sorok = new ArrayList<Sor>();
	private double memory = 0.0;
	public void add(Sor s) {
		sorok.add(s);
	}
	public void evaluate() {
		for (Sor s : sorok) {
			s.evaluate();
		}
	}
	public void setMemory(double value) {
		this.memory = value;
	}
	public double getMemory() {
		return this.memory;
	}
}
