GRAMMAR=excel
INPUT=input.txt

ifdef OS
    CPS := ;
else
    CPS := :
endif

run: $(GRAMMAR)Parser.class $(INPUT)
	java -cp .$(CPS)antlr-4.7.2-complete.jar $(GRAMMAR)Parser $(INPUT)

%.class: %.java
	javac -cp .$(CPS)antlr-4.7.2-complete.jar $<

$(GRAMMAR)Parser.java: $(GRAMMAR).g4
	java -jar antlr-4.7.2-complete.jar $<
