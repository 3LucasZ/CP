#!/bin/zsh
source ~/.zshrc

#Prereq:
#1. All data in folder: Desktop/Testcases
#2. Input files are x.in
#3. Corresponding output files are x.out

#Only change these lines
mySol=ComfortablyNumb.java

#Setup
echo Starting Judge
A=$CP/UsacoJava/src
echo Project Folder: $CP
echo Src Folder: $A
B=$HOME/Desktop/Testcases
echo Pipe Folder: $B

#Loop through all .in files
tcs=0
tcsAC=0
for file in $B/*.in; do
  ((tcs++))
  name=$(basename "$file" .in)
  echo "Testcase: $name"
  inFile="$B/$name.in"
  outFile="$B/$name.out"
  resFile="$B/$name.res"
  difFile="$B/$name.dif"
  #Your code
  ms0=$(getms)
  cat $inFile | java $A/$mySol > $resFile
  ms1=$(getms)
  echo $((ms1-ms0)) ms
  #Cmp
  diff $resFile $outFile > $difFile
  if cmp --silent -- "$resFile" "$outFile"; then
    echo "AC"
    ((tcsAC++))
  else
    echo "WA"
  fi
done

#End
echo "Solved: $tcsAC / $tcs"


