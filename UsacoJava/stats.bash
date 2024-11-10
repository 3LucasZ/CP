#!/bin/zsh
source ~/.zshrc

echo Starting Script...
CP="/Users/lucaszheng/Documents/GitHub/CP"
A=$CP/UsacoJava/src/Solutions
echo CP: $CP
echo A: $A
echo
echo -n "LINES OF CODE: "
find $A -name '*.java' | xargs wc -l | grep total | awk '{$1=$1};1'
echo -n "PROBLEMS SOLVED: "
find $A -name '*.java' | wc -l | awk '{$1=$1};1'