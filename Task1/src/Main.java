import werle.logParser.IParseAcceptor;
import werle.logParser.LogParser;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;


public class Main implements IParseAcceptor {
    private int counter = 1;
    private Writer writer;

    private Main(String outputFile) throws IOException {
        writer = new FileWriter(outputFile, false);
    }

    public static void main(String[] args) {
        String inputFile = "/var/log/auth.log", outputFile = "/tmp/invalidUsers.txt";
        switch (args.length) {
            case 0: break;
            case 2: outputFile = args[ 1]; // fall through
            case 1: inputFile = args[ 0]; break;
            default:
                System.out.println
                        ("Usage: At most two file names expected");
                System.exit(0);
        }
        System.out.println("Reading from " + inputFile);
        System.out.println("Writing to " + outputFile);
        LogParser parser = new LogParser(inputFile);

        try {
            parser.parse(new Main(outputFile)); //dirtbag maneuver
        } catch (IOException e) {
            System.out.println("Failed to parse log, reason: ");
            e.printStackTrace();
        }

        System.out.println("Done!");
    }

    @Override
    public void onLogMessage(String data) {
        try {
            writer.append(counter + " " + data + "\n");
            writer.flush();
        } catch (IOException e) {
            System.out.println("Error writing output line to file!");
            e.printStackTrace();
        }
        //System.out.println(counter + " " + data);
        counter++;
    }
}
