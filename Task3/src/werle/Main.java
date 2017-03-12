package werle;

import werle.BookParser.BookListParser;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Main {

    private static void quickWrite(FileWriter w, String o) {
        try {
            w.append(o);
        } catch (IOException e) {
            System.out.println("Error printing line to file:");
            e.printStackTrace();
        }
    }

    private static void quickClose(FileWriter w) {
        try {
            w.flush();
            w.close();
        } catch (IOException e) {
            System.out.println("Error flushing and closing FileWriter:");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String inputFile;
        String outputFile;
        Integer wordCountToFilter;
        FileWriter writer;
        Stream<String> bookURLs;


        try {
            inputFile = args[0];
            outputFile = args[1];
            wordCountToFilter = Integer.parseInt(args[2]);
        } catch (Exception ex) {
            System.out.println("Invalid input supplied.\nUsage: main.jar inputFile outputFile n");
            ex.printStackTrace();
            return;
        }

        System.out.println("Reading from " + inputFile);
        System.out.println("Writing to " + outputFile);
        BookListParser parser = new BookListParser();

        try {
            writer = new FileWriter(outputFile, false);
            bookURLs = Files.lines(Paths.get(inputFile));
        } catch (IOException e) {
            System.out.println("Error opening either input or destination file, terminating.");
            e.printStackTrace();
            return;
        }

        parser.parseList(bookURLs)
            .subList(0, wordCountToFilter - 1)
            .forEach(match -> {
                String out = match.getKey() + " - " + match.getValue();
                System.out.println(out);
                quickWrite(writer, out + "\n");
            });

        quickClose(writer);
    }
}
