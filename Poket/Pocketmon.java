package Poket;
import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Pocketmon {
	final String imagePath = "";
	private String name;
	private Image image;
	private int Max_health;
	private int health;
	private int atk;
	private int def;
	private String level;
	
	//포켓몬을 만들기 위한 생성자
	
	public Pocketmon(String name, int health, int atk, int def, int max, String level, String imagePath) {
		this.name = name;
		this.health = health;
		this.atk = atk;
		this.def = def;
		this.level = level;
		this.Max_health = max;
		this.image = new ImageIcon(imagePath).getImage();
	}
	
	//적 포켓몬을 랜덤으로 지정해서 반환해줌
	
	public Pocketmon Generate_E() {
		Random rand = new Random();
		String[] name = {"찌르꼬", "비버니", "꼬렛"};
		int[] health = {20 + rand.nextInt(5), 23 + rand.nextInt(10), 17 + rand.nextInt(5)};
		int[] Max = {health[0], health[1], health[2]};
		int[] atk = {5 + rand.nextInt(2), 3, 3 + rand.nextInt(2)};
		int[] def = {rand.nextInt(2), 3, rand.nextInt(1)};
		String[] Path = {imagePath + "bird.png", imagePath + "animal.png", imagePath + "rat.png"};
		String[] level = {"3", "3", "3"};
		int r_count = rand.nextInt(3);
		int selectedHealth = health[r_count];
		
		return new Pocketmon(name[r_count], selectedHealth, atk[r_count], def[r_count], Max[r_count], level[r_count], Path[r_count]);
	}
	
	//내 포켓몬의 경우 고정으로 값을 정한 후 반환
	
	public Pocketmon Generate_M() {
		String name = "불꽃숭이";
		int health = 28;
		int atk = 6;
		int def = 2;
		int Max_health = 28;
		String level = "3";
		String Path = imagePath + "mine.png";
		
		return new Pocketmon(name, health, atk, def, Max_health, level, Path);
	}
	
	public void draw(Graphics g, int sizeX, int sizeY) {
		g.drawImage(image, sizeX, sizeY, 100, 100, null);
	}
	
	//기본적인 전투를 위한 getter & setter
	
	public String nameReturn() {
		return this.name;
	}
	public int healthReturn() {
		return this.health;
	}
	public String levelReturn() {
		return level;
	}
	public int MaxReturn() {
		return this.Max_health;
	}
	public int AtkReturn() {
		return this.atk;
	}
	public int DefReturn() {
		return this.def;
	}
	
	//데미지를 입게 만드는 함수
	
	public void Damage(int damage) {
		this.health = this.health - damage;
		if(this.health < 0)
			this.health = 0;
	}
	
	// 반동 데미지
	
	public void Rebound(int damage) {
		this.health = this.health - damage*2;
		if(this.health < 0)
			this.health = 0;
	}
	
	//방어를 위해 만들어놓은 코드(사용 X)
	
	public void UpDefense(int defense) {
		this.def = this.def + defense;
	}
}
