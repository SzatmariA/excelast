grammar Excel;

@members {
    int row=1;
    char column = 'A';
    public static void main(String[] args) throws Exception {
         ExcelLexer lex =       new ExcelLexer(new ANTLRFileStream(args[0]));
         CommonTokenStream tokens = new CommonTokenStream (lex);
         ExcelParser parser = new ExcelParser(tokens);
         ast.Program p = new ast.Program();
         parser.start(p);
         p.evaluate();
    }
}

start [ast.Program p]
	: ((cells[p, column, row] { 
		column += 1;
		$p.add($cells.node); 
		} ';')* '\n'
		{	
			column = 'A';
			row++;
			
		})*
	;
cells [ast.Program p, char column, int row] returns [ ast.Cell node]
	: '=' expr { $node = new ast.Cell($p, $expr.node, ""+column+row); }
	| last {$node = new ast.Cell($p, $last.node, ""+column+row);}
	;
expr returns [ ast.Expression node ]
	: STRING { $node = new ast.Const($STRING.text); } (ADDOP op2=member { $node = new ast.Binary($ADDOP.text, $node, $op2.node); })* 
	| member { $node = $member.node; }
	;
member returns [ast.Expression node]
	: op1=fact { $node = $op1.node; } (ADDOP op2=fact { $node = new ast.Binary($ADDOP.text, $node, $op2.node); })* 
	;

fact returns [ast.Expression node]
	: op1=smthg { $node = $op1.node; } (MULOP op2=smthg { $node = new ast.Binary($MULOP.text, $node, $op2.node); })*
	;

smthg returns [ast.Expression node]
	: last {$node = $last.node;} 
	| '(' expr { $node = $expr.node; } ')'
	| CELL {$node = new ast.Const($CELL.text);}
	| SUMOP '(' CELL ':' CELL ')'
	;

last returns [ast.Expression node]
	: NUMBER { $node = new ast.Const($NUMBER.text); }
	| STRING { $node = new ast.Const($STRING.text);}
	;

NUMBER : [0-9]+ ('.' [0-9])*;
STRING : '"' ([a-zA-Z0-9=: ])* '"';
ADDOP : '+' | '-';
MULOP : '*' | '/';
SUMOP : 'SUM';
CELL : [A-Z][0-9]+;
WS : [ \t\r]+ -> skip;
