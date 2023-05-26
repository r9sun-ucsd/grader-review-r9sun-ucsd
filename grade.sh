CPATH='.:lib/hamcrest-core-1.3.jar:lib/junit-4.13.2.jar'

rm -rf student-submission
rm -rf grading-area

mkdir grading-area

git clone $1 student-submission
echo 'Finished cloning'

if [[ -f student-submission/ListExamples.java ]]
then 
    cp student-submission/ListExamples.java grading-area/
    cp GradeServer.java grading-area/
    cp Server.java grading-area/
    cp TestListExamples.java grading-area/
    cp -r lib grading-area/


    javac -cp ".;lib/hamcrest-core-1.3.jar;lib/junit-4.13.2.jar" grading-area/*.java > output.txt 2>&1
    if [[ $? == 1 ]]
    then
        cat output.txt
        exit 1
    fi 
    java -cp ".;lib/junit-4.13.2.jar;lib/hamcrest-core-1.3.jar;grading-area" org.junit.runner.JUnitCore TestListExamples > test.txt
    tests=$(cat test.txt | grep -o 'Tests run: [0-9]*' | grep -o '[0-9]*')
    fails=$(cat test.txt | grep -o 'Failures: [0-9]*' | grep -o '[0-9]*')
    if [[ $fails == "" ]]
    then
        tests=$(cat test.txt | grep -o '([0-9] tests)' | grep -o '[0-9]*')
        echo Perfect score!
        echo "Score: "$tests"/"$tests
    else 
        correct=$(expr $tests - $fails)
        echo "Score: "$correct"/"$tests
    fi
else
    echo Please provide the correct files!
    exit 1
fi


