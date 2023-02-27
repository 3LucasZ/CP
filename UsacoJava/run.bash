#!/bin/zsh

source ~/.zshrc

echo Starting RUN DEBUG Script
A=$CP/UsacoJava/src
echo CP: $CP
echo A: $A

echo Running your code against in.txt
cat $CP/io/in.txt | java $A/FindAndReplace.java > $CP/io/out.txt

#echo "Running correct code against in.txt"
#cat $CP/io/in.txt | java $A/Sol.java > $CP/io/cmp.txt
#
#echo "Running diff between your output and the correct output"
#diff $CP/io/out.txt $CP/io/cmp.txt > $CP/io/dif.txt