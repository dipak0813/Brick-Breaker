import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class Genrator 
{
	public int map[][];
	public int brickwidth;
	public int brickheight;
	public Genrator(int row, int col) 
	{
		map=new int[row][col];
		for(int i = 0; i < map.length; i=i+2) 
		{
			for(int j = 0; j<map[0].length; j=j+2 ) 
			{
				map[i][j]= 1 ;
			}
			
		}
		brickwidth=68;
		brickheight=45;
	}
	public void draw(Graphics2D g) 
	{
		for(int i = 0; i < map.length; i=i+2) 
		{
			for(int j = 0; j<map[0].length; j=j+2 ) 
			{
				if(map[i][j]>0) 
				{
					g.setColor(Color.white);
					g.fillRect(j * brickwidth + 80, i * brickheight + 50, brickwidth, brickheight);
					g.setStroke(new BasicStroke(8));
					g.setColor(Color.black);
					g.drawRect(j * brickwidth + 80, i * brickheight + 50, brickwidth, brickheight);
				}
			}
		}
	}
	public void setBrickValue(int value, int row, int col) 
	{
		map[row][col] = value;	
	}
}
