import java.util.*;
import java.io.*;

public class temp {
    private static final String DICTIONARY_PATH = "dictionary.txt";
    private static final int MAX_DISTANCE = 2;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        Set<String> dictionary = loadDictionary();

        System.out.print("Enter a word: ");
        String inputWord = scanner.nextLine().toLowerCase();

        if (dictionary.contains(inputWord)) {
            System.out.println("The word \"" + inputWord + "\" is spelled correctly.");
        } else {
            System.out.println("The word \"" + inputWord + "\" is spelled incorrectly.");
            System.out.print("Did you mean: ");

            for (String word : dictionary) {
                int distance = levenshteinDistance(inputWord, word);

                if (distance <= MAX_DISTANCE) {
                    System.out.print(word + ", ");
                }
            }
        }
    }

    private static Set<String> loadDictionary() throws FileNotFoundException {
        Set<String> dictionary = new HashSet<>();

        File dictionaryFile = new File("src//dictonary.txt");
        Scanner scanner = new Scanner(dictionaryFile);

        while (scanner.hasNext()) {
            String word = scanner.next().toLowerCase();
            dictionary.add(word);
        }

        return dictionary;
    }

    private static int levenshteinDistance(String s, String t) {
        int m = s.length();
        int n = t.length();
        int[][] d = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            d[i][0] = i;
        }

        for (int j = 0; j <= n; j++) {
            d[0][j] = j;
        }

        for (int j = 1; j <= n; j++) {
            for (int i = 1; i <= m; i++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    d[i][j] = d[i - 1][j - 1];
                } else {
                    int deletion = d[i - 1][j] + 1;
                    int insertion = d[i][j - 1] + 1;
                    int substitution = d[i - 1][j - 1] + 1;
                    d[i][j] = Math.min(Math.min(deletion, insertion), substitution);
                }
            }
        }

        return d[m][n];
    }
}
