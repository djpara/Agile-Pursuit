package gameObjects;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Playspace extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private GridSpace gridSpace = new GridSpace(20,20);
	
	public Playspace(BorderLayout bl) {
		super(bl);
		
		initialize();
	}
	
	private void initialize() {
		
		// Configure the PlaySpace
		setBorder(new EmptyBorder(5, 5, 5, 5));
		
		// Add the gridSpace
		this.add(gridSpace);
		
		setVisible(true);	
	}
	
	public GridSpace getGridSpace() {
		return gridSpace;
	}
}
