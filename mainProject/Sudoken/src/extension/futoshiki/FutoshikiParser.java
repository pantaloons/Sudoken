package extension.futoshiki;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

import parser.ParserExtension;
import sudokuboard.Cell;
import sudokuboard.Position;

public class FutoshikiParser implements ParserExtension {

    // An id to advertise what config extensions this parser reads.
    private String name = "futoshiki";
    Collection<CellInequality> inequalities = new ArrayList<>();

    @Override
    public String getName() {
        return name;
    }

    /**
     * Format: cellx inequality celly Eg: 0 5 lt 0 6
     */
    @Override
    public void load(String futoshikiConfig) {
        Scanner scanner = new Scanner(futoshikiConfig);
        // Improve robustness
        while (scanner.hasNext()) {
            Cell firstCell = new Cell(new Position(scanner.nextInt(),
                    scanner.nextInt()));
            Inequality inequality = Inequality.fromString(scanner.next());
            Cell secondCell = new Cell(new Position(scanner.nextInt(),
                    scanner.nextInt()));
            CellInequality cellInequality = new CellInequality(firstCell,
                    secondCell, inequality);
            inequalities.add(cellInequality);
        }
    }

    @Override
    public String save() {
        // TODO Auto-generated method stub
        return null;
    }

    public Collection<CellInequality> getInequalities() {
        return inequalities;
    }

    public void setInequalities(Collection<CellInequality> inequalities) {
        this.inequalities = inequalities;
    }
}
