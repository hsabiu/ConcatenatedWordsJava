# Introduction:
This directory consist of a gradle project and unit tests. The "IntelliJProject" directory contains source files that implements the logic for finding all six-letter words that are built from two  concatenated smaller words from a given dictionary of words (dictionary.txt).

## Building and testing:
To build and test the application, run the following commands from within the "IntelliJProject" root directory:

    gradle build
    gradle test
    gradle run --args "../dictionary.txt ../output.txt"

- The 'gradle build' command automatically execute the unit tests and compile the application if the tests passed.

- The 'gradle test' command executes unit tests (you can exclude running this command if you don't want to run the unit tests again separately)

- The 'gradle run' command run the application. Since the application expect the path to input and output files to be provided from the command line, you have to give the correct path to the files when executing the 'gradle run' command. 

Alternatively, you can import the "IntelliJProject" into IntelliJ IDEA and run both the application and the unit tests from within IntelliJ. If you decided to import the application into IntelliJ, you must edit the "Run configurations" and add the correct path to the input and output files. 

On successful execution, the application would generate a text file (output.txt) containing the results. The textfile is generated at the "JavaSolution" directory, unless you change the output directory when executing the "gradle run" command.

Test cases implemented:

- Test case 1: This test case verify that reading data from a file that does not exist raised an Exception and terminated the program

- Test case 2: This test case verify that all the words returned by the "getSixLetterWords" method have exactly 6 letters by looping through all the returned words and checking their length

- Test case 3: This test case verify that all the words returned by the "getConcatenatedWords" method are built from two concatenated smaller words that exist in the giving dictionary of words. The test method loop through all the returned words and check that both the two smaller words and the concatenated word exist in the input dictionary.

- Test case 4: This test case verify that errors returned when writing the result to the output file are handled