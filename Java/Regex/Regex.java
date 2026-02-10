
public static void main(String[] args) {
    String text    =
            "This is the text which is to be searched " +
                    "for occurrences of the word 'is'.";

    String regex = "is";

    Pattern p = Pattern.compile(regex);
    Matcher matcher = p.matcher(text);

    int count = 0;
    while (matcher.find()) {
        count++;
        System.out.println("found: " + count + " : "
                + matcher.start() + " - " + matcher.end());
    }

    // Java String Regex Method
    String text1 = "one two three two one";
    System.out.println(text1.matches("two"));

    // split()
    String[] twos = text1.split(("two"));

    // replaceFirst()
    String change = text1.replaceFirst("two", "five");

    // replaceAll
    String s = text1.replaceAll("one","six");


}