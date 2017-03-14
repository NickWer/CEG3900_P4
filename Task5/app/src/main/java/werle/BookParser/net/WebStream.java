package werle.BookParser.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Stream;
import java.net.*;
/**
 * Loads a webpage and returns a stream response.
 * Created by nick on 3/11/17.
 */
public class WebStream {

    /**
     * Loads a URL and returns the response as a Stream
     * @param Url - the address to load
     * @return A stream.
     */
    public static Stream<String> loadPage(String Url) {
        URL target;
        InputStream is = null;
        BufferedReader br;

        try {
            target = new URL(Url);
            is = target.openStream();
            br = new BufferedReader(new InputStreamReader(is));

            return br.lines();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
