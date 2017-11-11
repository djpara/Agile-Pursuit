package main.drawingUtil;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

public class DrawingComponent extends JComponent{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String text;
	
	public DrawingComponent(String txt){
		this.text = txt;
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);

		text = "Developer: " + text;

		Graphics2D g2d = (Graphics2D) g;
		g2d.setFont(new Font("System",Font.PLAIN,20));
		g2d.drawString(this.text, 25, 17);
		g2d.dispose();
	}

}
