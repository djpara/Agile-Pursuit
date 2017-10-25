package gameObjects;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

public class GridSpace extends JPanel {

	private static final long serialVersionUID = 1L;

	private GridPanel[][] gridPanels;
	
	public GridSpace(int x, int y) {
		setLayout(new GridLayout(x, y));
		gridPanels = new GridPanel[x][y];
		
		initialize(x, y);
	}
	
	private void initialize(int x, int y) {
		
		// Create the grid
		for (int i = 0; i < x; ++i) {
			for (int j = 0; j < y; ++j) {
				GridPanel gp = new GridPanel();
				gp.setBackground(Color.WHITE);
				gp.setSize(5, 5);
				Border border = gp.getBorder();
				Border margin = new EmptyBorder(1, 1, 1, 1); 
				gp.setBorder(new CompoundBorder(border, margin));
				gridPanels[i][j] = gp;
			}
		}
		
	}
	
	public GridPanel[][] getGridPanels() {
		return gridPanels;
	}
	
}
