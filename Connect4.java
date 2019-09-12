import javax.swing.*;
import java.lang.Exception;
import java.awt.*;
import java.awt.event.*;
public class Connect4 {
	JFrame theFrame;
	JPanel thePanel;
	boolean win;
	int currentPlayer;
	int row = 6;
	int col = 6;
	int[][] board;
	static int size = 0;
	static int seq = 0;
	JButton[][] button;
	JButton newGame;
	JButton winner;
	JButton error;
	GridLayout theGrid;
	final ImageIcon background = new ImageIcon("background.png");
	final ImageIcon player1 = new ImageIcon("player1.png");
	final ImageIcon player2 = new ImageIcon("player2.png");
	int numberToWin = 4;
	
	public Connect4(){
		theGrid = new GridLayout(row+1,col);
		board = new int[row][col];
		currentPlayer = 1;
		theFrame = new JFrame("Connect Four");
		theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		thePanel = new JPanel();
		thePanel.setLayout(theGrid);
		newGame = new JButton("New Game");
		newGame.addActionListener(new NewGameListener());
		newGame.setPreferredSize(new Dimension(10,10));
		winner = new JButton("Winner : None.");
		winner.setPreferredSize(new Dimension(200,10));
		error = new JButton("Action : ");
		error.setPreferredSize(new Dimension(275,10));
		
		button = new JButton[row][col];
		for(int i = 0; i < row; i++){
			for(int j = 0; j < col; j++){
				button[i][j] = new JButton(background);
				button[i][j].addActionListener(new ButtonListener());
				button[i][j].setBackground(Color.YELLOW);
				thePanel.add(button[i][j]);
			}
		}
		
		thePanel.add(winner);
		thePanel.add(newGame);
		thePanel.add(error);
		theFrame.setContentPane(thePanel);
		theFrame.pack();
		theFrame.setVisible(true);
	}
	
	public Connect4(int x, int y){
		row = x;
		col = x;
		numberToWin = y;
		theGrid = new GridLayout(row+1,col);
		board = new int[row][col];
		currentPlayer = 1;
		theFrame = new JFrame("Connect Four");
		theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		thePanel = new JPanel();
		thePanel.setLayout(theGrid);
		newGame = new JButton("New Game");
		newGame.addActionListener(new NewGameListener());
		newGame.setPreferredSize(new Dimension(10,10));
		winner = new JButton("Winner : None.");
		winner.setPreferredSize(new Dimension(200,10));
		error = new JButton("Action : ");
		error.setPreferredSize(new Dimension(275,10));
		
		button = new JButton[row][col];
		for(int i = 0; i < row; i++){
			for(int j = 0; j < col; j++){
				button[i][j] = new JButton(background);
				button[i][j].addActionListener(new ButtonListener());
				button[i][j].setBackground(Color.YELLOW);
				thePanel.add(button[i][j]);
			}
		}
		
		thePanel.add(winner);
		thePanel.add(newGame);
		thePanel.add(error);
		theFrame.setContentPane(thePanel);
		theFrame.pack();
		theFrame.setVisible(true);
	}
	
