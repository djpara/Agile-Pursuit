package main.drawingUtil;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

public class DrawingComponent2 extends JComponent{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawLine(240, 300, 240, 320);
		g2d.drawLine(230, 310, 250, 310);
		g2d.dispose();
	}

}

