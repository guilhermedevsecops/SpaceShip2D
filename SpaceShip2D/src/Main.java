

import javax.swing.JFrame;

import Modelo.Fase;



/***
 * 
 * @author Guilherme Henrique de Sousa Jesus
 *
 */

//Janela do Jogo
public class Main extends JFrame {
	
	public Main() {
		
		//Instanciando a classe fase que o JPanel com background
		add(new Fase());
		setTitle("Spaceship 2D");
		setSize(950, 700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		this.setResizable(false);
		setVisible(true);
	}
	
	//Inicia a tela do jogo
	public static void main(String[] args) {
		new Main();
	}
	

}
