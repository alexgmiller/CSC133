package com.mycompany.a1;
import com.codename1.ui.events.ActionListener; 

import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent; 
import com.codename1.ui.Form;
import java.lang.String;
public class Game extends Form {
	private GameWorld gw;
	private char hotKey;
	public Game() {
		gw = new GameWorld();
		gw.init();
		play();
	}	
	private void setKey(char key)
	{
		this.hotKey=key;
	}
	private char getKey() {
		return this.hotKey;
	}

	private void play() {
		Label myLabel=new Label("Enter a Command:"); 
		this.addComponent(myLabel);
		final TextField myTextField = new TextField(); 
		this.addComponent(myTextField); 
		this.show();
			myTextField.addActionListener(new ActionListener(){ 
			public void actionPerformed(ActionEvent evt) {
			String sCommand = myTextField.getText().toString(); 
			 myTextField.clear();
			if(sCommand.length() != 0)
				switch (sCommand.charAt(0)) { 
					case 'a':
						gw.transferSpaceshipToAlien();
						break;
					case 'o':
						gw.transferSpaceshipToAstronaut();
						break;
					case 'l':
						gw.moveLeft();
						break;
					case 'r':
						gw.moveRight();
						break;
					case 'u':
						gw.moveUp();
						break;
					case 'd':
						gw.moveDown();
						break;
					case 'e':
						gw.expandDoor();
						break;
					case 'c':
						gw.contractDoor();
						break;
					case 't':
						gw.gameTick();
						break;
					case 's':
						gw.updateScore();
						break;
					case 'w':
						gw.handleCollisionWithAlien();
						break;
					case 'f':
						gw.collideAlienAndAstronaut();
						break;
					case 'p':
						gw.printGameState();
						break;
					case 'm':
						gw.printMap();
						break;
					case 'x':
						setKey(sCommand.charAt(0));
						System.out.println("Are you sure to exit game?");
						break;
					 case 'N':
					 case 'n':
						if(getKey() == 'x') 
							System.out.println("Game continue");
						else {
							System.out.println("Invalid input");
						}
							break;
					 case 'Y':
					 case 'y':
						 if(getKey() == 'x')
							 gw.exit();
						 else {
							 System.out.println("Invalid input");
						 }
						 break;
					default:
						System.out.println("\nYour Input is invalid.Please enter valid command!!!\n");
						break;
					}
				} 
			 }
		);
		}
	}
	
	
	
	