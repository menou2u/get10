package controlor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Menu;

public class MenuListener implements ActionListener {

	private Menu menu;
	
	public MenuListener(Menu m){
		menu = m;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		menu.resetGrille();
	}

}
