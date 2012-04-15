package sudokusolver.square.events;

public interface ValueSetEventSource {

    void addListener(ValueSetListener listener);
    void removeListener(ValueSetListener listener);
}
