package Poket;
import javax.swing.*;
import java.awt.*;

public class Battle extends JPanel{
	private Pocketmon myPocketmon;
	private Pocketmon enemyPocketmon;
	//battleScreen에 들어갈 수 있도록 포켓몬 전달
	public Battle(Pocketmon myPocketmon, Pocketmon enemyPocketmon) {
		this.myPocketmon = myPocketmon;
		this.enemyPocketmon = enemyPocketmon;
	}
	//paintcomponent 실행
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		setBackground(Color.WHITE);
		if (myPocketmon != null) {
            myPocketmon.draw(g, 100, 220);
        	}
	}
}