	class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent action) {
			if(win == false){
				for(int i = 0; i < col; i++){
					for(int j = 0; j < row; j++){
						if(button[j][i] == action.getSource()){						
							if(currentPlayer == 1 && board[j][i] == 0){
								error.setText("Action : Good.");
								if(j == row-1){
									button[j][i].setIcon(player1);
									board[j][i] = 1;	
								}
								else{
									for(int k = j; k < row; k++){
										if(board[k+1][i] == 1 || board[k+1][i] == 2){
											button[k][i].setIcon(player1);
											board[k][i] = 1;
											break;
										}
										else if ((k + 1 == row-1 && board[k + 1][i] == 0)){
											button[k+1][i].setIcon(player1);
											board[k+1][i] = 1;
											break;
										}
									}
								}
								if(checkWin()){
									winner.setText("Winner : Player 1 has won!");
								}
								currentPlayer = 2;
								break;
							}		
							else if(currentPlayer == 2 && board[j][i] == 0){
								error.setText("Action : Good.");
								if(j == row-1){
									button[j][i].setIcon(player2);
									board[j][i] = 2;
								}
								else{
									for(int k = j; k < row; k++){
										if(board[k+1][i] == 1 || board[k+1][i] == 2){
											button[k][i].setIcon(player2);
											board[k][i] = 2;
											break;
										}
										else if ((k + 1 == row-1 && board[k + 1][i] == 0)){
											button[k+1][i].setIcon(player2);
											board[k+1][i] = 2;
											break;
										}
									}
								}
								if(checkWin()){
									winner.setText("Winner : Player 2 has won!");
								}
								currentPlayer = 1;
								break;
							}
							else{
								error.setText("Action : invalid move. Spot already taken.");
								break;
							}	
						}
					}
				}
			}
			else{
				error.setText("Action : Game is over.");
			}
		}	
	}
	class NewGameListener implements ActionListener{
		public void actionPerformed(ActionEvent action){
			currentPlayer = 1;
			error.setText("Action : ");
			winner.setText("Winner : None.");
			win = false;
			for(int i = 0; i < row; i++){
				for(int j = 0; j < col; j++){
					board[i][j] = 0;
					button[i][j].setIcon(background);
				}
			}
		}
	}
	
	public boolean checkWin() {
		if(checkHorizontal()){
			win = true;
			return win;
		}
		else if(checkVertical()){
			win = true;
			return win;
		}
		else if(checkDiagonalPos()){
			win = true;
			return win;
		}
		else if(checkDiagonalNeg()){
			win = true;
			return win;
		}
		return win;
	}
	private boolean checkHorizontal(){
		int counter = 0;	
		for(int i = 0; i < row; i++){
			for(int j = 0; j < col; j++){
				if(board[i][j] == currentPlayer){
					counter++;
				}
				else{
					counter = 0;
				}
				if(counter == numberToWin){
					return true;
				}
			}
			counter = 0;
		}
		return false;
	}
	private boolean checkVertical(){
		int counter = 0;	
		for(int i = 0; i < col; i++){
			for(int j = 0; j < row; j++){
				if(board[j][i] == currentPlayer){
					counter++;
				}
				else{
					counter = 0;
				}
				if(counter == numberToWin){
					return true;
				}
			}
			counter = 0;
		}
		return false;
	}
	private boolean checkDiagonalPos(){
		int counter = 0;
		for(int i = 0; i <= col-numberToWin; i++){
			for(int j = numberToWin-1; j < row; j++){
				int y = i;
				int z = j;
				if(board[j][i] == currentPlayer){
					counter++;
					for(int x = 0; x < numberToWin-1; x++){
						z--;
						y++;
						if(board[z][y] == currentPlayer){
							counter++;
						}
						else{
							counter = 0;
						}
					}
				}
				
				if(counter == numberToWin){
					return true;
				}
			}
			counter = 0;
		}
		return false;
	}
	private boolean checkDiagonalNeg(){
		int counter = 0;
		for(int i = 0; i <= col-numberToWin; i++){
			for(int j = 0; j <= row-numberToWin; j++){
				int y = i;
				int z = j;
				if(board[j][i] == currentPlayer){
					counter++;
					for(int x = 0; x < numberToWin-1; x++){
						z++;
						y++;
						if(board[z][y] == currentPlayer){
							counter++;
						}
						else{
							counter = 0;
						}
					}
				}
				
				if(counter == numberToWin){
					return true;
				}
			}
			counter = 0;
		}
		return false;
	}
	
	public static void main(String[] args) {
		if(args.length > 0 && args.length <= 2){
			try{
				size = Integer.parseInt(args[0]);
				seq = Integer.parseInt(args[1]);
			}
			catch(Exception e){
				System.out.println(e);
			}
		}
		if(size == 0 && seq == 0){
			javax.swing.SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					JFrame.setDefaultLookAndFeelDecorated(true);
					new Connect4();
				}
			});
		}
		else if(size >=6 && size <=15 && seq < size && seq > 2){
			javax.swing.SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					JFrame.setDefaultLookAndFeelDecorated(true);
					new Connect4(size, seq);
				}
			});
		}
		else{
			if(size < 6)
				System.out.println("Size of board too small, choose a size between 6 and 15.");
			if(size > 15)
				System.out.println("Size of board too small, choose a size between 6 and 15.");
			if(seq > size)
				System.out.println("Sequence cannot be larger then size of board.");
			if(seq <= 2)
				System.out.println("Sequence must be greater then 2.");
		}
	}
}
