/* Copyright 2016 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.engedu.anagrams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Arrays;

public class AnagramDictionary {

    private static final int MIN_NUM_ANAGRAMS = 5;
    private static final int DEFAULT_WORD_LENGTH = 3;
    private static final int MAX_WORD_LENGTH = 7;
    private Random random = new Random();
    private ArrayList<String> wordList = new ArrayList<>();
    private HashSet<String> wordSet = new HashSet<>();
    private HashMap<String, ArrayList<String>> lettersToWord = new HashMap<>();

    public AnagramDictionary(Reader reader) throws IOException {
        BufferedReader in = new BufferedReader(reader);
        String line;
        while ((line = in.readLine()) != null) {
            String word = line.trim();
            wordList.add(word);
            wordSet.add(word);
            // hashmap
            String sortedWord = sortLetters(word);
            if (!lettersToWord.containsKey(sortedWord)) {
                ArrayList<String> newList = new ArrayList<>();
                lettersToWord.put(sortedWord, newList);
            }
            if (wordSet.contains(word)) { //valid word
                lettersToWord.get(sortedWord).add(word);
            }
        }
    }


    public boolean isGoodWord(String word, String base) {
        if(wordSet.contains(word) && !word.contains(base)){
            return true;
        } else {
            return false;
        }
    }

    private String sortLetters(String inputString) {
        char tempArray[] = inputString.toCharArray();
        // sort tempArray
        Arrays.sort(tempArray);
        // return new sorted string
        return new String(tempArray);
    }

    public List<String> getAnagrams(String targetWord) {
//        ArrayList<String> result = new ArrayList<String>();
//        Iterator<String> iterator = wordList.iterator();
//        while(iterator.hasNext()) {
//            String word = iterator.next();
//                if(word.length()== targetWord.length()&& word.contains(targetWord)!= true) {
//                    String newWord = sortLetters(word);
//                    String newTargetWord = sortLetters(targetWord);
//                    if (newWord.equals(newTargetWord)) {
//                        result.add(word);
//                    }
//                }
        String sortedWord = sortLetters(targetWord);
        return lettersToWord.get(sortedWord);
    }

    public List<String> getAnagramsWithOneMoreLetter(String word) {
        ArrayList<String> result = new ArrayList<String>();
        return result;
    }

    public String pickGoodStarterWord() {
        return "stop";
    }
}
