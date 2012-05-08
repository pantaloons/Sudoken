package sudoken.extension.kenken;

import java.awt.GridBagConstraints;
import java.util.List;

import javax.swing.JLabel;

import sudoken.domain.Board;
import sudoken.domain.Constraint;
import sudoken.domain.Position;
import sudoken.gui.BoardGraphics;
import sudoken.gui.CellGraphics;

/**
 * OperatorConstraint enforces that the values of a cage meet
 * the target value of the cage, when operated on by the Operator
 * of the cage
 *
 */
public class OperatorConstraint extends Constraint {
	/** operator defining behaviour of constraint */
	private Operator operator;
	
	/** target value that needs to be reached */
	private int target;
	
	/** list of cells that constraint is concerned about */
	private List<Position> positions;
	
	/**
	 * Create an Operator Constraint
	 * @param provider Extension that provided the constraint
	 * @param shouldSave Should this constraint be saved when the board is saved
	 * @param positions List of Positions of the cells in the cage
	 * @param target Target value of the cage
	 * @param operator Operator used by the cage
	 */
	public OperatorConstraint(String provider, boolean shouldSave, List<Position> positions, int target, Operator operator) {
		super(provider, shouldSave);
		
		if (positions.size() < 2) {
			throw new IllegalArgumentException("Must have more than two positions.");
		}
		else if (operator.isBinary() && (positions.size() != 2)) {
			throw new IllegalArgumentException(
					"Must only have two positions for subtract or division operators.");
		}
		
		this.target = target;
		this.operator = operator;
		this.positions = positions;
	}
	
	@Override
	public boolean canHandle(Position position) {
		return positions.contains(position);
		/*for (Position p : positions) {
			if (position.equals(p))
				return true;
		}
		
		return false;*/
	}
	
	@Override
	public boolean isViolated(Board board) {
		return operator.isViolated(board, positions, target);
	}
	
	/**
	 * Save the constraint
	 * @return String representation of the constraint
	 */
	public String save() {
    	String saveStr = String.format("%d %s %d ", target, operator.toString(), positions.size());
    	for (Position p : positions)
    		saveStr += p.getX() + " " + p.getY() + " ";
    	return saveStr;
    }
	
	/**
	 * Get a string representing the constraint (position dependent)
	 * @return
	 */
	public String getRepresentation() {
		return operator.toString() + target;
	}

	@Override
	public void decorate(BoardGraphics bg) {
    	
		//Put borders around cages
		
    	int[] dx = new int[]{0, -1, 0, 1};
    	int[] dy = new int[]{-1, 0, 1, 0};
    	
    	for(int i = 0; i < positions.size(); i++) {
    		Position p = positions.get(i);
    		for(int k = 0; k < 4; k++) {
    			int nx = p.getX() + dx[k];
    			int ny = p.getY() + dy[k];
    			
    			boolean edge = true;
				for(int j = 0; j < positions.size(); j++) {
					if(j == i) continue;
					
					Position p2 = positions.get(j);
					if(p2.getX() == nx && p2.getY() == ny) {
						edge = false;
						break;
					}
				}
    			
    			if(edge) {
    				bg.getCell(p).setBorderWidth(k, 2);
    			}
    		}
    	}
    	
		//Draw operator and target to first position
    	
    	CellGraphics firstCell = bg.getCell(positions.get(0));
    	JLabel cageLabel = new JLabel();
    	cageLabel.setFont(cageLabel.getFont().deriveFont(5));
    	cageLabel.setText(getRepresentation());
    	GridBagConstraints gbc = new GridBagConstraints();
    	gbc.gridy = 1;

    	firstCell.add(cageLabel, gbc);
    	
    	
	}
}
