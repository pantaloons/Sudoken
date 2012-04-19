package sudoken.extension.jigsaw;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

import sudoken.domain.Constraint;
import sudoken.domain.UniqueConstraint;
import sudoken.persistence.SectionParser;

public class JigsawParser implements SectionParser {
    /**
     * Format: Positive integers in a grid the same size as puzzle, with each
     * number specifying a cell's membership to a jigsaw piece.
     * 
     * @return
     */
    // TODO: Ensure jigsaw pieces are contiguous.
    @Override
    public Collection<Constraint> load(String config, int width, int height)
            throws IOException {
        Scanner sc = new Scanner(config);
        List<UniqueConstraint> pieces = new ArrayList<UniqueConstraint>();
        int[] counts = new int[width];
        for (int i = 0; i < width; i++) {
            pieces.add(new UniqueConstraint());
        }
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int nextInt = sc.nextInt();
                // nextInt specified with 1 at origin, not 0.
                if (nextInt > 0 && nextInt <= width) {
                    pieces.get(nextInt - 1).add(j, i);
                    counts[nextInt - 1]++;
                } else
                    throw new IOException("Parse error...");
            }
        }
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] != width)
                throw new IOException(
                        "Parse error: Jigsaw pieces of wrong size.");
        }
        List<Constraint> c = new ArrayList<Constraint>();
        c.addAll(pieces);
        return c;
    }
}
