echo
echo -n LINES OF CODE:
find . -name '*.java' | xargs wc -l | grep total | awk '{$1=$1};1'
echo
echo -n PROBLEMS SOLVED:
find . -name '*.java' | wc -l | awk '{$1=$1};1'
echo
