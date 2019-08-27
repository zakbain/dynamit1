import java.util.*;

public class WordCounter {
    private static final String wordSplitRegex = "\\s+";
    private static final String headers = "Word\t\tCount\n-------------------------------------------";

    public static Map<String, Integer> calculateSortedWordCount(String text) {
        String scrubbedText = scrubText(text);
        Map<String, Integer> wordCounts = new HashMap<>();
        String[] words = scrubbedText.split(wordSplitRegex);
        for (String word : words) {
            if (wordCounts.containsKey(word)) {
                Integer count = wordCounts.get(word);
                wordCounts.put(word, count + 1);
            } else {
                wordCounts.put(word,1);
            }
        }
        Map<String, Integer> sortedWordCounts = sortMap(wordCounts);
        return sortedWordCounts;
    }

    public static void printWordCounts(Map<String, Integer> wordCounts) {
        System.out.println(headers);
        for (Map.Entry<String, Integer> entry : wordCounts.entrySet()) {
            String entryCountLine = String.format("%s\t\t%d", entry.getKey(), entry.getValue());
            System.out.println(entryCountLine);
        }
    }

    private static String scrubText(String text) {
        return text.replaceAll("[\\.|,|\\?|\"]", "");
    }

    private static Map<String, Integer> sortMap(Map<String, Integer> map) {
        List<Map.Entry<String, Integer>> entries = new ArrayList<>(map.entrySet());
        entries.sort(new WordCountDescendingComparator());

        Map<String, Integer> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : entries) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }
}
