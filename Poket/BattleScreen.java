package Poket;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.random.*;

public class BattleScreen extends JFrame {
    //파일 경로 설정
	private String imagePath = "\\";
    
	//패널에 필요한 요소들 모두 추가
    private PlayerMovePanel MP;
    
    private Pocketmon myPocketmon;
    private Pocketmon enemyPocketmon;
    
    private Battle battlePanel;
    
    private JLabel myLvInfo;
    private JLabel enemyLvInfo;
    private JLabel myNameInfo;
    private JLabel enemyNameInfo;
    private JLabel myHpInfo;
    private JLabel enemyHpInfo;
    
    private JPanel VariousButton;
    private JPanel ToolButton;
    private JPanel StartButton;
    
    private JButton FirstSkill = new JButton("몸통박치기");
    private JButton SecondSkill = new JButton("활퀴기");
    private JButton ThirdSkill = new JButton("화염방사");
    private JButton FourthSkill = new JButton("화염바퀴");
    
    private JButton FirstList = new JButton("가방");
    private JButton SecondList = new JButton("도망간다");
    private JButton ThirdList = new JButton("포켓몬");
    
    private JButton startButton = new JButton("전투 시작"); 
    private boolean myTurn = true;
    
    
    //생성자를 통해서 배틀스크린을 띄움
    public BattleScreen(PlayerMovePanel MP) {
        this.MP = MP;
        
        //포켓몬이라는 것을 초기화
        Pocketmon collection = new Pocketmon("", 0, 0, 0, 0,"", "");
        ///각각 마다 추가
        this.myPocketmon = collection.Generate_M();
        this.enemyPocketmon = collection.Generate_E();
        
        
        //배틀 패널을 그림
        this.battlePanel = new Battle(myPocketmon, enemyPocketmon) {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                setOpaque(false);                
            }
        };
        
        //배틀 패널 크기 설정 후 추가
        battlePanel.setBounds(0, 0, 500, 400);
        add(battlePanel);
        
        //각각 마다 내용 생성
        this.myLvInfo = new JLabel("LV : " + myPocketmon.levelReturn());
        this.enemyLvInfo = new JLabel("LV : " + enemyPocketmon.levelReturn());
        this.myNameInfo = new JLabel(myPocketmon.nameReturn());
        this.enemyNameInfo = new JLabel(enemyPocketmon.nameReturn());
        this.myHpInfo = new JLabel("");
        this.enemyHpInfo = new JLabel("");
        //int값을 setText에서 String값으로 받기 위해 String.valueOf 이용(웹사이트 참고), getter setter를 통해 값을 받음
        myHpInfo.setText(String.valueOf("HP :" + myPocketmon.healthReturn()) + "/" + String.valueOf(myPocketmon.MaxReturn()));
        enemyHpInfo.setText(String.valueOf("HP :" + enemyPocketmon.healthReturn()) + "/" + String.valueOf(enemyPocketmon.MaxReturn()));
        
        //기초 설정
        myLvInfo.setBounds(400, 240, 200, 30);
        enemyLvInfo.setBounds(150, 60, 200, 30);
        myNameInfo.setBounds(300, 240, 200, 30);
        enemyNameInfo.setBounds(50, 60, 200, 30);
        
        myHpInfo.setFont(new Font("Serif", Font.BOLD, 25));
        enemyHpInfo.setFont(new Font("Serif", Font.BOLD, 25));
        myHpInfo.setBounds(350, 260, 200, 30);
        enemyHpInfo.setBounds(110, 80, 200, 30);
        
        add(myLvInfo);
        add(enemyLvInfo);
        add(myNameInfo);
        add(enemyNameInfo);
        add(myHpInfo);
        add(enemyHpInfo);
        
        setLayout(null);
        MP.setEnabled(false);
        setName("전투 발생!");
        setSize(500, 800);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        VariousButton = new JPanel();
        ToolButton = new JPanel();
        StartButton = new JPanel();
        
        VariousButton.setLayout(new GridLayout(2, 2, 20, 20));
        VariousButton.setBounds(15, 400, 450, 225);
        
        ToolButton.setLayout(new GridLayout(1, 3, 20, 20));
        ToolButton.setBounds(15, 650, 450, 60);
        
        startButton.setBounds(30, 400, 420, 220);
        
        VariousButton.add(FirstSkill);
        VariousButton.add(SecondSkill);
        VariousButton.add(ThirdSkill);
        VariousButton.add(FourthSkill);
        
        FirstSkill.setEnabled(false);
        SecondSkill.setEnabled(false);
        ThirdSkill.setEnabled(false);
        FourthSkill.setEnabled(false);
        
        ToolButton.add(FirstList);
        ToolButton.add(SecondList);
        ToolButton.add(ThirdList);
        
        StartButton.add(startButton);
        
        this.add(VariousButton);
        this.add(ToolButton);
        this.add(startButton);
        
