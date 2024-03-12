package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.AbstractDocument.Content;

import exceptions.GameActionException;
import exceptions.InvalidPowerDirectionException;
import exceptions.InvalidPowerTargetException;
import exceptions.OccupiedCellException;
import exceptions.UnallowedMovementException;
import exceptions.WrongTurnException;
import model.game.Direction;
import model.game.Game;
import model.game.Player;
import model.pieces.Piece;
import model.pieces.heroes.ActivatablePowerHero;
import model.pieces.heroes.Armored;
import model.pieces.heroes.Medic;
import model.pieces.heroes.Ranged;
import model.pieces.heroes.Speedster;
import model.pieces.heroes.Super;
import model.pieces.heroes.Tech;
import model.pieces.sidekicks.SideKickP1;
import model.pieces.sidekicks.SideKickP2;


class startPanel extends JPanel
{
	private Image bg = new ImageIcon("601898.png").getImage();
	public void paintComponent(Graphics g)
	{
		g.drawImage(bg,0,0,getWidth(),getHeight(),this);
	}
}
class grave extends JPanel
{
	private Image bg = new ImageIcon("fortnite_campfire-1152x361.jpg").getImage();
	public void paintComponent(Graphics g)
	{
		g.drawImage(bg,0,0,getWidth(),getHeight(),this);
	}
}


public class board extends JFrame implements ActionListener{

	
	private Player player1, player2;
	private Game game ;
	private JTextArea t1, t2 ;
	private JButton [] [] board = new JButton [7][6];
	
	private JLabel background1 , cur1,cur2 ,back;
	private JPanel area1,area2;
	private JTextArea a1 ,a2,pay1,pay2,info;
	private JButton u,d,l,r,ul,ur,dl,dr,move,usepower,cancel;
	private JPanel payload1,payload2,dead1,dead2;
	private JButton[] dead1characters = new JButton[9];
	private JButton[] dead2characters = new JButton[9];
	private JProgressBar level1,level2;
	private int x=-1,y=-1,x1=-1,x2=-1,y1=-1,y2=-1;	private Direction direction;
	private String type;
	private JButton start,restart;
	private JPanel background;
	private JTextArea txt , currentplayer1, currentplayer2 ;
	private JTextArea grave;


	
	
	 
	public static void main(String[] args) {
		new board();
	} 
	public board ()
	
