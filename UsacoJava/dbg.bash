#!/bin/zsh

source ~/.zshrc

echo Starting FULL DEBUG Script
A=$CP/UsacoJava/src
echo CP: $CP
echo A: $A

echo Running the generator
java $A/Generator.java

#echo "Running correct code against in.txt"
#cat $CP/io/in.txt | java $A/Sol.java > $CP/io/cmp.txt

echo Running your code against in.txt
source ~/.zshrc
ms0=$(getms)
cat $CP/io/in.txt | java $A/MaximumAnd.java > $CP/io/out.txt
ms1=$(getms)
echo Your code took $((ms1-ms0)) ms

#echo "Running diff between your output and the correct output"
#diff $CP/io/out.txt $CP/io/cmp.txt > $CP/io/dif.txt