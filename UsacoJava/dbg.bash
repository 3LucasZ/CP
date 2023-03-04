#!/bin/zsh

#only change these lines
fileName=IntersectionAndUnion.java


source ~/.zshrc

echo Starting FULL DEBUG Script
A=$CP/UsacoJava/src
echo CP: $CP
echo A: $A

echo Running the generator
java $A/Generator.java > $CP/io/in.txt
head -n 1000 $CP/io/in.txt > $CP/io/in_less.txt

echo Running correct code against in.txt
cat $CP/io/in.txt | java $A/Sol.java > $CP/io/cmp.txt

echo Running your code against in.txt
source ~/.zshrc
ms0=$(getms)
cat $CP/io/in.txt | java $A/$fileName > $CP/io/out.txt
ms1=$(getms)
echo Your code took $((ms1-ms0)) ms

echo Running diff between your output and the correct output
diff $CP/io/out.txt $CP/io/cmp.txt > $CP/io/dif.txt