	{
		setDefaultLookAndFeelDecorated(true);
		setIconImage(new ImageIcon("SHC.png").getImage());
		txt = new JTextArea();
		background  = new  startPanel();
		start = new JButton();	
		start.setText("START");
		start.setBounds(240, 600, 400, 60);
		start.setOpaque(false);
		start.addActionListener(this);
		start.setFont(new Font(Font.MONOSPACED, Font.BOLD, 50));
		start.setBorder(null);
		start.setForeground(Color.white);
		start.setBackground(Color.BLACK);
		start.setContentAreaFilled(false);
		txt.setText("SUPERHERO CHESS");		
		txt.setOpaque(false);
		txt.setForeground(Color.WHITE);
		txt.setFont(new Font(Font.MONOSPACED, Font.BOLD, 55));
		txt.setBounds(200, 50, 700, 100);;
		txt.setEditable(false);	
		background.add(txt);
		background.add(start);
		setContentPane(background);
		
		
		player1 = new Player(JOptionPane.showInputDialog("Player 1 Name: "));
		player2 = new Player(JOptionPane.showInputDialog("Player 2 Name: "));
		game = new Game(player1, player2);
		setTitle("SUPERHERO CHESS");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLayout(null);
		setSize(900, 730);
		setLocationRelativeTo(null);
		back = new JLabel();
		back.setSize(getWidth(), getHeight());
		back.setBackground(Color.black);
		back.setLayout(null);
		background1 = new JLabel();
		background1.setLayout(new GridLayout(7, 6));
		background1.setBounds(160, 35, 540, 630);
		back.setIcon(new ImageIcon("BACKB.png"));
		
		
		for (int i = 0 ; i<7 ;i++)
		{
			for(int j =0 ; j<6 ; j++)
			{
				board[i][j] = new JButton();
				if (game.getCellAt(i, j).getPiece()!=null)
					if(game.getCellAt(i, j).getPiece() instanceof Medic)
						if(game.getCellAt(i, j).getPiece().getOwner()==game.getPlayer1())
							board[i][j].setIcon(new ImageIcon("MedicP1.png"));
						else
							board[i][j].setIcon(new ImageIcon("MedicP2.png"));
					else if(game.getCellAt(i, j).getPiece() instanceof SideKickP1)
						board[i][j].setIcon(new ImageIcon("Side1.png"));
					else if(game.getCellAt(i, j).getPiece() instanceof SideKickP2)
						board[i][j].setIcon(new ImageIcon("Side2.png"));
					else if(game.getCellAt(i, j).getPiece() instanceof Ranged)
						if(game.getCellAt(i, j).getPiece().getOwner()==game.getPlayer1())
							board[i][j].setIcon(new ImageIcon("RangedP1.png"));
						else
							board[i][j].setIcon(new ImageIcon("RangedP2.png"));
					else if(game.getCellAt(i, j).getPiece() instanceof Speedster)
						if(game.getCellAt(i, j).getPiece().getOwner()==game.getPlayer1())
							board[i][j].setIcon(new ImageIcon("SpeedP1.png"));
						else
							board[i][j].setIcon(new ImageIcon("SpeedP2.png"));
					else if(game.getCellAt(i, j).getPiece() instanceof Super)
						if(game.getCellAt(i, j).getPiece().getOwner()==game.getPlayer1())
							board[i][j].setIcon(new ImageIcon("SuperP1.png"));
						else
							board[i][j].setIcon(new ImageIcon("SuperP2.png"));
					else if(game.getCellAt(i, j).getPiece() instanceof Tech)
						if(game.getCellAt(i, j).getPiece().getOwner()==game.getPlayer1())
							board[i][j].setIcon(new ImageIcon("TechP1.png"));
						else
							board[i][j].setIcon(new ImageIcon("TechP2.png"));
					else if(game.getCellAt(i, j).getPiece() instanceof Armored)
						if(game.getCellAt(i, j).getPiece().getOwner()==game.getPlayer1())
							board[i][j].setIcon(new ImageIcon("ArmorP1.png"));
						else
							board[i][j].setIcon(new ImageIcon("ArmorP2.png"));
				
				board[i][j].addActionListener(this);
				
				if((i+j) %2==0)
					board[i][j].setBackground(new Color(235, 230, 230));
				else 
					board[i][j].setBackground(new Color(255, 140, 0));

				//board[i][j].setOpaque(false);
				//board[i][j].setBorder(null);
				//board[i][j].setContentAreaFilled(false);
				background1.add(board[i][j]);
			}
		}
		
		
		u = new JButton();
		u.setContentAreaFilled(false);
		u.setBounds(60, 500,40, 40);
		u.setIcon(new ImageIcon("UP.png"));
		u.setHorizontalAlignment(SwingConstants.CENTER);
		u.setBorder(null);
		u.setOpaque(false);
		u.setEnabled(false);
		ul = new JButton();
		ul.setContentAreaFilled(false);
		ul.setBounds(20, 500,40, 40);	
		ul.setOpaque(false);
		ul.setIcon(new ImageIcon("UPLEFT.png"));
		ul.setHorizontalAlignment(SwingConstants.CENTER);
		ul.setBorder(null);
		ul.setEnabled(false);
		ur = new JButton();
		ur.setContentAreaFilled(false);
		ur.setBounds(100, 500,40, 40);
		ur.setOpaque(false);
		ur.setIcon(new ImageIcon("UPRIGHT.png"));
		ur.setHorizontalAlignment(SwingConstants.CENTER);
		ur.setBorder(null);
		ur.setEnabled(false);
		r = new JButton();
		r.setContentAreaFilled(false);
		r.setBounds(100, 540,40, 40);
		r.setOpaque(false);
		r.setIcon(new ImageIcon("RIGHT.png"));
		r.setHorizontalAlignment(SwingConstants.CENTER);
		r.setBorder(null);
		r.setEnabled(false);
		l = new JButton();
		l.setContentAreaFilled(false);
		l.setBounds(20, 540,40, 40);
		l.setOpaque(false);
		l.setIcon(new ImageIcon("LEFT.png"));
		l.setHorizontalAlignment(SwingConstants.CENTER);
		l.setBorder(null);
		l.setEnabled(false);
		dl = new JButton();
		dl.setContentAreaFilled(false);
		dl.setBounds(20, 580,40, 40);
		dl.setOpaque(false);
		dl.setIcon(new ImageIcon("DOWNLEFT.png"));
		dl.setHorizontalAlignment(SwingConstants.CENTER);
		dl.setBorder(null);
		dl.setEnabled(false);
		d = new JButton();
		d.setContentAreaFilled(false);
		d.setBounds(60, 580,40, 40);
		d.setOpaque(false);
		d.setIcon(new ImageIcon("DOWN.png"));
		d.setHorizontalAlignment(SwingConstants.CENTER);
		d.setBorder(null);
		d.setEnabled(false);
		dr = new JButton();
		dr.setContentAreaFilled(false);
		dr.setBounds(100, 580,40, 40);
		dr.setOpaque(false);
		dr.setIcon(new ImageIcon("DOWNRIGHT.png"));
		dr.setHorizontalAlignment(SwingConstants.CENTER);
		dr.setBorder(null);
		dr.setEnabled(false);
		payload1 = new JPanel();
		payload1.setBackground(Color.white);
		payload1.setBounds(140, 35, 20, 630);
		payload2 = new JPanel();
		payload2.setBackground(Color.white);
		payload2.setBounds(700, 35, 20, 630);
		level1 = new JProgressBar(level1.VERTICAL);
		level1.setMaximum(6);
		level1.setMinimum(0);
		level1.setBounds(140, 35, 20, 630);
		level1.setOpaque(false);
		level1.setForeground(Color.blue);
		level2 = new JProgressBar(level2.VERTICAL);
		level2.setMaximum(6);
		level2.setMinimum(0);
		level2.setOpaque(false);
		level2.setForeground(Color.RED);
		level2.setBounds(700, 35, 20, 630);
		
		cancel = new JButton(new ImageIcon("CANCEL.png"));
		usepower = new JButton(new ImageIcon("POWER.png"));
		move = new JButton(new ImageIcon("MOVE.png"));
		cancel.setOpaque(false);
		usepower.setOpaque(false);
		move.setOpaque(false);
		cancel.setBorder(null);
		usepower.setBorder(null);
		move.setBorder(null);
		cancel.setContentAreaFilled(false);
		usepower.setContentAreaFilled(false);
		move.setContentAreaFilled(false);

		move.setBounds(20, 340,120,45);
		
		move.setForeground(Color.black);
		move.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		move.setEnabled(false);
		usepower.setBounds(20, 385, 120, 45);
		usepower.setForeground(Color.black);
		usepower.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		usepower.setEnabled(false);
		info = new JTextArea();
		info.setBounds(30, 130, 120, 50);
		info.setOpaque(false);
		info.setBorder(null);
		info.setEditable(false);
		info.setForeground(new Color(255, 140, 0));
		currentplayer2=new JTextArea(player2.getName());
		currentplayer2.setEditable(false);
		currentplayer2.setOpaque(false);
		currentplayer2.setFont(new Font(Font.MONOSPACED, Font.BOLD, 25));
		currentplayer2.setForeground(Color.WHITE);
		currentplayer2.setBounds(370, 0, 600, 30);
		
		currentplayer1=new JTextArea(player1.getName());
		currentplayer1.setEditable(false);
		currentplayer1.setOpaque(false);
		currentplayer1.setFont(new Font(Font.MONOSPACED, Font.BOLD, 25));
		currentplayer1.setForeground(Color.WHITE);
		currentplayer1.setBounds(370, 660, 600, 30);
		
		cur1 = new  JLabel();
		cur1.setBounds(330, 670, 30, 30);
		cur1.setBorder(null);
		cur1.setOpaque(false);
		cur1.setIcon(new ImageIcon("ARROWP1.png"));
		cur2 = new  JLabel();
		cur2.setBounds(330,5, 30, 30);
		cur2.setBorder(null);
		cur2.setOpaque(false);
		cur2.setVisible(false);
		cur2.setIcon(new ImageIcon("ARROWP2.png"));
		grave = new JTextArea();
		grave.setBounds(730, 25, 200, 60);
		grave.setText("GRAVE");
		grave.setEditable(false);
		grave.setBorder(null);
		grave.setForeground(Color.white);
		grave.setFont(new Font(Font.MONOSPACED, Font.BOLD, 50));
		grave.setOpaque(false);
		cancel.setBounds(20, 430, 120, 45);
		cancel.setForeground(Color.black);
		cancel.setEnabled(false);
		cancel.addActionListener(this);
		cancel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		dead1 = new JPanel();
		dead1.setBounds(700, 95, 200, 540);
		dead1.setOpaque(false);
		dead1.setLayout(new GridLayout(6,2));
		
		
		for (int i = 0 ; i<9 ;i++)
		{
				dead1characters[i]=new JButton();
				dead1characters[i].setVisible(false);
				dead1characters[i].setEnabled(false);
				dead1characters[i].setOpaque(false);
				dead1characters[i].setBorder(null);
				dead1characters[i].setContentAreaFilled(false);
				dead1characters[i].addActionListener(this);
				dead1.add(dead1characters[i]);
		}
		
		
		dead2 = new JPanel();
		dead2.setBounds(700, 95, 200, 540);
		dead2.setOpaque(false);
		dead2.setLayout(new GridLayout(6,2));
		for (int i = 0 ; i<9 ;i++)
		{
				dead2characters[i]=new JButton();
				dead2characters[i].setVisible(false);
				dead2characters[i].setEnabled(false);
				dead2characters[i].setContentAreaFilled(false);
				dead2characters[i].setOpaque(false);
				dead2characters[i].setBorder(null);
				dead2characters[i].addActionListener(this);
				dead2.add(dead2characters[i]);
		}
		
		
		
		restart = new JButton(new ImageIcon("RESTART.png"));
		restart.addActionListener(this);
		restart.setBounds(20, 50, 120, 45);
		restart.setContentAreaFilled(false);
		restart.setOpaque(false);
		restart.setBorder(null);
		
		
		
		move.addActionListener(this);
		usepower.addActionListener(this);
		u.addActionListener(this);
		d.addActionListener(this);
		r.addActionListener(this);
		l.addActionListener(this);
		ur.addActionListener(this);
		ul.addActionListener(this);
		dr.addActionListener(this);
		dl.addActionListener(this);
		back.add(restart);
		back.add(cur1);
		back.add(cur2);
		back.add(grave);
		back.add(level1);
		back.add(level2);
		back.add(dead2);
		back.add(dead1);
		back.add(cancel);
		back.add(currentplayer1);
		back.add(currentplayer2);
		back.add(info);
		back.add(usepower);
		back.add(move);
		back.add(payload1);
		back.add(payload2);
		back.add(d);
		back.add(l);
		back.add(r);
		back.add(dl);
		back.add(dr);
		back.add(ur);
		back.add(ul);
		back.add(u);
		
		back.add(background1);
		//setContentPane(back);
		setVisible(true);
		
	}
	public void updateBoard () {
		for(int i=0;i<9;i++) {
			dead1characters[i].setIcon(null);
			dead2characters[i].setIcon(null);
			dead1characters[i].setVisible(false);
			dead2characters[i].setVisible(false);
			dead1characters[i].setEnabled(false);
			dead2characters[i].setEnabled(false);
		}
		for(int i=0;i<7;i++)
			for(int j=0;j<6;j++)
				board[i][j].setIcon(null);
		for(int i=0;i<7;i++)
			for(int j=0;j<6;j++) {
				if (game.getCellAt(i, j).getPiece()!=null)
					if(game.getCellAt(i, j).getPiece() instanceof Medic)
						if(game.getCellAt(i, j).getPiece().getOwner()==game.getPlayer1())
							board[i][j].setIcon(new ImageIcon("MedicP1.png"));
						else
							board[i][j].setIcon(new ImageIcon("MedicP2.png"));
					else if(game.getCellAt(i, j).getPiece() instanceof SideKickP1)
						board[i][j].setIcon(new ImageIcon("Side1.png"));
					else if(game.getCellAt(i, j).getPiece() instanceof SideKickP2)
						board[i][j].setIcon(new ImageIcon("Side2.png"));
					else if(game.getCellAt(i, j).getPiece() instanceof Ranged)
						if(game.getCellAt(i, j).getPiece().getOwner()==game.getPlayer1())
							board[i][j].setIcon(new ImageIcon("RangedP1.png"));
						else
							board[i][j].setIcon(new ImageIcon("RangedP2.png"));
					else if(game.getCellAt(i, j).getPiece() instanceof Speedster)
						if(game.getCellAt(i, j).getPiece().getOwner()==game.getPlayer1())
							board[i][j].setIcon(new ImageIcon("SpeedP1.png"));
						else
							board[i][j].setIcon(new ImageIcon("SpeedP2.png"));
					else if(game.getCellAt(i, j).getPiece() instanceof Super)
						if(game.getCellAt(i, j).getPiece().getOwner()==game.getPlayer1())
							board[i][j].setIcon(new ImageIcon("SuperP1.png"));
						else
							board[i][j].setIcon(new ImageIcon("SuperP2.png"));
					else if(game.getCellAt(i, j).getPiece() instanceof Tech)
						if(game.getCellAt(i, j).getPiece().getOwner()==game.getPlayer1())
							board[i][j].setIcon(new ImageIcon("TechP1.png"));
						else
							board[i][j].setIcon(new ImageIcon("TechP2.png"));
					else if(game.getCellAt(i, j).getPiece() instanceof Armored)
						if(game.getCellAt(i, j).getPiece().getOwner()==game.getPlayer1())
							board[i][j].setIcon(new ImageIcon("ArmorP1.png"));
						else
							board[i][j].setIcon(new ImageIcon("ArmorP2.png"));
				
			}
		for(int i=0;i<game.getCurrentPlayer().getDeadCharacters().size();i++) {
			if(game.getCurrentPlayer().getDeadCharacters().get(i) instanceof Medic)
				if(game.getCurrentPlayer()==game.getPlayer1())
					dead1characters[i].setIcon(new ImageIcon("MedicP1.png"));
				else
					dead2characters[i].setIcon(new ImageIcon("MedicP2.png"));
			else if(game.getCurrentPlayer().getDeadCharacters().get(i) instanceof SideKickP1)
				dead1characters[i].setIcon(new ImageIcon("Side1.png"));
			else if(game.getCurrentPlayer().getDeadCharacters().get(i) instanceof SideKickP2)
				dead2characters[i].setIcon(new ImageIcon("Side2.png"));
			else if(game.getCurrentPlayer().getDeadCharacters().get(i) instanceof Ranged)
				if(game.getCurrentPlayer()==game.getPlayer1())
					dead1characters[i].setIcon(new ImageIcon("RangedP1.png"));
				else
					dead2characters[i].setIcon(new ImageIcon("RangedP2.png"));
			else if(game.getCurrentPlayer().getDeadCharacters().get(i) instanceof Speedster)
				if(game.getCurrentPlayer()==game.getPlayer1())
					dead1characters[i].setIcon(new ImageIcon("SpeedP1.png"));
				else
					dead2characters[i].setIcon(new ImageIcon("SpeedP2.png"));
			else if(game.getCurrentPlayer().getDeadCharacters().get(i) instanceof Super)
				if(game.getCurrentPlayer()==game.getPlayer1())
					dead1characters[i].setIcon(new ImageIcon("SuperP1.png"));
				else
					dead2characters[i].setIcon(new ImageIcon("SuperP2.png"));
			else if(game.getCurrentPlayer().getDeadCharacters().get(i) instanceof Tech)
				if(game.getCurrentPlayer()==game.getPlayer1())
					dead1characters[i].setIcon(new ImageIcon("TechP1.png"));
				else
					dead2characters[i].setIcon(new ImageIcon("TechP2.png"));
			else if(game.getCurrentPlayer().getDeadCharacters().get(i) instanceof Armored)
				if(game.getCurrentPlayer()==game.getPlayer1())
					dead1characters[i].setIcon(new ImageIcon("ArmorP1.png"));
				else
					dead2characters[i].setIcon(new ImageIcon("ArmorP2.png"));
			if(game.getCurrentPlayer()==player1)
				dead1characters[i].setVisible(true);
			else
				dead2characters[i].setVisible(true);
		}
		ur.setEnabled(false);
		dr.setEnabled(false);
		ul.setEnabled(false);
		dl.setEnabled(false);
		u.setEnabled(false);
		d.setEnabled(false);
		l.setEnabled(false);
		r.setEnabled(false);
		move.setEnabled(false);
		usepower.setEnabled(false);
		direction=null;
		x=-1;
		y=-1;
		type=null;
		if (game.getCurrentPlayer() == player1)
		{
			cur1.setVisible(true);
			cur2.setVisible(false);
		}
		else if (game.getCurrentPlayer() == player2)
		{
			cur2.setVisible(true);
			cur1.setVisible(false);
		}
			
		x1=-1;
		y1=-1;
		x2=-1;
		y2=-1;
		info.setText(null);
		cancel.setEnabled(false);
		level1.setValue(player1.getPayloadPos());
		level2.setValue(player2.getPayloadPos());
		if(player1.getPayloadPos()==6) {
			JOptionPane.showMessageDialog(null, player1.getName()+" is the winner");
			for(int i=0;i<7;i++)
				for(int j=0;j<6;j++)
					board[i][j].setEnabled(false);
		}
		else if(player2.getPayloadPos()==6) {
			JOptionPane.showMessageDialog(null, player2.getName()+" is the winner");
			for(int i=0;i<7;i++)
				for(int j=0;j<6;j++)
					board[i][j].setEnabled(false);
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==restart)
			new board();
		else if(e.getSource()==start) {
			setContentPane(back); repaint(); revalidate();
			
		}
		else if(e.getSource()==cancel) {
			ur.setEnabled(false);
			dr.setEnabled(false);
			ul.setEnabled(false);
			dl.setEnabled(false);
			u.setEnabled(false);
			d.setEnabled(false);
			l.setEnabled(false);
			r.setEnabled(false);
			move.setEnabled(false);
			usepower.setEnabled(false);
			direction=null;
			x=-1;
			y=-1;
			type=null;
			x1=-1;
			y1=-1;
			x2=-1;
			y2=-1;
			cancel.setEnabled(false);
			info.setText(null);
		}
		else if(e.getSource()==move)
			type="move";
		else if(e.getSource()==usepower)
			type="power";
		else if(e.getSource()==u)
			direction=Direction.UP;
		else if(e.getSource()==d)
			direction=Direction.DOWN;
		else if(e.getSource()==r)
			direction=Direction.RIGHT;
		else if(e.getSource()==l)
			direction=Direction.LEFT;
		else if(e.getSource()==ul)
			direction=Direction.UPLEFT;
		else if(e.getSource()==ur)
			direction=Direction.UPRIGHT;
		else if(e.getSource()==dl)
			direction=Direction.DOWNLEFT;
		else if(e.getSource()==dr)
			direction=Direction.DOWNRIGHT;	
		else if(x==-1 && y==-1)
			for(int i=0;i<7;i++)
				for(int j=0;j<6;j++) 
					if(board[i][j]==e.getSource()) {
						if(game.getCellAt(i, j).getPiece()!=null)
							if(game.getCellAt(i, j).getPiece().getOwner()==game.getCurrentPlayer()) {
								x=i; y=j; 
								cancel.setEnabled(true);
								type=null;
								if(game.getCellAt(x, y).getPiece() instanceof Speedster)
									info.setText("Piece: Speedster "+'\n'+"Belongs to: "+game.getCurrentPlayer().getName()+'\n'+"It has no power");
								else if(game.getCellAt(x, y).getPiece() instanceof SideKickP1 ||game.getCellAt(x, y).getPiece() instanceof SideKickP2)
									info.setText("Piece: SideKick "+'\n'+"Belongs to: "+game.getCurrentPlayer().getName()+'\n'+"It has no power");
								else if(game.getCellAt(x, y).getPiece() instanceof Medic)
									info.setText("Piece: Medic "+'\n'+"Belongs to: "+game.getCurrentPlayer().getName()+'\n'+"Power is used: "+((ActivatablePowerHero)game.getCellAt(x, y).getPiece()).isPowerUsed());
								else if(game.getCellAt(x, y).getPiece() instanceof Armored)
									info.setText("Piece: Armored "+'\n'+"Belongs to: "+game.getCurrentPlayer().getName()+'\n'+"Armor is Up: "+((Armored)game.getCellAt(x, y).getPiece()).isArmorUp());
								else if(game.getCellAt(x, y).getPiece() instanceof Ranged)
									info.setText("Piece: Ranged "+'\n'+"Belongs to: "+game.getCurrentPlayer().getName()+'\n'+"Power is used: "+((ActivatablePowerHero)game.getCellAt(x, y).getPiece()).isPowerUsed());
								else if(game.getCellAt(x, y).getPiece() instanceof Super)
									info.setText("Piece: Super "+'\n'+"Belongs to: "+game.getCurrentPlayer().getName()+'\n'+"Power is used: "+((ActivatablePowerHero)game.getCellAt(x, y).getPiece()).isPowerUsed());
								else if(game.getCellAt(x, y).getPiece() instanceof Tech)
									info.setText("Piece: Tech "+'\n'+"Belongs to: "+game.getCurrentPlayer().getName()+'\n'+"Power is used: "+((ActivatablePowerHero)game.getCellAt(x, y).getPiece()).isPowerUsed());
								info.setForeground(new Color(255, 140, 0));
							}
							else
								JOptionPane.showMessageDialog(null, "It is "+game.getCurrentPlayer().getName()+" turn");
					}	
		if(x!=-1 && y!=-1) {
			//if(x1==-1) {
				ur.setEnabled(false);
				dr.setEnabled(false);
				ul.setEnabled(false);
				dl.setEnabled(false);
				u.setEnabled(false);
				d.setEnabled(false);
				l.setEnabled(false);
				r.setEnabled(false);
				move.setEnabled(false);
				usepower.setEnabled(false);
				if(game.getCellAt(x, y).getPiece()!=null) {
					move.setEnabled(true);
					Piece p=game.getCellAt(x, y).getPiece();
					if(p instanceof Medic ||p instanceof Tech||p instanceof Super||p instanceof Ranged)
						if(((ActivatablePowerHero)p).isPowerUsed()==false)
							usepower.setEnabled(true);				}
			//}
		}
		if(type=="move") {
			if(game.getCellAt(x, y).getPiece()!=null) {
				if(game.getCellAt(x, y).getPiece() instanceof SideKickP1) {
					u.setEnabled(true);
					ur.setEnabled(true);
					ul.setEnabled(true);
					l.setEnabled(true);
					r.setEnabled(true);
				}
				else if(game.getCellAt(x, y).getPiece() instanceof SideKickP2) {
					d.setEnabled(true);
					dr.setEnabled(true);
					dl.setEnabled(true);
					l.setEnabled(true);
					r.setEnabled(true);
				}
				else if(game.getCellAt(x, y).getPiece() instanceof Tech) {
					ul.setEnabled(true);
					dl.setEnabled(true);
					ur.setEnabled(true);
					dr.setEnabled(true);
				}
				else if(game.getCellAt(x, y).getPiece() instanceof Medic) {
					u.setEnabled(true);
					d.setEnabled(true);
					r.setEnabled(true);
					l.setEnabled(true);
				}
				else if(game.getCellAt(x, y).getPiece() instanceof Super) {
					u.setEnabled(true);
					d.setEnabled(true);
					r.setEnabled(true);
					l.setEnabled(true);
				}
				else if(game.getCellAt(x, y).getPiece() instanceof Speedster) {
					u.setEnabled(true);
					d.setEnabled(true);
					r.setEnabled(true);
					l.setEnabled(true);
					ul.setEnabled(true);
					dr.setEnabled(true);
					ur.setEnabled(true);
					dl.setEnabled(true);
				}
				else if(game.getCellAt(x, y).getPiece() instanceof Armored) {
					u.setEnabled(true);
					d.setEnabled(true);
					r.setEnabled(true);
					l.setEnabled(true);
					ul.setEnabled(true);
					dr.setEnabled(true);
					ur.setEnabled(true);
					dl.setEnabled(true);
				}
				else if(game.getCellAt(x, y).getPiece() instanceof Ranged) {
					u.setEnabled(true);
					d.setEnabled(true);
					r.setEnabled(true);
					l.setEnabled(true);
					ul.setEnabled(true);
					dr.setEnabled(true);
					ur.setEnabled(true);
					dl.setEnabled(true);
				}
			}
		}
		else if(type=="power") {
			if(game.getCellAt(x, y).getPiece()!=null) {
				 if(game.getCellAt(x, y).getPiece() instanceof Ranged) {
					u.setEnabled(true);
					d.setEnabled(true);
					l.setEnabled(true);
					r.setEnabled(true);
				}
				else if(game.getCellAt(x, y).getPiece() instanceof Super) {
					u.setEnabled(true);
					d.setEnabled(true);
					l.setEnabled(true);
					r.setEnabled(true);
				}
				else if(game.getCellAt(x, y).getPiece() instanceof Medic) {
					u.setEnabled(true);
					d.setEnabled(true);
					l.setEnabled(true);
					r.setEnabled(true);
					ur.setEnabled(true);
					dr.setEnabled(true);
					ul.setEnabled(true);
					dl.setEnabled(true);
				}
			}
		}
		if(type=="move") {
			if(direction!=null)
				if(game.getCellAt(x, y).getPiece()!=null) {
					try {
						if(game.getCellAt(x, y).getPiece() instanceof Speedster)
							((Speedster)(game.getCellAt(x, y).getPiece())).move(direction);
						else if(game.getCellAt(x, y).getPiece() instanceof SideKickP1)
							((SideKickP1)(game.getCellAt(x, y).getPiece())).move(direction);
						else if(game.getCellAt(x, y).getPiece() instanceof SideKickP2)
							((SideKickP2)(game.getCellAt(x, y).getPiece())).move(direction); 
						else if(game.getCellAt(x, y).getPiece() instanceof Medic)
							((Medic)(game.getCellAt(x, y).getPiece())).move(direction);
						else if(game.getCellAt(x, y).getPiece() instanceof Armored)
							((Armored)(game.getCellAt(x, y).getPiece())).move(direction);
						else if(game.getCellAt(x, y).getPiece() instanceof Ranged)
							((Ranged)(game.getCellAt(x, y).getPiece())).move(direction);
						else if(game.getCellAt(x, y).getPiece() instanceof Super)
							((Super)(game.getCellAt(x, y).getPiece())).move(direction);
						else if(game.getCellAt(x, y).getPiece() instanceof Tech)
							((Tech)(game.getCellAt(x, y).getPiece())).move(direction);
						updateBoard();
					}
					catch(GameActionException e1) {
						if(e1 instanceof UnallowedMovementException)
							JOptionPane.showMessageDialog(null,"Unallowed movement");
						else if(e1 instanceof OccupiedCellException)
							JOptionPane.showMessageDialog(null, "The directory cell is occupied");
					}
				}
			}
		if(type=="power")
			if(direction!=null) {
				if(game.getCellAt(x, y).getPiece()!=null) {
					try {
						if(game.getCellAt(x, y).getPiece() instanceof Super) {
							((Super)(game.getCellAt(x, y).getPiece())).usePower(direction, null, null);
							updateBoard();
						}
						else if(game.getCellAt(x, y).getPiece() instanceof Ranged) {
							((Ranged)(game.getCellAt(x, y).getPiece())).usePower(direction, null, null);
							updateBoard();
						}
						else if(game.getCellAt(x, y).getPiece() instanceof Medic) {
							for(int i=0;i<9;i++)
								if(game.getCurrentPlayer()==player1) 
									dead1characters[i].setEnabled(true);
								else if(game.getCurrentPlayer()==player2) 
									dead2characters[i].setEnabled(true);
							for(int i=0;i<game.getCurrentPlayer().getDeadCharacters().size();i++) {
								if(game.getCurrentPlayer()==player1) {
									if(e.getSource()==dead1characters[i])
										x1=i;
								}
								else if(game.getCurrentPlayer()==player2)
									if(e.getSource()==dead2characters[i])
										x1=i;
							}
							if(x1!=-1) {
								((Medic)game.getCellAt(x, y).getPiece()).usePower(direction, game.getCurrentPlayer().getDeadCharacters().get(x1), null);
								updateBoard();
							}
							else if(game.getCurrentPlayer().getDeadCharacters().size()==0)
								JOptionPane.showMessageDialog(null, "There are no dead pieces");
						}
					}
					catch(GameActionException e1) {
						if(e1 instanceof InvalidPowerTargetException)
							JOptionPane.showMessageDialog(null,((InvalidPowerTargetException)e1).getMessage());
						else if(e1 instanceof InvalidPowerDirectionException)
							JOptionPane.showMessageDialog(null,((InvalidPowerDirectionException)e1).getMessage());
					}
				}
			}
			else {
				if(game.getCellAt(x, y).getPiece()!=null) {
					try {
						if(game.getCellAt(x, y).getPiece() instanceof Tech) {
							if(x1==-1 && y1==-1)
								for(int i=0;i<7;i++)
									for(int j=0;j<6;j++) 
										if(board[i][j]==e.getSource()) {
											x1=i; y1=j; 
										}
							if(x1!=-1 && y1!=-1)
								if(game.getCellAt(x1, y1).getPiece()==null) {
									for(int i=0;i<7;i++)
										for(int j=0;j<6;j++) 
											if(board[i][j]==e.getSource()) {
												x2=i; y2=j; 
											}
									if(game.getCellAt(x2, y2).getPiece()!=null) {
										((Tech)game.getCellAt(x, y).getPiece()).usePower(null, game.getCellAt(x2, y2).getPiece(), new Point(x1,y1));
										updateBoard();
									}
								}
								else {
									((Tech)game.getCellAt(x, y).getPiece()).usePower(null, game.getCellAt(x1, y1).getPiece(),null);
									updateBoard();
								}
							}
					}
					catch(GameActionException e1) {
						if(e1 instanceof InvalidPowerTargetException)
							JOptionPane.showMessageDialog(null,((InvalidPowerTargetException)e1).getMessage());
						else if(e1 instanceof InvalidPowerDirectionException)
							JOptionPane.showMessageDialog(null,((InvalidPowerDirectionException)e1).getMessage());
					}
				}
				
			}
	}
}
