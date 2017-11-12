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
	
	String mText;
	int mTextXCoordinate = 0;
	int mTextYCoordinate = 0;
	int mFontSize = 12;
	
	public DrawingComponent(String text, int xCoordinate, int yCoordinate, int fontSize){
		setText(text);
		setCoordinates(xCoordinate, yCoordinate);
		setFontSize(fontSize);
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;
		g2d.setFont(new Font("System",Font.PLAIN,mFontSize));
		g2d.drawString(mText, mTextXCoordinate, mTextYCoordinate);
		g2d.dispose();
	}

	public void setText(String text) {
		mText = text;
	}

	public void setCoordinates(int x, int y) {
		if (x < 0 || y < 0) {
			return;
		}

		mTextXCoordinate = x;
		mTextYCoordinate = y;
	}

	public void setFontSize(int size) {
		if (size < 0) {
			return;
		}

		mFontSize = size;
	}

}
