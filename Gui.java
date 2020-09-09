package snake;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class Gui extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton play;
	private JButton exit;
	
	//costruttore
	public Gui() {
		super("Snake");// Chiama i metodi dalla superclass
		setLayout(new FlowLayout());
		setPreferredSize(new Dimension(300,300));
		setResizable(false);
		JLabel background = new JLabel(new ImageIcon("src/snake/snake2.jpg"));
        add(background);
        background.setLayout(new FlowLayout());
        
		Icon e = new ImageIcon(getClass().getResource("ex1.png"));
		exit = new JButton();	
		exit.setIcon(e);
		exit.setPreferredSize(new Dimension(210, 90));
		
		Icon p = new ImageIcon(getClass().getResource("sna.png"));
		Icon pq = new ImageIcon(getClass().getResource("apple.jpg"));
		play = new JButton();
		play.setIcon(p);
		play.setPreferredSize(new Dimension(210, 90));
		play.setRolloverIcon(pq);
		
		background.add(play);
		background.add(exit);
		
		HandlerClass handler = new HandlerClass();
		HandlerClass1 handler1 = new HandlerClass1();
		play.addActionListener(handler);
		exit.addActionListener(handler1);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	private class HandlerClass implements ActionListener {
		public void actionPerformed(ActionEvent event) { 
			
			Snake.Start();
			dispose();
				
			}	
		}
	private class HandlerClass1 implements ActionListener {
		public void actionPerformed(ActionEvent event) { 
		
			System.exit(0);
			
		}
					
			 	
	}
	
	
}
