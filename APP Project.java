import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
class Game extends JPanel implements ActionListener
{
	boolean leftup; 
	boolean leftdown; 
	boolean rightup;   
	boolean rightdown; 
	
	int leftx,lefty,rightx,righty; 
	
	Timer time; 
	int delay=25; 
	
	boolean inGame;
	
	boolean ballleft; 
	boolean ballright; 
	boolean ballup; 
	boolean balldown; 
	
	int ballx,bally; 
	
	int flag=0; 
	int win=0; 
	Game(int x,int y,int width,int height)
	{
		
		setBorder(BorderFactory.createMatteBorder(5,5,5,20, Color.red));
		
            addKeyListener(new KeyMover());
            setBackground(Color.white); 
		setFocusable(true);
		setVisible(true);
		setBounds(x,y,width,height);    
		
		initGame(); 
	}
	
	public void initGame()
	{
		leftdown=false;
		leftup=false;
		rightdown=false;
		rightup=false;
		
		leftx=20;
		lefty=300;
		
		rightx=1070;
		righty=300;
		
		inGame=true;
		
		delay=50;
		
		ballleft=false;
		ballright=true;
		ballup=true;
		balldown=false;
		
		win=0;
		
		ballx=50;
		bally=150;
		
		time=new Timer(delay, this);
		time.start();
		
	}
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		doPaint(g);
		
	}
	public void move()
	{
		if(leftup) 
		{
			if(lefty==0) 
			{
				lefty=0; 
			}
			else
			{
				lefty-=20; 
			}
		}
		if(leftdown) 
		{
			if(lefty==500)
			{
				lefty=500; 
			}
			else
			{
				lefty+=20; 
			}
		}
		if(rightup) 
		{
			if(righty==0) 
			{
				righty=0; 
			}
			else
			{
				righty-=20; 
			}
		}
		if(rightdown) 
		{
			if(righty==500) 
			{
				righty=500; 
			}
			else
			{
				righty+=20; 
			}
		}
		
		if(ballright && ballup) 
		{
			if(bally==0) 
			{  
				ballup=false;
				balldown=true; 
				ballx+=10;
				bally+=10;
			}
			else
			{ 
				ballx+=10;
				bally-=10;
			}
			return;
		}
		if(ballright && balldown) 
		{ 
			if(bally==600)
			{ 
				ballup=true;
				balldown=false;
				ballx+=10;
				bally-=10;
			}
			else
			{ 
				ballx+=10;
				bally+=10;
			}
			return;
		}
		if(ballleft && ballup)
		{ 
			if(bally==0)
			{ 
				ballup=false;
				balldown=true;
				ballx-=10;
				bally+=10;
			}
			else
			{ 
				ballx-=10;
				bally-=10;
			}
			return;
		}
		if(ballleft && balldown)
		{
			if(bally==600)
			{ 
				ballup=true;
				balldown=false;
				ballx-=10;
				bally-=10;
			}
			else
			{ 
				ballx-=10;
				bally+=10;
			}
			return;
		}
		
	}
	
   
	public void checkWins()
	{
		if(ballx<=0)
		{ 
			inGame=false;
			win=1;
		}
		if(ballx>=1100)
		{ 
			inGame=false;
			win=0;
		}
		if(ballx==rightx)
		{ 
			if(bally>=righty && bally<=(righty+100))
			{ 
				ballright=false;
				ballleft=true;
			}
			
		}
		if(ballx==leftx)
		{ 
			if(bally>=lefty && bally<=(lefty+100))
			{ 
				ballright=true;
				ballleft=false;
			}
		}
	}
	
    
	public void doPaint(Graphics g)
	{
		if(inGame)
		{ 
			g.setColor(Color.black);
			g.setFont(new Font("MV Boli", Font.BOLD, 25));
        	g.drawString("Use W and D", 850,50);
			
			g.setColor(Color.black);
			g.setFont(new Font("MV Boli", Font.BOLD, 25));
        	g.drawString("Use Q and A", 50,50);

			g.setColor(Color.blue);	
			g.drawLine(550, 0, 550, 600);

			g.setColor(Color.magenta);
			g.fillOval(ballx, bally, 10, 10);
			
			g.setColor(Color.yellow);	
			g.fillRect(leftx, lefty, 5, 100);
			
			g.setColor(Color.green);
			g.fillRect(rightx, righty, 5, 100);
			
		}
		else
		{ 
			gameOver(g);
		}
		Toolkit.getDefaultToolkit().sync();
	}
	
	public void gameOver(Graphics g)
	{
		String msg = "GAME OVER";
        Font small = new Font("cambria", Font.BOLD, 35);
        FontMetrics metr = getFontMetrics(small);

        int width=this.getWidth();
        int height=this.getHeight();
        
        g.setColor(Color.cyan);
        g.setFont(small);
        
        g.drawString(msg, (width - metr.stringWidth(msg)) / 2, height/2 - 50);
        g.setColor(Color.red);
        if(win==0)
        {
        	g.drawString("Left Player Wins!", (width - metr.stringWidth(msg))/2 - 60, height/2  + 10);
        }
        else
        {
        	g.drawString("Right Player Wins!", (width - metr.stringWidth(msg))/2 - 30, height/2  + 10);
        }
        
		g.setColor(Color.orange);
		g.setFont(new Font("century", Font.BOLD, 40));
		g.drawString("Press Enter to Restart", 
		(width - metr.stringWidth(msg))/2 - 120, height/2  + 50);
	}


	 @Override
	 public void actionPerformed(ActionEvent e) 
	 {

		 if (inGame) 
		 {	 
			 checkWins();
			 move(); 
	     }
		 
	     repaint();
	 
	 }
	 
     
	 public class KeyMover extends KeyAdapter 
	 {

	        @Override
	        public void keyPressed(KeyEvent e) {

	            int key = e.getKeyCode(); 

	            if ((key == KeyEvent.VK_Q) ) {
	               leftup=true;
	               leftdown=false;
	            }

	            if ((key == KeyEvent.VK_A) ) { 
	               leftup=false;
	               leftdown=true;
	            }

	            if ((key == KeyEvent.VK_W) ) { 
	                rightup=true;
	                rightdown=false;
	            }

	            if ((key == KeyEvent.VK_D)) { 
	            	rightup=false;
	                rightdown=true;
	            }

				if((key == KeyEvent.VK_ENTER)){ 
					if(!inGame){
						initGame();
					}
				}

	        }

	        @Override
	        public void keyReleased(KeyEvent e) {

	        	int key = e.getKeyCode();

	            if ((key == KeyEvent.VK_Q) ) {
	               leftup=false;               
	            }

	            if ((key == KeyEvent.VK_A) ) {
	               leftdown=false;
	            }

	            if ((key == KeyEvent.VK_W) ) {
	                rightup=false;
	            }

	            if ((key == KeyEvent.VK_D)) {
	                rightdown=false;
	            }
				
	        }

	    }
}


class Frame extends JFrame implements ActionListener,MouseListener
{
	Container cont;
	int height,width,x,y;
	Game b1;
	String lab;
	Frame(int x,int y,int width,int height)
	{
		cont = getContentPane();
		cont.setLayout(null);
		setLayout(null);
	
		this.height=height;
		this.width=width;
		this.x=x;
		this.y=y;
		
		setBounds(x,y,width,height);
		setVisible(true);
		setLayout(null);
	
		b1=new Game(0,0,width,height);
		cont.add(b1);
		
		
		setLocationRelativeTo(null);
        setResizable(false);
        pack();
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
	}

	public void actionPerformed(ActionEvent ae)
	{
		lab=ae.getActionCommand();
	}

	public void mouseClicked(MouseEvent e) {}  
	public void mouseEntered(MouseEvent e) {}
	
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
}

 class Pong {

	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
            @Override
			public void run() {                
                Frame e1 = new Frame(0,0,1100,600);
                e1.setBounds(200,150,1100,638);
                e1.setVisible(true);                
            }
        });
	}

}