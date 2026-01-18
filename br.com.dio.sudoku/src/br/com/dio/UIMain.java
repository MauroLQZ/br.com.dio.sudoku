package br.com.dio;

import java.awt.Dimension;
import java.util.stream.Stream;

import javax.swing.JFrame;
import javax.swing.JPanel;

import br.com.dio.ui.custom.frame.MainFrame;
import br.com.dio.ui.custom.panel.MainPanel;
import br.com.dio.ui.custom.screen.MainScreen;

import static java.util.stream.Collectors.toMap;

/*Para ver como esta o projeto
 * 
 *  PAREI NO VIDEO ; AJUSTANDO DETALHES FINAIS
 *  
 *  */
public class UIMain {

	public static void main(String[] args) {
		final var gameConfig = Stream.of(args)
				.collect(toMap(k -> k.split(";")[0], v -> v.split(";")[1]));
    		
		var mainScreen = new MainScreen(gameConfig);
		mainScreen.buildMainScreen();
		/*var dimension = new Dimension(600, 600);
		JPanel mainPanel = new MainPanel(dimension);
		JFrame mainFrame = new MainFrame(dimension, mainPanel);
		//Dar Refresh na tela
		mainFrame.revalidate();
		mainFrame.repaint();*/
	
	}

}
