grammar excel;

@members {
    
    public static void main(String[] args) throws Exception {
         ExcelLexer lex =       new ExcelLexer(new ANTLRFileStream(args[0]));
         CommonTokenStream tokens = new CommonTokenStream (lex);
         ExcelParser parser =   new ExcelParser(tokens);
         ast.Program p = new ast.Program();
         parser.start(p);
         //p.evaluate();
    }
}

csv [ast.Program p]
	: ((cells[p] { $p.add($cells.node); } ';')* '\n')*
	;
cells [ast.Program p] returns [ ast.Cell node]
	: '=' expr 
	| last
	;
expr returns [ ast.Expression node ]
	: STRING (ADDOP member)*
	| member
	;
member returns [ast.Expression node]
	: fact (ADDOP fact)*
	;

fact returns [ast.Expression node]
	: smthg ( MULOP smthg)*
	;

smthg returns [ast.Expression node]
	: last 
	| '(' expr ')'
	| CELL
	| SUMOP '(' CELL ':' CELL ')'
	;

last returns [ast.Expression node]
	: NUMBER
	| STRING
	;

NUMBER : [0-9]+;
STRING : '"' [a-zA-Z0-9]* '"';
ADDOP : '+' | '-';
MULOP : '*' | '/';
SUMOP : 'SUM';
CELL : [A-Z][0-9]+;
WS : [ \t\r]+ -> skip;
