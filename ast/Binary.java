package ast;

import java.util.ArrayList;
import java.util.List;

public class Binary extends Expression {
	private String op;
	private String ownKey;
	private Expression lhsNode;
	private Expression rhsNode;
	private String key1 = "";
	private String key2 = "";
	public Binary(String op, Expression lhs, Expression rhs) {
		this.op = op;
		this.lhsNode = lhs;
		this.rhsNode = rhs;
	}
	
	public Binary(String op, String ownKey, String lhs, String rhs) {
		this.op = op;
		this.key1 = lhs;
		this.key2 = rhs;
		this.ownKey = ownKey;
	}
	
	
	
	
	
	public String evaluate(Program p) {
		if(!key1.isEmpty()) {
			//This can only be the SUM
			double value = 0.0; //Ez double lesz.
			List<Cell> cells = new ArrayList<Cell>();
            cells = p.getCells(this.key1, this.key2, this.ownKey);
			try {		
				for(Cell c : cells) {
					//Ide is kell majd az also, mert igy csak konkatenal
					value +=  Double.parseDouble(c.evaluate());
				}
			}catch (NumberFormatException e) {
				System.out.println("Invalid number format in: " + ownKey);
			}
			
			return "" + value;
		}
		else {
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
				try {
					double rhs = Double.parseDouble(this.rhsNode.evaluate(p));
					switch (this.op) {
						case "+": return lhs +rhs;
					}
				}
				catch(NumberFormatException n) {
					String rhs = this.rhsNode.evaluate(p);
					switch (this.op) {
						case "+": return lhs +rhs;
					}
					
				}
			}
		}
		return "0.0";
	}
}
