package ast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Program {
	private HashMap<String, Cell> cells = new HashMap<String, Cell>();
	private double memory = 0.0;
	public void add(Cell c) {
		cells.put(c.getlabel(), c);
	}
	public void evaluate() {
		
		cells.forEach((k,v) -> System.out.println("key: "+k+" value:"+v.evaluate()));
		
	}
	public void setMemory(double value) {
		this.memory = value;
	}
	public double getMemory() {
		return this.memory;
	}
}
