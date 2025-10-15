package Modelo;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

//Fase do jogo
public class Fase extends JPanel implements ActionListener {
	// Atributos da Fase
	private Image fundo;
	private Player player;
	private Timer timer;
	private List<Enemy1> enemy1;
	
	private boolean emJogo;
	
	
	
	// Constructor da fase
	public Fase() {

		setFocusable(true);
		setDoubleBuffered(true);

		ImageIcon referencia = new ImageIcon("res\\fundo.png");
		fundo = referencia.getImage();

		// Instanciando player na fase
		player = new Player();
		player.load();

		// instanciar teclado

		addKeyListener(new TecladoAdapter());

		// velocidade do jogo
		timer = new Timer(1, this);
		timer.start();
		
		InicializaInimigos();
		emJogo = true;
	}
	
	//fun��o de posicionamento e inicializa��o de inimigos
	public void InicializaInimigos(){
		
		int coordenadas[] = new int [40];
		enemy1 = new ArrayList<Enemy1>();
		
		for (int i = 0; i < coordenadas.length; i++) {
			int x = (int)(Math.random() * 8000+1024);
			int y = (int)(Math.random() * 500+30);
			enemy1.add(new Enemy1(x,y));

		}
		
	}

	// Metodo para exibir fundo inimigos e player na tela o fundo
	public void paint(Graphics g) {

		Graphics2D graficos = (Graphics2D) g;
		if(emJogo == true) {
			graficos.drawImage(fundo, 0, 0, null);
			graficos.drawImage(player.getImagem(), player.getX(), player.getY(), this);
	
			List<Tiro> tiros = player.getTiros();
			for (int i = 0; i < tiros.size(); i++) {
				Tiro m = tiros.get(i);
				m.load();
				graficos.drawImage(m.getImagem(), m.getX(), m.getY(), this);
	
			}
			
			for (int o = 0; o < enemy1.size(); o++) {
				Enemy1 in = enemy1.get(o);
				in.load();
				graficos.drawImage(in.getImagem(), in.getX(), in.getY(), this);
				
			}
		}else {
			ImageIcon fimJogo = new ImageIcon("res\\game_over.png");
			graficos.drawImage(fimJogo.getImage(), 0, 0, null);
		}
		g.dispose();

	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		player.update();
		List<Tiro> tiros = player.getTiros();
		for (int i = 0; i < tiros.size(); i++) {
			Tiro m = tiros.get(i);
			if (m.isVisivel()) {
				m.update();
			} else {
				tiros.remove(i);
			}
		}
		
		for (int o = 0; o < enemy1.size(); o++) {
			
			Enemy1 in = enemy1.get(o);
				if(in.isVisivel()) {
					in.update();
					
				}else {
					enemy1.remove(o);
				}
			
		}
		checarColisoes();
		repaint();

	}
	//Fun��o para checar Colis�es
	public void checarColisoes() {
		Rectangle formaNave = player.getBounds();
		Rectangle formaEnemy1;
		Rectangle formaTiro;
		
		//Ret�ngulo de Impacto Inimigo
		for (int i = 0; i < enemy1.size(); i++) {
			Enemy1 tempEnemy1 = enemy1.get(i);
			formaEnemy1 = tempEnemy1.getBounds();
			if(formaNave.intersects(formaEnemy1)) {
				
				player.setVisivel(false);
				tempEnemy1.setVisivel(false);
				emJogo = false;
				
			}
		}
		//Ret�ngulo de Impacto Tiros
		List<Tiro> tiros = player.getTiros();
		for (int j = 0; j < tiros.size(); j++) {
			Tiro tempTiro = tiros.get(j);
			formaTiro = tempTiro.getBounds();
			for (int o = 0; o < enemy1.size(); o++) {
				Enemy1 tempEnemy1 = enemy1.get(o);
				formaEnemy1 = tempEnemy1.getBounds();
				if(formaTiro.intersects(formaEnemy1)) {
					tempEnemy1.setVisivel(false);
					tempTiro.setVisivel(false);
				}
			}
		}
	}

	private class TecladoAdapter extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			player.keyPressed(e);
		}

		@Override
		public void keyReleased(KeyEvent e) {
			player.keyRelease(e);
		}

	}

}
