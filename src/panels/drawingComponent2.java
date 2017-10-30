package panels;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

public class drawingComponent2 extends JComponent{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawLine(240, 280, 240, 340);
		g2d.drawLine(270, 310, 210, 310);
		g2d.dispose();
	}

}