        //버튼 눌렀을 때 이벤트 발생(start를 누르기 전에는 스킬 버튼 비활성화)
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startButton.setVisible(false);
                FirstSkill.setEnabled(true);
                SecondSkill.setEnabled(true);
                ThirdSkill.setEnabled(true);
                FourthSkill.setEnabled(true);
                FirstList.setVisible(false);
                SecondList.setVisible(false);
                ThirdList.setVisible(false);
                revalidate();
            }
        });
        //스킬 1(모든 스킬들과 마찬가지로 체력 값이 0 이 되면 종료 후 다시 조작을 맵으로 넘김
        FirstSkill.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (myTurn) {
                	String skill = "몸통박치기";
                    int damage = myPocketmon.AtkReturn() - enemyPocketmon.DefReturn();
                    enemyPocketmon.Damage(damage);
                    Re_Hp();
                    myTurn = false;
                    buttonOnOff(myTurn);
                    emenmyTurn();
                    if(myPocketmon.healthReturn() == 0 || enemyPocketmon.healthReturn() == 0) {
                    	dispose();
                    	if (MP != null) {
                            MP.setEnabled(true);
                            MP.requestFocusInWindow();
                        }
                    }
                }
            }
        });
        //스킬 2
        SecondSkill.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (myTurn) {
                    int damage = myPocketmon.AtkReturn() - enemyPocketmon.DefReturn() - 1;
                    enemyPocketmon.Damage(damage);
                    Re_Hp();
                    myTurn = false;
                    buttonOnOff(myTurn);
                    emenmyTurn();
                    if(myPocketmon.healthReturn() == 0 || enemyPocketmon.healthReturn() == 0) {
                    	dispose();
                    	if (MP != null) {
                            MP.setEnabled(true);
                            MP.requestFocusInWindow();
                        }
                    }
                }
            }
        });
        //스킬 3(반동 데미지 추가)
        ThirdSkill.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (myTurn) {
                    int damage = myPocketmon.AtkReturn() * 3 - enemyPocketmon.DefReturn();
                    enemyPocketmon.Damage(damage);
                    Re_Hp();
                    myTurn = false;
                    buttonOnOff(myTurn);
                    emenmyTurn();
                    if(myPocketmon.healthReturn() == 0 || enemyPocketmon.healthReturn() == 0) {
                    	dispose();
                    	if (MP != null) {
                            MP.setEnabled(true);
                            MP.requestFocusInWindow();
                        }
                    }
                }
            }
        });
        //스킬 4
        FourthSkill.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (myTurn) {
                    int damage = myPocketmon.AtkReturn()*3 - enemyPocketmon.DefReturn();
                    enemyPocketmon.Damage(damage);
                    myPocketmon.Rebound(myPocketmon.AtkReturn());
                    Re_Hp();
                    myTurn = false;
                    buttonOnOff(myTurn);
                    emenmyTurn();
                    if(myPocketmon.healthReturn() == 0 || enemyPocketmon.healthReturn() == 0) {
                    	dispose();
                    	if (MP != null) {
                            MP.setEnabled(true);
                            MP.requestFocusInWindow();
                        }
                    }
                }
            }
        });
        //가방 버튼 클릭
        FirstList.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "상처약이\n존재하지 않습니다", "가방", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        //도망 버튼 클릭(종료 및 playermovePanel로)
        SecondList.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                if (MP != null) {
                    MP.setEnabled(true);
                    MP.requestFocusInWindow();
                }
            }
        });
        //포켓몬 버튼 클릭
        ThirdList.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               JOptionPane.showMessageDialog(null, "불꽃숭이", "포켓몬", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                dispose();
                if (MP != null) {
                    MP.setEnabled(true);
                    MP.requestFocusInWindow();
                }
            }
        });
        
        setResizable(false);
        setVisible(true);
    }
    //호출시 랜덤값에 따라 내 포켓몬에게 데미지를 줌
    private void emenmyTurn() {
    	Random rand = new Random();
    	int Act = rand.nextInt(1);
    	if(Act == 0) {
    		String name = "몸통박치기";
    		int damage = enemyPocketmon.AtkReturn() - myPocketmon.DefReturn() + 2;
    		myPocketmon.Damage(damage);
    	}
    	else if(Act == 1) {
    		String name = "강력한 공격";
    		int damage = enemyPocketmon.AtkReturn()*2 - myPocketmon.DefReturn();
    		enemyPocketmon.Damage(damage);
    	}
    	Re_Hp();
    	myTurn = true;
    	buttonOnOff(myTurn);
    }
    //턴과 턴 간격이 존재시 조금 더 자연스럽게 턴이 넘어가는 것을 보이기 위해 버튼의 활성화를 조절했지만 sleep 사용시 프로그램 자체가 멈춰서 보류
    private void buttonOnOff(boolean myTurn) {
    	FirstSkill.setVisible(myTurn);
        SecondSkill.setVisible(myTurn);
        ThirdSkill.setVisible(myTurn);
        FourthSkill.setVisible(myTurn);
        FirstList.setVisible(myTurn);
        SecondList.setVisible(myTurn);
        ThirdList.setVisible(myTurn);

        FirstSkill.setEnabled(myTurn);
        SecondSkill.setEnabled(myTurn);
        ThirdSkill.setEnabled(myTurn);
        FourthSkill.setEnabled(myTurn);
        revalidate();
    }
    //페인팅
    public void paint(Graphics g) {
        super.paint(g);
        myPocketmon.draw(g, 100, 500);
        enemyPocketmon.draw(g, 300, 100);
    }
    //Hp 상황을 다시 갱신하는 함수
    private void Re_Hp() {
        myHpInfo.setText("HP: " + myPocketmon.healthReturn() + "/" + myPocketmon.MaxReturn());
        enemyHpInfo.setText("HP: " + enemyPocketmon.healthReturn() + "/" + enemyPocketmon.MaxReturn());
    }
}
