package com.habib.yardiexercise;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * A class for finding all six-letter words that are built from two concatenated smaller words
 * from a given dictionary of words
 *
 * @author habib
 */
public class ConcatenatedWords {

    public static void main(String[] args) {

        ConcatenatedWords concatenated = new ConcatenatedWords();

        try {

            long startTime = System.currentTimeMillis();

            // Read the dictionary words into a 'HashSet'
            Set<String> allWords = concatenated.readData(args[0]);

            // Get only the words with exactly six letters
            Set<String> sixLetterWords = concatenated.getSixLetterWords(allWords);

            //Get only the words that are built of two concatenated smaller words
            ArrayList<String> concatenatedWords = concatenated.getConcatenatedWords(sixLetterWords, allWords);

            // Write the output of the concatenated words to an output file
            concatenated.writeOutput(concatenatedWords, args[1]);

            long endTime =System.currentTimeMillis();
            long duration = (endTime - startTime);

            System.out.println("---------------------------------------------");
            System.out.println("SUCCESS: Application execution time = " + duration + " ms");
            System.out.println("---------------------------------------------");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Helper method to read data from a text file into a 'Set' data structure
     *
     * @param inputPath Absolute path to the input text file containing dictionary of words
     * @return This method returns a 'Set'. Each element of the set represents a single line from the input text file.
     * @implNote The time complexity of this method is O(n) in the worse case, where n is the number of lines in the
     * input file
     */
    Set<String> readData(String inputPath) throws FileNotFoundException {

        // I use 'HashSet' data structure to provide fast membership testing
        Set<String> wordsRead = new HashSet<>();

        try {
            Scanner file = new Scanner(new File(inputPath));

            while (file.hasNext()) {
                wordsRead.add(file.next().trim());
            }
        } catch (FileNotFoundException e) {
            throw e;
        }
        return wordsRead;
    }

    /**
     * Helper method to write data from 'ArrayList' data structure to an output text file
     *
     * @param wordsList  An 'ArrayList' containing words of exactly six character length that are built of two
     *                   concatenated smaller words
     * @param outputFile Relative or absolute path to the output file
     * @implNote This method does not return anything. It simply writes the data of the 'wordsList'
     * into a text 'outputFile', with each line of the output file containing a single word.
     * If the 'outputFile' does not exist, the method would create the file and write to it.
     * If the file already exist, the method would first truncate the file before writing to it.
     */
    void writeOutput(ArrayList<String> wordsList, String outputFile) throws FileNotFoundException {

        try {
            PrintWriter writer = new PrintWriter(outputFile);

            for (String line : wordsList) {
                writer.println(line);
            }
            writer.close();

        } catch (FileNotFoundException e) {
            throw e;
        }
    }

    /**
     * A method that return all words of exactly six characters long from a 'Set' of a given dictionary of words
     *
     * @param allWords A 'Set' containing all the words from a given dictionary of words
     * @return This method return a 'Set' containing words of exactly six character long
     * @implNote This method does not return anything. It simply writes the data of the 'wordsList'
     * @implNote The time complexity of this method is O(n) in the worse case, where n is the number
     * of elements in the 'allWords' set
     */
    Set<String> getSixLetterWords(Set<String> allWords) {

        Set<String> sixLetterWords = new HashSet<>();

        for (String word : allWords) {
            if (word.length() == 6) {
                sixLetterWords.add(word);
            }
        }
        return sixLetterWords;
    }


    /**
     * A method that find and return all six-letter words that are built of two concatenated smaller words from a
     * given dictionary of words
     *
     * @param wordsSixLetters A 'Set' containing all the words of exactly six character long
     * @param wordsAll        A 'Set' containing all the words from a given dictionary of words
     * @return This method returns an 'ArrayList' containing words of exactly six character length that are built
     * of two concatenated smaller words from the 'wordsAll' Set
     * @implNote Each element of the output ArrayList is a string in the following format:
     * <p>
     * word_1 + word_2 => concatenated_word
     * <p>
     * For example, if the word is 'indoor', the method would add the following string to the list:
     * <p>
     * in + door => indoor
     * <p>
     * The time complexity of this method is O(n) + (5 * O(m)) in the worse case, where n is the
     * number of elements in the 'words_six_letters' set and m is the number of elements in the 'words_all' set
     * <p>
     * Since the running time of the method would be dominated by the size of 'wordsAll' set as the
     * number of words in the input dictionary gets larger, the worse case time complexity of this
     * method is O(m), where m is size of the input dictionary of words.
     */
    ArrayList<String> getConcatenatedWords(Set<String> wordsSixLetters, Set<String> wordsAll) {

        ArrayList<String> concatArray = new ArrayList<>();

        /* Loop through all the words provided in the 'wordsSixLetters' set. For each word, try
        to form a smaller word by extracting the letters of the word from the beginning to the
        current loop iteration index. If the smaller word formed exist in the given dictionary
        and the remaining letters of the word form a smaller word that also exist in the dictionary,
        add the word to the list of 'wordsSixLetters' words that are built of two concatenated
        smaller words */
        for (String word : wordsSixLetters) {
            for (int i = 0; i < word.length(); i++) {
                if (wordsAll.contains(word.substring(0, i)) && wordsAll.contains(word.substring(i, word.length()))) {
                    String tempStr = word.substring(0, i) + " + " + word.substring(i, word.length()) + " => " + word;
                    concatArray.add(tempStr);
                }
            }
        }

        return concatArray;
    }
}
