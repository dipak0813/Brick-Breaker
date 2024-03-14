import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Screen extends JPanel implements KeyListener, ActionListener 
{
	private boolean play=false;
	private int score=0;
	private int totalbricks=64;
	private Timer timer;
	private int delay=16;
	private int playerX=600;
	private int ballposX=650;
	private int ballposY=700;
	private int ballXdir=-3;
	private int ballYdir=-2;
	
	private Genrator map;
	
	
	public Screen() 
	{
		map = new Genrator(8,18);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer =new Timer(delay,this);
		timer.start();	
	}
	
	public void paint(Graphics g) {
		//background
		g.setColor(Color.orange);
		g.fillRect(1,1,1310,740);
		
		// drawing map
		map.draw((Graphics2D)g);
		
		// score
		g.setColor(Color.black);
		g.setFont(new Font("serif", Font.BOLD,25));
		g.drawString("Your Score is :"+score, 1100,30);
		
		//border
		g.setColor(Color.red);
		g.fillRect(0,0,3,740);
		g.fillRect(0,0,1310,3);
		g.fillRect(1303,0,3,740);
		g.fillRect(0,737,1310,3);
		
		// the paddle
		g.setColor(Color.red);
		g.fillRect(playerX,720,120,120);
		
		// the ball
		g.setColor(Color.blue);
		g.fillOval(ballposX,ballposY,20,20);
		
		g.setColor(Color.white);
		g.setFont(new Font("serif", Font.BOLD,20));
		//g.drawString("https://www.a2itonline.com" , 20,25);
		
		
		if(totalbricks <=0 || score>=400){	
			play=false;
			ballXdir=0;
			ballYdir=0;
			g.setColor(Color.green);
			g.setFont(new Font("serif", Font.BOLD,35));
			g.drawString("You Win, Score :" +score , 530,400);
			
			g.setFont(new Font("serif", Font.BOLD,35));
			g.drawString("Press Enter to Restart " , 530,450);
			
		}
		
		
		if(ballposY > 715) {
			play=false;
			ballXdir=10;
			ballYdir=10;
			g.setColor(Color.red);
			g.setFont(new Font("serif", Font.BOLD,35));
			g.drawString("Game Over, Score :" +score , 530,400);
			
			g.setFont(new Font("serif", Font.BOLD,35));
			g.drawString("Press Enter to Restart " , 530,450);
			
			
		}
		
		g.dispose();
		
	}
//.......................................................................................
	public void actionPerformed(ActionEvent arg0) 
	{
		// TODO Auto-generated method stub
		timer.start();
		if(play) 
		{
			if(new Rectangle(ballposX,ballposY,20,20).intersects(new Rectangle(playerX, 700, 120, 20))) 
			{
				ballYdir=--ballYdir;
			}
			
			A: for(int i =0; i<map.map.length; i++ ) 
			{
				for(int j=0; j<map.map[0].length; j++) 
				{
					if(map.map[i][j]>0) 
					{
						int brickX=j*map.brickwidth+80;
						int brickY=i*map.brickheight+50;
						int brickwidth = map.brickwidth;
						int brickheight = map.brickheight;
						
						Rectangle rect= new Rectangle(brickX, brickY, brickwidth, brickheight);
						Rectangle ballRect = new Rectangle(ballposX, ballposY,20,20);
						Rectangle brickRect =rect;
						if(ballRect.intersects(brickRect)) 
						{
							map.setBrickValue(0, i, j);
							totalbricks--;
							score +=10 ;
							
							if(ballposX +19 <= brickRect.x || ballposX+1 >= brickRect.x + brickRect.width) 
							{
								ballXdir=-ballXdir;
								
							}
							else 
							{
								ballYdir=-ballYdir;
							}
							
							break A;
						}	
					}
				}
			}
			
			
			ballposX+=ballXdir;
			ballposY+=ballYdir;
			if(ballposX < 0) 
			{
				ballXdir=-ballXdir;
			}
			if(ballposY < 0) 
			{
				ballYdir =-ballYdir;
			}
			if(ballposX > 1287) 
			{
				ballXdir=-ballXdir;
			}
		}
		repaint();
		
	}

	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode()==KeyEvent.VK_RIGHT || e.getKeyCode()==KeyEvent.VK_D ||e.getKeyCode()==KeyEvent.VK_L) {
			if(playerX >= 1187) 
			{
				playerX = 1187;
			}
			else 
			{
				moveRight();
			}
		}
		
		if (e.getKeyCode()==KeyEvent.VK_LEFT || e.getKeyCode()==KeyEvent.VK_A || e.getKeyCode()==KeyEvent.VK_K) {
			if(playerX<=0) 
			{
				playerX=0;
			}
			else 
			{
				moveLeft();
			}
		}
		
		if(e.getKeyCode()==KeyEvent.VK_ENTER) {
			if(!play) {
				play=true;
				ballposX=650;
				ballposY=700;
				ballXdir=-3;
				ballYdir=-2;
				playerX=600;
				score=0;
				totalbricks=64;
				map = new Genrator(8,18);
				repaint();
			}
		}
		
	}
	
	public void moveRight() {
		play=true;
		playerX+=20;
		
	}
	
	public void moveLeft() {
		play=true;
		playerX-=20;
		
	}
//........................................................................
	public void keyReleased(KeyEvent arg0) {}

	public void keyTyped(KeyEvent arg0) {}
	

}
