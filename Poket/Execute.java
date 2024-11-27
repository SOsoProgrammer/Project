package Poket;
import javax.swing.*;
public class Execute {

	public static void main(String[] args) {
		final String imagePath = "C:\\Users\\xorud\\Desktop\\team_project\\Team\\src\\Poket\\Image\\";
		int[][] map = {{1,1,1,1,1,1,1,1,1,1},
					   {1,9,9,9,9,9,9,9,9,1},
					   {1,0,0,0,0,0,0,9,9,1},
					   {1,0,0,0,0,0,0,0,0,1},
					   {1,0,0,0,0,0,0,9,9,1},
					   {1,8,0,0,0,0,0,9,9,1},
					   {1,8,8,0,0,0,0,0,0,1},
					   {1,8,8,8,0,0,0,0,0,1},
					   {1,8,8,8,8,0,0,9,9,1},
					   {1,1,1,1,1,1,1,1,1,1}};
		JFrame frame = new JFrame("포켓몬 게임");
		PlayerMovePanel panel = new PlayerMovePanel(map, imagePath);
		frame.getContentPane().add( panel );
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		frame.pack();
		frame.setVisible(true);
	}
}
