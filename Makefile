BINDIR=bin
SRCDIR=src

run: compile
	java -classpath $(BINDIR) Test

compile: clean
	javac -d $(BINDIR) -sourcepath $(SRCDIR) -g -target 1.4 -source 1.4 $(SRCDIR)/Test.java 

clean:
	rm -f `find $(BINDIR) -name '*.class'`

