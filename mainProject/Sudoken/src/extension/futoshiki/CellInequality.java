package extension.futoshiki;

import sudokuboard.Cell;

public class CellInequality {

    private Cell a;
    private Cell b;
    private Inequality inequality;

    public CellInequality(Cell a, Cell b, Inequality inequalityType) {
        super();
        this.a = a;
        this.b = b;
        this.inequality = inequalityType;
    }

    public Cell getA() {
        return a;
    }

    public Cell getB() {
        return b;
    }

    public Inequality getInequalityType() {
        return inequality;
    }

    /**
     * The method is {@code isViolated} as opposed to {@code isTrue}, as,
     * technically, it is not possible to prove the cell inequality is true if
     * the wrong cells are given. It is only possible to say that the inequality
     * is not violated.
     * 
     * @param a
     * @param b
     * @return
     */
    public boolean isViolated(Cell a, Cell b) {
        boolean isViolated;
        boolean sameCells = a.getPosition().equals(b.getPosition());
        if (sameCells) {
            isViolated = !(inequality.isTrue(a.getValue(), b.getValue()));
        } else {
            isViolated = false;
        }
        return isViolated;
    }
}
