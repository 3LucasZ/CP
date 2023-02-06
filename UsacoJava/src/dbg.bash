source $HOME/.zshrc
echo "Starting Script"
A=$CP/UsacoJava/src
echo "CP:" $CP "A:" $A
echo "Running Generator"
java $A/Generator.java
echo "Running Sol"
java $A/Sol.java
echo "Running your code"
java $A/FindAndReplace.java > $CP/io/dbg.txt
echo "Running diff"
diff $CP/io/out.txt $CP/io/cmp.txt > $CP/io/dif.txt