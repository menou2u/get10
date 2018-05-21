package model;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class Button extends JButton {

	int value;
	private BufferedImage buttonIcon;
	
	public Button(int value) {
		this.value = value;
		switch (value) {
		case 1 :
	    	try {
	    		setImage(ImageIO.read(getClass().getResource("/resources/1.png")));
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case 2 :
			try {
				setImage(ImageIO.read(getClass().getResource("/resources/2.png")));
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case 3 :
			try {
				setImage(ImageIO.read(getClass().getResource("/resources/3.png")));
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case 4 :
			try {
				setImage(ImageIO.read(getClass().getResource("/resources/4.png")));
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case 5 :
			try {
				setImage(ImageIO.read(getClass().getResource("/resources/5.png")));
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case 6 :
			try {
				setImage(ImageIO.read(getClass().getResource("/resources/6.png")));
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case 7 :
			try {
				setImage(ImageIO.read(getClass().getResource("/resources/7.png")));
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case 8 :
			try {
				setImage(ImageIO.read(getClass().getResource("/resources/8.png")));
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case 9 :
			try {
				setImage(ImageIO.read(getClass().getResource("/resources/9.png")));
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case 10 :
			try {
	    		setImage(ImageIO.read(getClass().getResource("/resources/10.png")));
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		default:
			break;
		}
	}
	
	public int getValue(){
		return value;
	}
	
	public void setValue(int value){
		this.value = value;
	}

	public Image getImage() {
		return buttonIcon;
	}

	public void setImage(BufferedImage image) {
		this.buttonIcon = image;
		setIcon(new ImageIcon(buttonIcon));
		setBorder(BorderFactory.createEmptyBorder());
		setContentAreaFilled(false);
	}
}
