package snake;


//IMPLEMENTAZIONE DI TUTTI I METODI DEL GIOCO


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Body extends JPanel implements ActionListener, KeyListener{
	
	
	//DICHIARAZIONI 
	
	
	public int x, y, xspeed=30, yspeed=0, a, b, c, d, xapple, yapple, xspider, yspider, score;
	public Image apple, spider;
	public boolean p = false;
	public boolean inizio = true;
	Random rand = new Random();
	Timer timer ; 
	
	List<Point> parti,ragno; //abbiamo creato una lista di parti per costruire il serpente
	                         //ogni pezzetto del corpo segue la testa
	                         //abbiamo creato una lista di ragni in modo che si generino più ragni sul pannello di gioco
	
	//METODI
	
	
	public Body() {  //costruttore
		
	    startgame(); //richiamo il metodo startgame con tutte le sue azioni	    
	}
	
	public void image() {  //metodo che colloca l'immagine della mela e del ragno, presa dalla cartella scritta
		
		ImageIcon ap = new ImageIcon(getClass().getResource("src/snake/apple1.png")); 
		apple = ap.getImage();
		randomposition();
		
		ImageIcon sp = new ImageIcon(getClass().getResource("src/snake/ragno4.jpg"));
		spider = sp.getImage();
		randomposition1();
		
	}
	
	public void randomposition() {  //metodo che genera le coordinate randomiche della mela nel frame
		
		xapple = 30*rand.nextInt(40);  //le coordinate saranno multipli di 30
		yapple = 30*rand.nextInt(20);
		
	}

	public void randomposition1() {  //metodo che genera le coordinate randomiche nel frame per ogni ragno della lista 
		
		ragno = new ArrayList<>(); //richiama la lista di ragni creata 
		
		for(int i=0; i<4; i++) {  //i<4, così abbiamo impostato un max di 4 ragni nel frame
			
			xspider = 30*rand.nextInt(40); //le coordinate saranno multipli di 30
			yspider = 30*rand.nextInt(20);
			ragno.add(new Point(xspider-30*i,yspider));

		}	
	}
	
	public void paintComponent(Graphics g) {  //metodo che disegna sul frame tutti i componenti grafici
		
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
	
		drawapple(g); //mela
		drawscore(g); //scorecount
	    grid(g); //griglia
	    spider(g); //lista di ragni
	    parts(g); //lista delle parti del serpente
	    	
	}
	
	public void startgame() {  //metodo che definisce l'inizio del gioco
		
		inizio = true; //variabile booleana
		
		x = 30*rand.nextInt(40); //questo significa che mi prende un numero randomico tra 1 e 40 e lo moltiplica per 30. 
	                             //ci genera quindi una posizione randomica multiplo di 30 siccome il nostro Frame � 1200x900
	    y = 30*rand.nextInt(30);
	   
	    score = 0; //se gameover lo score viene resettato
	    
	    image(); //richiama il metodo image che colloca le immagini di ragno e mela prese dalla cartella di appartenenza
	    
	    addKeyListener(this);//servono per permettere all'utente di cliccare le frecce direzionali e ricevere risposta dal programma
	                         //senza di queste tre le freccette non farebbero nulla
	    setFocusable(true);
	    setFocusTraversalKeysEnabled(true);

		parti = new ArrayList<>(); //richiama la lista di parti del serpente creata
		
		for(int i=0; i<3; i++) {  //con un ciclo for facciamo in modo che la lunghezza iniziale del serpentino sia 3 quaretti
			
			parti.add(new Point(x-30*i,y)); //.add aggiunge un pezzettino
		}
		
	}
	
	public void restartgame() { //metodo che determina il restart del gioco
		
		inizio = true; //variabile booleana
		
		x = 30*rand.nextInt(40); //questo significa che mi prende un numero randomico tra 1 e 40 e lo moltiplica per 30. 
        						 //ci genera quindi una posizione randomica multiplo di 30 siccome il nostro Frame � 1200x900
		y = 30*rand.nextInt(30);

		score = 0; //se gameover lo score si resetta

		timer = new Timer(100, this); //100 è la velocità
		timer.start(); // fa partire il serpente

		parti = new ArrayList<>(); //richiama la lista di parti del serpente creata

		for(int i=0; i<3; i++) { //con un ciclo for facciamo in modo che la lunghezza iniziale del serpentino sia 3 quaretti
			
			parti.add(new Point(x-30*i,y)); //.add aggiunge un pezzettino
			
		}
		
	}
	
	public void gameover() { //metodo che determina il gameover quando il serpente mangia se stesso
		
		timer.stop(); //il tempo si ferma ovvero il serpente si stoppa
		
		if (JOptionPane.showConfirmDialog(null, "Snake: You eat yourself. \nRestart the Game?", "GAME OVER !",  
            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {  
	        //messaggio che viene stampato in una finestra interattiva
			
            restartgame(); //se si clicca l'opzione yes allora viene richiamato il metodo di restart del gioco ed il gioco si riavvia    
		}
		else {
			System.exit(ABORT); //altrimenti, se si clicca l'opzione no, il programma termina
		}
		
	}
	
	public void gameover1() { //metodo che determina il gameover quando il serpente mangia un ragno
		
		timer.stop(); //il tempo si ferma ovvero il serpente si stoppa
		
		if (JOptionPane.showConfirmDialog(null, "Snake: You eat a spider. \nRestart the Game?", "GAME OVER !", 
            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
			//messaggio che viene stampato in una finestra interattiva
			
            startgame();  //se si clicca l'opzione yes allora viene richiamato il metodo di inizio del gioco ed il gioco si riavvia    
		}
		else {
			System.exit(ABORT); //altrimenti, se si clicca l'opzione no, il programma termina
		}
		
	}
	
	public void grid(Graphics g) {  //metodo che disegna la griglia guida
		
		g.setColor(Color.black); //setta il colore delle linee nero
		
	    /* disegna le linee verticali */
		
	    for(a=0; a<=getWidth(); a=a+30) {     //getWidth prende la larghezza dello schermo 
	    									  //getHeight prende l'altezza dello schermo
	    	g.drawLine(a, 0, a, getHeight()); //g.drawLine disegna le linee
	    }

	    /* disegna le linee orizzontali */
	    
	    for(b=0; b<=getHeight(); b=b+30) { 
	    	
	    	g.drawLine(0, b, getWidth(), b);
	    }
	}
	
	public void parts(Graphics g) {  //metodo che disegna le parti del serpente 
	
		g.setColor(Color.black); //setta il colore nero per tutto il corpo del serpente
		
		for(Point e : parti) { //ciclo for che prende la lista delle parti del serpente
			
	    	g.fillRect(e.x,e.y,30,30); //.fillRect riempie il quadratico con il colore nero per le coordinate di ogni pezzetto del corpo del serpente
	    }
		
		g.setColor(Color.green); //setta il colore verde per la testa del serpente
		
		Point testa = parti.get(0); //.get(0) prende il pezzetto zero del serpente cioè la testa
		
        g.fillRect(testa.x, testa.y, 30, 30); //.fillRect riempie il quadratico con il colore verde per le coordinate della testa del serpente
        
	}
	
	public void spider(Graphics g) {  //metodo che disegna il ragno
		
		for(Point r : ragno) { //ciclo for che prende la lista del ragno
			
			g.drawImage(spider,r.x,r.y,this); //disegna i ragni nelle coordinate r.x ed r.y			
		}
	}
	
	public void drawapple(Graphics g) {  //metodo che disegna la mela
		
		p = check(); //richiama il metodo check
		
		if(p=true) { //con questo if facciamo in modo che la mela non si generi nè sul serpente nè sui ragni
			
			g.drawImage(apple,xapple,yapple,this); //disegna la mela nelle coordinate xapple ed yapple
			
			p = false;
		}
	}
	
	public boolean check() {  //metodo booleano che 
		
		for(Point r : ragno) { //ciclo for che prende la lista del ragno
			
			if(xapple != x && yapple != y && xapple != r.x && yapple != r.y) {
				//la condizione dell'if confronta tutte le coordinate di mela, snake e ragni
				
				p = true; //se la condizione è vero settiamo p=true
				
			}else { //se non è verificata la condizione dell'if allora: 
				    //setta p=false, assegna nuove coordinate randomiche alla mela con randomposition e rifà la check 
				
				p = false;
				randomposition(); //richiama il metodo randomposition della mela
				check(); //richiama la check
			}
		}
		return p; //metodo booleano quindi si ha il return
	}
	
	public void drawscore(Graphics g) {  //metodo che disegna lo scorecount sul frame
		
		g.setColor(Color.black); //setta il colore nero della scritta
		g.setFont(new Font("Courier", Font.BOLD, 30 )); //setta lo stile, la grandezza ed il grassetto(bold)
		String scoreCount = "Score: " + score; //stringa di testo + varibile score che si aggiorna ogni volta che snake mangia la mela
	    g.drawString(scoreCount,57, 57); //disegna la stringa sul frame nelle coordinate x=57, y=57 
	    
		}
	
	public void hits() { //metodo che verifica le condizioni di gameover
		
		Point testa = parti.get(0); //.get(0) prende il pezzetto zero del serpente cioè la testa
		
		for(int i=1; i<parti.size(); i++) { //ciclo for che prende tutta la lunghezza del serpente con .size
			
			Point corpo = parti.get(i); //.get(i) prende il pezzetto i-esimo del corpo del serpente 
			
			if(testa.x == corpo.x && testa.y == corpo.y && inizio == false) {
				//se le coordinate della testa (testa.x e testa.y) coincidono con le coordinate del corpo (corpo.x e corpo.y) 
				//allora gameover
				gameover();
			}
		}
		
	}
	
	public void actionPerformed(ActionEvent e) {  //metodo che definisce le principali azioni
												  //questa funzione, oltre ad aggiornare la posizione del serpente, 
												  //lo ricrea dall'altro lato del frame se arriva al bordo
			
		for (int i = parti.size() -1; i > 0; i--) { //fa muovere tutto il corpo assieme con capo la testa
           
			Point p1 = parti.get(i - 1);
            Point p2 = parti.get(i);
            p2.x = p1.x;
            p2.y = p1.y;
        }
		
		Point testa = parti.get(0);     
		testa.x += xspeed;
		testa.y += yspeed;
		
		//questi cicli for ed if-else annidati servono per fare in modo che quando si genera il pannello di gioco, questo si adatti al
		//monitor con dimensioni sempre multipli di 30 ed inoltre si adatta anche la griglia
		
		if(testa.x<0) {
			
			if(getWidth() % 30 == 0) {
				
				testa.x = (getWidth() - 30);
				
			}else {
				
				for(int i = 0; i<30; i++) {
					
					if((getWidth() - i) % 30 == 0) {
						
						testa.x = (getWidth() - i);
					}
				}
			}
			
		}else if(testa.x + 30 > getWidth()) {
			
			testa.x = 0;
		}
		
		if(testa.y<0) {
			
			if(getHeight() % 30 == 0) {
				
				testa.y = (getHeight() - 30);
				
			}else {
				
				for(int i = 0; i<30; i++) {
					
					if((getHeight() - i) % 30 == 0) {
						
						testa.y = (getHeight() - i);
					}
				}
			}
			
		}else if(testa.y + 30 > getHeight()) {
			
			testa.y = 0;
		}
		
			
		if(testa.x==xapple && testa.y==yapple) {
			//se le coordinate della testa del serpente (testa.x, testa.y) coincidono con quelle della mela 
			//allora il serpente si incrementa di 1 e si generano nuove coordinate per la posizione di mela e ragni
			randomposition();
			randomposition1();
			
			//qui sotto le righe che determinano la crescita del serpente 
			
			Point coda = parti.get(parti.size()-1); //.get prende tutta la lunghezza del serpente con .size 
			int c = coda.x + xspeed; //coordinata x della cosa + la velocità lungo x
			int d = coda.y + yspeed; //coordinata y della cosa + la velocità lungo y (che è uguale a 0)
									 //+ xspeed e + yspeed fanno in modo che ogni pezzetto aggiuntivo del serpente segua la testa
			parti.add(new Point (c,d)); //.add aggiunge un pezzo al serpente
			
			score++; //se il serpente mangia la mela allora lo score viene incrementato di 1 (è inizializzato a 0)
			
			if(score > 10) { //se lo score>10 aumenta la velocità del serpente
				
				timer.stop();
				timer = new Timer(50,this); //50 è la velocità
				timer.start();
			}
			
			if(score > 30) { //se lo score>30 aumenta la velocità del serpente 
				
				timer.stop();
				timer = new Timer(20,this); //20 è la velocità
				timer.start();
			}
			
		}
		
		for(Point s : ragno) { //ciclo for che prende la lista del ragno
			
			if(testa.x==s.x && testa.y==s.y) { 
				//se le coordinate della testa (testa.x e testa.y) coincidono con le coordinate del ragno (s.x e s.y) 
				//allora gameover1	
				gameover1();
			}
		}
		
		hits(); //richiama il metodo hits con tutte le sue azioni
		repaint(); //aggiorna tutto
		
	}
	
	
	//le prossime 4 funzioni stabiliscono il cambiamento della direzione del serpente
	
	
	public void up() { 
		yspeed = -30;
		xspeed = 0;
	}
	
	public void down() {
		yspeed = 30;
		xspeed = 0;
	}
	
	public void left() {
		xspeed = -30;
		yspeed = 0;
	}
	
	public void right() {
		xspeed = 30;
		yspeed = 0;
	}
	
	
	//attraverso il KeyPressed noi assegnamo ad una delle quattro frecce direzionali una funzione di quelle 4 qua sopra
	// che modificano l'andamento del serpente
	
	
	public void keyPressed(KeyEvent e) {
		int input = e.getKeyCode();
		
		if(input == KeyEvent.VK_UP) {
			up();
		}
		
		if(input == KeyEvent.VK_DOWN) {
			down();
		}
		
		if(input == KeyEvent.VK_LEFT) {
		    left();
		}
		
		if(input == KeyEvent.VK_RIGHT) {
			right();
		}
		inizio = false;
	}
	
	public void keyTyped(KeyEvent e) {}
	
	public void keyReleased(KeyEvent e) {}
	
}
