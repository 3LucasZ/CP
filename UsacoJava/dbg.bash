#!/usr/bin/env bash

source $HOME/.zshrc
echo "Starting FULL DEBUG Script"
A=$CP/UsacoJava/src
echo "CP:" $CP
echo "A:" $A

echo "Running Generator"
java $A/Generator.java
#
#echo "Running Sol"
#cat $CP/io/in.txt | java $A/Sol.java > $CP/io/cmp.txt

echo "Running your code"
cat $CP/io/in.txt | java $A/LightsOff.java > $CP/io/out.txt
#
#echo "Running diff"
#diff $CP/io/out.txt $CP/io/cmp.txt > $CP/io/dif.txt