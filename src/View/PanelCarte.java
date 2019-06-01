package view;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *  Class PanelCarte.
 */
public class PanelCarte extends JPanel implements MouseListener {

	private Dimension dim;
    private PanelDessineur dessinCarte;
    private int totalEquipe;
    
	/**
	 *  Construit un objet de type Carte.
	 */
	public PanelCarte(int totalEquipe) {
		this.totalEquipe = totalEquipe;
	    this.setPreferredSize(new Dimension(1095, 0));
        this.setBackground(new Color(48, 48, 48));
	    this.setVisible(true);
	    
	    this.dim = new Dimension(0,0);
	    
        this.dessinCarte = new PanelDessineur(totalEquipe);
        this.dessinCarte.setBackground(new Color(48, 48, 48));
        
        JScrollPane scroll = new JScrollPane(this.dessinCarte) ;
        
        scroll.setPreferredSize(new Dimension(1095, 935));
        //scroll.getVerticalScrollBar().setUnitIncrement(25);
        scroll.getVerticalScrollBar().setPreferredSize (new Dimension(0,0)); 
        scroll.getHorizontalScrollBar().setPreferredSize (new Dimension(0,0)); 
        scroll.setWheelScrollingEnabled(false);
        scroll.addMouseListener(this);
        
        if(this.totalEquipe > 4) {
	        this.ajoutScrollHex();
        }
        
        this.add(scroll);

	}
	
	 public void ajoutScrollHex() {
    	final int W = 585;
        final int H = 860;
        boolean changed = true;

        int x = 1000;
        int y = 1000;
        Rectangle rect = new Rectangle(W, H);
        this.dessinCarte.scrollRectToVisible(rect);
 
        int this_width = (x + W);
        if (this_width > this.dim.width) {
        	this.dim.width = this_width; changed=true;
        }
        
        int this_height = (y + H);
        if (this_height > this.dim.height) {
        	this.dim.height = this_height; changed=true;
        }
        
        if (changed) {
            this.dessinCarte.setPreferredSize(this.dim);
            this.dessinCarte.revalidate();
        }
        this.dessinCarte.repaint();
    }
	 
	public void mouseClicked(MouseEvent event) {
		Point clik = event.getPoint().getLocation();
		final int W = 585;
        final int H = 860;

        int x = (int) clik.getX();
        int y = (int) clik.getY();
        System.out.println("X : " + x + " Y : " + y);
        
        Rectangle rect = new Rectangle(x, y, W, H);
        
        this.dessinCarte.scrollRectToVisible(rect);

        this.dessinCarte.repaint();
	}

	public void mouseEntered(MouseEvent event) {}
	public void mouseExited(MouseEvent event) {}
	public void mousePressed(MouseEvent event) {}
	public void mouseReleased(MouseEvent event) {}	 
}
