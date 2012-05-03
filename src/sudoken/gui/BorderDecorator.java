package sudoken.gui;

import java.util.Collection;
import java.util.List;

import sudoken.domain.ConstraintDecorator;
import sudoken.domain.Position;

/**
 * BorderDecorators decorate a board with a border around a List of adjacent cells
 *
 */
public class BorderDecorator extends ConstraintDecorator {

	private List<Position> positions;

	public BorderDecorator(List<Position> positions){
		this.positions = positions;
	}
	
	@Override
	public void decorate(BoardGraphics bg) {
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
	}

}
