package Poket;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;


public class PlayerMovePanel extends JPanel {
	private int percent;
	private int x, y, maxX, maxY;
	
	//arrow는 방향 조절을 위해 설정
	private int arrow = 3;
	
	//각각의 방향을 조정
	private final int FRONT = 2, BACK = 3, LEFT = 4, RIGHT = 5;
	
	
	private int[][] map;
	private Image[] image;
	private final int Size = 50, Scope = 3, Tile = 0, Character = 2, Wall = 1, Water = 8, Grass = 9;
	public PlayerMovePanel(int map[][], final String imagePath) {
		this.x = 5;
		this.y = 5;
		this.map = map;
		this.maxX = map[0].length-1;
		this.maxY = map.length-1;
		this.image = new Image[10];
		this.image[2] = new ImageIcon(imagePath + "character_front.png").getImage();
		this.image[3] = new ImageIcon(imagePath + "character_back.png").getImage();
		this.image[4] = new ImageIcon(imagePath + "character_left.png").getImage();
		this.image[5] = new ImageIcon(imagePath + "character_right.png").getImage();
		this.image[0] = new ImageIcon(imagePath + "tile.png").getImage();
		this.image[8] = new ImageIcon(imagePath + "Water.png").getImage();
		this.image[9] = new ImageIcon(imagePath + "Grass.png").getImage();
		this.arrow = 5;
		
		setPreferredSize(new Dimension(Size *(2*Scope+1), Size*(2*Scope+1)));
		addKeyListener(new ArrowKeyListener());
		setFocusable(true);
		requestFocus();
		setBackground(Color.WHITE);
	}
	//수몽이 움직이기 코드 참조
	//arrow를 추가해 방향에 따른 이미지를 호출한다
	private class ArrowKeyListener extends KeyAdapter{
		public void keyPressed(KeyEvent e) {
			switch(e.getKeyCode()) {
			case KeyEvent.VK_ESCAPE:
				System.exit(0);
				break;
			case KeyEvent.VK_LEFT: case 'a': case'A':
				arrow = LEFT;
				move(-1,0);
				break;
			case KeyEvent.VK_RIGHT: case 'd': case 'D':
				arrow = RIGHT;
				move(1, 0);
				break;
			case KeyEvent.VK_UP: case 'w': case'W':
				arrow = BACK;
				move(0, -1);
				break;
			case KeyEvent.VK_DOWN: case 's': case 'S':
				arrow = FRONT;
				move(0, 1);
				break;
			}
			repaint();
		}
	}
	public void move(int x, int y) {
		this.x = this.x + x;
		this.y = this.y + y;
		this.x = ( this.x <= 0 ) ? 0 : this.x;
		this.y = ( this.y <= 0 ) ? 0 : this.y;
		this.x = ( this.x >= maxX ) ? maxX : this.x;
		this.y = ( this.y >= maxY ) ? maxY : this.y;
		if( map[this.y][this.x] == Wall  ) {
			this.x -= x;
			this.y -= y;
		}
		else if(map[this.y][this.x] == Water)
		{
			this.x -= x;
			this.y -= y;
		}
		
		//잔디일때는 일정확률로 Battle 진행
		
		else if(map[this.y][this.x] == Grass) {
			Random random = new Random();
			percent = random.nextInt(100);
			if(percent <= 10) {
				BattleScreen BS = new BattleScreen(this);
				BS.setVisible(true);
			}
		}
		this.requestFocusInWindow(true);
	}
	
	//paint 코드와 동일하지만 일부 변경
	public void paint( Graphics g ){
		super.paint( g );
		g.fillRect(0, 0, getWidth(), getHeight());
		for( int y = this.y - Scope, y2 = 0; y <= this.y + Scope; y++, y2++ ){
			for( int x = this.x - Scope, x2 = 0; x <= this.x + Scope; x++, x2++ ){
				int index = Tile;
				if ( ( this.x == x ) && ( this.y == y ) )
					;
				else if ( ( 0 <= x ) && ( x <= maxX ) && ( 0 <= y ) && ( y <= maxY ) )
					index = map[y][x];
				g.drawImage( this.image[index], x2*Size, y2*Size, Size, Size, null );
			}
			
			//내가 플레이하는 캐릭터의 좌표값을 가져와 움직이도록 만든다
			//방향을 조절하는 것을 받아오기 위한 위치 조정
			int characterX = (this.x - (this.x - Scope));
	        int characterY = (this.y - (this.y - Scope));

	        g.drawImage(this.image[arrow], characterX*Size, characterY*Size, Size, Size, null);
		}
	}
}