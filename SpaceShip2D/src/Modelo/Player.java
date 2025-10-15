package Modelo;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class Player {
	
	//atributos de movimenta��o
	private int x,y;
	private int dx, dy;
	
	//imagem Personagem
	private Image imagem;
	
	//Colis�o
	private int altura,largura;
	
	private List<Tiro> tiros;
	
	private boolean isVisivel;
	
	
	
	//defini��o de Spawm personagem
	public Player() {
		this.x = 100;
		this.y = 500;
		isVisivel = true;
		
		tiros = new ArrayList<Tiro>();
	}
	
	//Define Imagem Player
	public void load() {
		ImageIcon referencia = new ImageIcon("res\\nave.png");
		imagem = referencia.getImage();
		altura = imagem.getHeight(null);
		largura = imagem.getWidth(null);
		
	}
	
	//Metodos de Movimento
	public void update() {
		x += dx;
		y += dy;
	}
	
	public void tiroSimples() {
		this.tiros.add(new Tiro(x + largura, y + (altura/2)));
	}
	
	public Rectangle getBounds() {
		return new Rectangle (x,y,largura,altura);
	}
	
	//Entradas Teclado
	public void keyPressed(KeyEvent tecla) {
		int codigo = tecla.getKeyCode();
		
		//a��es ao pressionar teclas
		
		if(codigo == KeyEvent.VK_A) {
			tiroSimples();
		}
		
		if(codigo == KeyEvent.VK_UP) {
			dy = -3;
			
		}
		
		if(codigo == KeyEvent.VK_DOWN) {
			dy = 3;
			
		}
		
		if(codigo == KeyEvent.VK_LEFT) {
			dx = -3;
			
		}
		
		if(codigo == KeyEvent.VK_RIGHT) {
			dx = 3;
			
		}
		
	}
	
	public void keyRelease(KeyEvent tecla) {
		int codigo = tecla.getKeyCode();
		
		//a��es ao soltar teclas
		
		if(codigo == KeyEvent.VK_UP) {
			dy = 0;
			
		}
		
		if(codigo == KeyEvent.VK_DOWN) {
			dy = 0;
			
		}
		
		if(codigo == KeyEvent.VK_LEFT) {
			dx =0 ;
			
		}
		
		if(codigo == KeyEvent.VK_RIGHT) {
			dx =0;
			
		}
		
	}
	
	

	public boolean isVisivel() {
		return isVisivel;
	}

	public void setVisivel(boolean isVisivel) {
		this.isVisivel = isVisivel;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Image getImagem() {
		return imagem;
	}

	public List<Tiro> getTiros() {
		return tiros;
	}
	
	
	
	
}
