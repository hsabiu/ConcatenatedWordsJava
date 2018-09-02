 package com.habib.yardiexercise;

 import org.junit.Test;

 import java.io.FileNotFoundException;
 import java.util.ArrayList;
 import java.util.Set;

 import static org.junit.Assert.assertEquals;
 import static org.junit.Assert.assertTrue;

 /**
  * A class implementing unit tests for the ConcatenatedWords class
  *
  * @author habib
  */
 public class ConcatenatedWordsTest {

     @Test(expected = FileNotFoundException.class)
     public void testReadData() throws FileNotFoundException {
         ConcatenatedWords concatenated = new ConcatenatedWords();

         // Verify that reading data from a file that does not exist raise FileNotFoundException
         // and terminated the program
         concatenated.readData("/dummy_file.txt");
     }

     @Test(expected = FileNotFoundException.class)
     public void testWriteOutput() throws FileNotFoundException {
         ConcatenatedWords concatenated = new ConcatenatedWords();
         Set<String> allWords = concatenated.readData("src/test/resources/dictionary.txt");
         Set<String> sixLetterWords = concatenated.getSixLetterWords(allWords);
         ArrayList<String> concatenatedWords = concatenated.getConcatenatedWords(sixLetterWords, allWords);

         // Verify that the errors returned when writing the result to the output file are handled
         concatenated.writeOutput(concatenatedWords, "/dummy/path/");
     }

     @Test
     public void testGetSixLetterWords() throws FileNotFoundException {

         ConcatenatedWords concatenated = new ConcatenatedWords();
         Set<String> allWords = concatenated.readData("src/test/resources/dictionary.txt");
         Set<String> sixLetterWords = concatenated.getSixLetterWords(allWords);

         // Verify that all the words returned by the 'getSixLetterWords' method have exactly 6 letters
         for (String word : sixLetterWords) {
             assertEquals(6, word.length());
         }
     }

     @Test
     public void testGetConcatenatedWords() throws FileNotFoundException {
         ConcatenatedWords concatenated = new ConcatenatedWords();
         Set<String> allWords = concatenated.readData("src/test/resources/dictionary.txt");
         Set<String> sixLetterWords = concatenated.getSixLetterWords(allWords);
         ArrayList<String> concatenatedWords = concatenated.getConcatenatedWords(sixLetterWords, allWords);

         // Verify that all the words returned by the 'getConcatenatedWords' method are built
         // from two concatenated smaller words that exist in the giving dictionary of words
         for (String word : concatenatedWords) {

             String[] tempArray = word.split("=>");

             String[] smallerWords = tempArray[0].split("\\+");
             String concatenatedWord = tempArray[1].replaceAll("\\s+", "");

             String word_1 = smallerWords[0].replaceAll("\\s+", "");
             String word_2 = smallerWords[1].replaceAll("\\s+", "");

             assertTrue(allWords.contains(word_1));
             assertTrue(allWords.contains(word_2));
             assertTrue(allWords.contains(concatenatedWord));
         }
     }
 }