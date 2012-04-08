package sudokusolver.square.events;

public interface PossibilityReductionEventSource {

    void addListener(PossibilityReductionListener listener);
    void removeListener(PossibilityReductionListener listener);
}
