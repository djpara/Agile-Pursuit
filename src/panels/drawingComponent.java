package panels;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

public class drawingComponent extends JComponent{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String text;
	
	public drawingComponent(String txt){
		this.text = txt;
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setFont(new Font("Herculanum",Font.PLAIN,25));
		g2d.drawString(this.text, 5, 17);
		g2d.dispose();
	}

}
