package sudokusolver.square.events;

public interface SquarePossibilityReductionSource {

    void addListener(PossibilityReductionListener listener);
    void removeListener(PossibilityReductionListener listener);
}
