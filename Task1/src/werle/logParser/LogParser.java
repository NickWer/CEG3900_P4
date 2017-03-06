package werle.logParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This object is used to parse logs and produces a
 * Created by nick on 3/4/17.
 */
public class LogParser {
    private final Pattern regex;
    private String target;

    public LogParser(String target){
        this.target = target;
        this.regex = Pattern.compile("(?:Invalid user [A-Za-z]+ from )(\\b\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\b)"); //precompile regex for speed
    }

    /**
     * Parses the log file and returns an address
     * @throws IOException if the path supplied on construction is invalid
     */
    public void parse(IParseAcceptor callback) throws IOException {
        Files.lines(Paths.get(target))
                .map(this::authAttempt)
                .filter(Objects::nonNull)
                .forEach(callback::onLogMessage);
    }

    /**
     * Extracts the IPv4 Address of invalid auth attempts
     * @param line - log line to be parsed
     * @return a String containing an IPv4 address, or null if the line does not correspond to an invalid auth attempt.
     */
    private String authAttempt(String line) {
        Matcher test = regex.matcher(line);
        if(test.find())
            return test.toMatchResult().group(1);
        else
            return null;
    }
}
