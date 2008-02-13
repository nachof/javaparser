BINDIR=bin
SRCDIR=src


run: $(BINDIR) compile
	java -classpath $(BINDIR) Test

$(BINDIR):
	mkdir $(BINDIR)

compile: $(BINDIR) clean
	javac -d $(BINDIR) -sourcepath $(SRCDIR) -g -target 1.4 -source 1.4 $(SRCDIR)/Test.java 

clean: $(BINDIR)
	rm -f `find $(BINDIR) -name '*.class'`

