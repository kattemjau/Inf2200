FILE=buildhouse1.txt


run: compile
	java Oblig2 $(FILE)
clean:
	rm -r *.class
compile:
	javac *.java
git:
	git add .
	git commit -m "something more"
	git push

pack:
	tar -cvf sindrech.tgz ../Oblig2
