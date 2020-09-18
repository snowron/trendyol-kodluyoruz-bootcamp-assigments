import java.util.HashSet;
import java.util.Set;

public class WordCount {
    public int count(String sentence) {
        if (sentence == null || sentence.length() == 0) {
            throw new NullPointerException("Sentence is null");
        }
        String[] signs = {"(", ")", "!", "?", ".", ",", ":", ";", "..."};
        String[] wordsArray = sentence.split(" ");
        Set wordSet = new HashSet<String>();
        for (String word : wordsArray) {
            String lowerCaseWord = word.toLowerCase();
            for (String sign : signs) {
                lowerCaseWord = lowerCaseWord.replace(sign, "");
            }
            wordSet.add(lowerCaseWord);
        }
        return wordSet.size();
    }
}
