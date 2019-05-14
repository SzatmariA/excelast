# Generate excel-like csv into html table
This project uses  [ANTLR's java](https://github.com/antlr/antlr4/blob/master/doc/java-target.md) version
Click the link to read more.


**Usage**

First download the [ANTLR Toolset](https://www.antlr.org/download/antlr-4.7.2-complete.jar).

Change the input.csv the way you'd use an excel table. You need to type *=* before expressions and functions.
 * SUM: you can summorize numbers and expressions in your csv with this function.
 * A1: you can target any existing cell you want.

E.g. =SUM(A1:C1)

Look at the input.csv for further examples.


After you changed the input.csv you need to run the makefile again with the command *make* 
