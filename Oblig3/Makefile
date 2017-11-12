n=100

all: compile run

run:
	java Oblig3 $(n)
compile:
	clear
	javac *.java
clean:
	rm -r *.class
	rm -r rapport.txt
rapport: clean compile
	java Oblig3 10 >> rapport.txt
	java Oblig3 100 >> rapport.txt
	java Oblig3 1000 >> rapport.txt
	java Oblig3 10000 >> rapport.txt
	java Oblig3 100000 >> rapport.txt
	java Oblig3 1000000 >> rapport.txt
	java Oblig3 10000000 >> rapport.txt
git:
	git commit -am "iniczhiated project"
	git push
