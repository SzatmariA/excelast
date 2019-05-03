package ast;

public class Unary extends Expression {
	private String key;
	private String ownKey;
	
	public Unary(String key, String ownKey) {
		this.key = key;
		this.ownKey = ownKey;
	}
	public String evaluate(Program p) {
		//TODO: NullPointerException ha nem letezore probal hivatkozni
		/*if (this.node == null) {
			return p.getMemory();
		}
		
		double val = this.node.evaluate(p);
		switch (this.op) {
			case "+": return val;
			case "-": return -val;
			case "abs": return (val < 0) ? -val : val;
		}*/
		
		
		try{
			if(ownKey.equals(key))
				throw new NullPointerException();
			p.addReference(ownKey,key);	
			if(ownKey.equals(p.getReferenced(key))) {
				throw new NullPointerException();
			}
			Cell c = p.getCell(this.key);
			return c.evaluate();
		}catch(NullPointerException e) {
			System.out.println("Invalid reference in: "  + ownKey);
		}	
		return "";
		
	}
}
