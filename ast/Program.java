package ast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class Program {
	private LinkedHashMap<String, Cell> cells = new LinkedHashMap<String, Cell>();
	private HashMap<String, String> references = new HashMap<String, String>();
	
	
	public void add(Cell c) {
		cells.put(c.getlabel(), c);
	}
	
	public void addReference(String ownKey, String referenced) {
		references.put(ownKey, referenced);
	}
	
	
	public String getReferenced(String key) {
		//Ezzel megtudom, hogy amire hivatkozok mire hivatkozik.
		return references.get(key);
	}
	
	public void evaluate() {
		int i = 1;
		//cells.forEach((k,v) -> System.out.println("key: "+k+" value:"+v.evaluate()));
		
		
		StringBuilder buf = new StringBuilder();
		buf.append("<html>" +
		           "<body>" +
		           "<table>"
		           );

		for(Map.Entry<String, Cell> entry : cells.entrySet()) {
		    String key = entry.getKey();
		    Cell v = entry.getValue();
		    if(!key.contains("" +i)){
	        	++i;
	        	buf.append("<tr>");
	        }
		    buf.append("<td>")
		       .append("<b>" + key + ":</b>")
		       .append(" ")
		       .append(v.evaluate())
		       .append("</td>");
		}
		
		buf.append("</table>" +
		           "</body>" +
		           "</html>");
		String html = buf.toString();
		try {
			File newHtmlFile = new File(Paths.get(".").toAbsolutePath().normalize().toString() + "/new.html");
			newHtmlFile.createNewFile();
			FileWriter fileWriter = new FileWriter(newHtmlFile);
			fileWriter.write(html);
			fileWriter.flush();
			fileWriter.close();
		} catch (IOException e) {
			//System.out.println(Paths.get(".").toAbsolutePath().normalize().toString() + "/new.html");
			e.printStackTrace();
		}
		
	}
	public Cell getCell(String key) {
		return cells.get(key);
	}
	
	public List<Cell> getCells(String key1, String key2, String ownKey){
		char cell1Label= key1.substring(0,1).charAt(0);
		char cell2Label= key2.substring(0,1).charAt(0);
		int cell1Num = Integer.parseInt(key1.substring(1, key1.length()));
		int cell2Num = Integer.parseInt(key2.substring(1, key2.length()));
		List<Cell> cellz = new ArrayList<Cell>();
		
		if(cell1Label == cell2Label) {
			int i = 1;
			for(Map.Entry<String, Cell> entry : cells.entrySet()) {
			    String key = entry.getKey();
			    Cell v = entry.getValue();
			    if(i < cell1Num) {
					++i;
					
					continue;
				}
				if(key.contains("" + cell1Label + i)) {
					if(!key.equals(ownKey)) {
							cellz.add(v);
					}
					if(i == cell2Num) break;
					++i;
					
				}
			}
		}
		else if (cell1Num == cell2Num) {
			char i = 'A';
			for(Map.Entry<String, Cell> entry : cells.entrySet()) {
			    String key = entry.getKey();
			    Cell v = entry.getValue();
			    if(key.contains("" + cell1Num)) {
					++i;
					if(!key.equals(ownKey)) {
						cellz.add(v);
					}
					if(key.equals(key2)) break;
				}
			}
		}
		else {
			char a = 'A';
		    int i = 1;		    
		    
		   
		 
		    for(Map.Entry<String, Cell> entry : cells.entrySet()) {
		    	String key = entry.getKey();
				Cell v = entry.getValue();
		        if(!key.contains("" +a)) ++a;
		     	if(!key.contains("" +i)){
		       		a = 'A';
		        	++i;
		        }
		      
		      if(i < cell1Num){
		          continue;
		      }
		      else if(i == cell1Num && a < cell1Label){
		      	continue;
		      }
		      
		   	 
		      	
		      	
		       // System.out.println(" " + key + " " + entry.getValue() + "=======>" + a + i);
		        if(!key.equals(ownKey)) {
					cellz.add(v);
				}
		        if(key.equals(key2))
		        	break;
		    }
		}
		
		return cellz;
	}
	
	
	
}
