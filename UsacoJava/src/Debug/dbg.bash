#!/bin/zsh
source ~/.zshrc

#only change these lines
fileName=ComfortablyNumb.java
gen=true
cmp=false

#Setup
echo Starting Script...
A=$CP/UsacoJava/src
echo CP: $CP
echo A: $A

#Generator
if [ "$gen" = true ] ; then
  echo Running the generator
  java $A/Debug/Generator.java > $CP/io/in.txt
fi
head -n 1000 $CP/io/in.txt > $CP/io/in_less.txt

#Your code
echo Running your code against in.txt
ms0=$(getms)
cat $CP/io/in.txt | java $A/$fileName > $CP/io/out.txt
ms1=$(getms)
echo Your code took $((ms1-ms0)) ms

#Correct code
if [ "$cmp" = true ] ; then
  echo Running correct code against in.txt
  cat $CP/io/in.txt | java $A/Debug/Sol.java > $CP/io/cmp.txt

  echo Running diff between your output and the correct output
  diff $CP/io/out.txt $CP/io/cmp.txt > $CP/io/dif.txt
fi