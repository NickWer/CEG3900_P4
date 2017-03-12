package werle.BookParser;

import werle.BookParser.net.WebStream;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Responsible for parsing lists of books and returning the frequency of each word occurrence
 * Created by nick on 3/11/17.
 */
public class BookListParser {
    public List<AbstractMap.SimpleEntry<String, Integer>> parseList(Stream<String> bookList) {
        List<AbstractMap.SimpleEntry<String, Integer>> sortable = new ArrayList<>();
        Pattern oneWord = Pattern.compile("[a-zA-Z]+");
        bookList.map(WebStream::loadPage) // each URL is now a stream of lines of text
                .flatMap(book ->
                        book.flatMap(line -> {
                            Matcher m = oneWord.matcher(line);
                            List<String> out = new ArrayList<>();
                            while(m.find())
                                out.add(m.group().toLowerCase());

                            return out.stream();
                        })
                ) // All books are now mapped into one stream of words
                .collect(Collectors.toConcurrentMap( //Collect to a dictionary counting by value
                        w -> w, w -> 1, Integer::sum
                )).forEach((key, value) -> { // Push each item into a sortable data structure (List)
                    AbstractMap.SimpleEntry a = new AbstractMap.SimpleEntry<>(key, value);
                    sortable.add(a);
                });

        sortable.sort((entry1, entry2) -> entry2.getValue() - entry1.getValue()); //sort by value, descending
        return sortable;
    }
}
