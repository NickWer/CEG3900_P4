package werle.logParser;

/**
 * Classes inheriting this interface will be notified of invalid log attempts via callback
 * Created by nick on 3/4/17.
 */
public interface IParseAcceptor {
    void onLogMessage(String data);
}
