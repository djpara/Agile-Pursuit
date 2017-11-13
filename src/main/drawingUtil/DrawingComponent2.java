package main.drawingUtil;

import jdk.nashorn.internal.objects.Global;
import main.globalVariables.GlobalVariables;

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
		g2d.drawLine(GlobalVariables.PLAY_SPACE_PANEL_WIDTH/2,
				GlobalVariables.PLAY_SPACE_PANEL_HEIGHT/2 - 10 - GlobalVariables.CUSHION,
				GlobalVariables.PLAY_SPACE_PANEL_WIDTH/2,
				GlobalVariables.PLAY_SPACE_PANEL_HEIGHT/2 + 10 - GlobalVariables.CUSHION);
		g2d.drawLine(GlobalVariables.PLAY_SPACE_PANEL_WIDTH/2 - 10,
				GlobalVariables.PLAY_SPACE_PANEL_HEIGHT/2 - GlobalVariables.CUSHION - 1,
				GlobalVariables.PLAY_SPACE_PANEL_WIDTH/2 + 10,
				GlobalVariables.PLAY_SPACE_PANEL_HEIGHT/2 - GlobalVariables.CUSHION - 1);
		g2d.dispose();
	}

}

