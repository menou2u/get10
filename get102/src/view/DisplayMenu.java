package view;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JPanel;

import controlor.MenuListener;
import model.Menu;

public class DisplayMenu extends JPanel implements Observer {

	private static final long serialVersionUID = -511424357728896878L;
	private Menu menu;

	public DisplayMenu(Menu m){
		menu = m;
		menu.addObserver(this);
		JButton recommencer = new JButton("Recommencer");
		recommencer.addActionListener(new MenuListener(menu));
		add(recommencer);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
	
}
