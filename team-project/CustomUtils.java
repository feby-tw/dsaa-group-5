import java.util.stream.Collectors;

public class CustomUtils {
    public static int countChar(String text, char target) {
        if (text == null || text.isEmpty())
            return 0;

        int count = 0;
        for (char c : text.toCharArray()) {
            if (c == target) {
                count++;
            }
        }
        return count;
    }

    public static String uniqueChar(String text) {
        if (text == null || text.isEmpty())
            return null;

        return text.chars()
            .distinct()
            .mapToObj(c -> String.valueOf((char) c))
            .collect(Collectors.joining());
    }
}