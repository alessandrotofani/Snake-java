package snake;


//IMPLEMENTAZIONE DI SNAKE


import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JLabel;



public class Snake {

	public static void Start() //mi serve per rendere privata la classe Creation
	{
		
		Creation();	
		
	}

	public static void Creation() //metodo creazione pannello
	{
		
	JFrame panel = new JFrame("Snake Demo"); //abbiamo creato un pannello per il gioco
	
	JLabel emptyLabel = new JLabel("");
	emptyLabel.setPreferredSize(new Dimension(1200,900)); //dimensioni del pannello
	
	panel.getContentPane().add(emptyLabel,BorderLayout.CENTER); //getContentPane() prende il contenuto del pannello
	panel.setResizable(true);//Permette di togliere il tasto che allarga il Frame
	panel.pack();
	
	panel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Chiude il programma se clicchiamo sulla X del Frame
	
	Body serpente = new Body();//Istanza della classe punto
	panel.add(serpente);//Aggiunta dell'oggetto punto al Frame cos� che ricrei tutto quello che abbiamo dichiarato nella classe Point
	
	panel.setVisible(true); // Comando ultimo da aggiungere per fare in modo che il Frame sia visibile e non in background
	panel.setLocationRelativeTo(null);//centra il frame a met� dello schermo
	}
	
}