package gameObjects;

import java.awt.Graphics;

import javax.swing.JPanel;

import interfaces.IDrawable;

public class GridPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private IDrawable m_Drawable;
	
	public GridPanel() {}
	
	public GridPanel(IDrawable drawable) {
		m_Drawable = drawable;
	}
	
	public void setDrawable(IDrawable drawable) {
		m_Drawable = drawable;
		repaint();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (m_Drawable != null) {
			m_Drawable.draw(g);
		}
	}
}
