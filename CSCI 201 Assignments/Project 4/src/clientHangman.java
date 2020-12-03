import java.io.BufferedReader;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Properties;
import java.util.Scanner;


public class clientHangman {
	public static String replaceLett(String str, String ch, int index) {
	    return str.substring(0, index) + ch.toUpperCase() + str.substring(index+1);
	}
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		boolean success = false;
		String userInputFile ="";
		String realuserInputFile = "";
		String fileLine = "";
		String serverHostname = "";
		String serverPort ="";
		String DBConnection = "";
		String DBUsername = "";
		String DBPassword = "";
		String SecretWordFile = "";
		String username = "";
		String password = "";
		String userExist = "";
		String passExist = "";
		Boolean login = false;
		Boolean ServerisConnected = true;
		Properties prop = new Properties();
		while(!success) {
			try {
				System.out.println("What is the name of the configuration file?");
				userInputFile = input.nextLine();
				InputStream in = new FileInputStream(userInputFile);
				prop.load(in);
				serverHostname = prop.getProperty("ServerHostname", "NULL");
				serverPort = prop.getProperty("ServerPort", "NULL");	
				DBConnection = prop.getProperty("DBConnection", "NULL");
				DBUsername = prop.getProperty("DBUsername", "NULL");
				DBPassword = prop.getProperty("DBPassword", "NULL");
				SecretWordFile = prop.getProperty("SecretWordFile", "NULL");
				
				if(serverHostname.equals("NULL")){
					throw new Exception("ServerHostname is a required parameter in the configuration file");
				}
				else if(DBConnection.equals("NULL")) {
					throw new Exception("DBConnection is a required parameter in the configuration file");
				}
				else if(DBUsername.equals("NULL")) {
					throw new Exception("DBUsername is a required parameter in the configuration file");
				}
				else if(DBPassword.equals("NULL")) {
					throw new Exception("DBPassword is a required parameter in the configuration file");
				}
				else if(SecretWordFile.equals("NULL")) {
					throw new Exception("SecretWordFile is a required parameter in the configuration file");
				}
				else if(serverPort.equals("NULL")) {
					throw new Exception("ServerPort is a required parameter in the configuration file");
				}
				else {
					success = true;
					System.out.println("Reading the config file...");
					System.out.println("Server Hostname - " + serverHostname);
					System.out.println("Server Port - " + serverPort);
					System.out.println("Database Connection String - " + DBConnection);
					System.out.println("Database Username – " + DBUsername);
					System.out.println("Database Password - " + DBPassword);
					System.out.println("Secret Word File - "+ SecretWordFile);
					System.out.print("Trying to connect to server...");
					
				}
			}catch(FileNotFoundException e) {
				System.out.println("Configuration file " + userInputFile +" could not be found.\n");
				success = false;
				
			}
			catch(IOException e) {
				System.out.println("That is not a valid option.\n");
				success = false;
			}
			catch(Exception e) {
				System.out.println(e.getMessage() + "\n");
				success = false;
				
			}
			
	
		}//end of parsing config file
		
		try {
			Socket s = new Socket(serverHostname, Integer.parseInt(serverPort));
			PrintWriter pr = new PrintWriter(s.getOutputStream());
			InputStreamReader in = new InputStreamReader(s.getInputStream());
			BufferedReader bf = new BufferedReader(in);
			
			String serverOutput = bf.readLine();
			if(serverOutput.equals("serverconnected")) {
				System.out.println("Connected");
			}
			else if(serverOutput.equals("notserverconnected")) {
				System.out.println("Unable to connect to server " + serverHostname+ " on port " + serverPort);
			}
			String userInput = "";
			String wins = "";
			String loss = "";
			while(!login) {
				System.out.println("Username: ");
				username = input.nextLine();
				System.out.println("Password: ");
				password = input.nextLine();
				//trying to log in with password <password>.
				pr.println("userInfo");
				pr.println(username);
				pr.println(password);
				pr.flush();
				String exist = bf.readLine();
				if(exist.equals("yesExists")) {
					//successfully logged in.
					System.out.println("Great! You are now logged in as " + username + ".\n" + username + "'s Record\n-----------");
					System.out.println(bf.readLine());
					System.out.println(bf.readLine());
					login = true;
				}
				else if(exist.equals("noExists")) {
					System.out.println("No account exists with those credentials.");
					//does not have an account so not successfully logged in.
					System.out.println("Would you like to create a new account?");
					userInput = input.nextLine();
					if(userInput.equalsIgnoreCase("Yes")) {
						System.out.println("Would you like to use the username and password above?");
						userInput = input.nextLine();
						if(userInput.equalsIgnoreCase("Yes")) {
							//created an account with password <password>.
							
							pr.println("insertUser");
							pr.flush();
							System.out.println("Great! You are now logged in as " + username + ".\n" + username + "'s Record\n-----------");
						//has record <numWins> wins and <numLosses> losses.
							System.out.println("Wins - 0\nLosses - 0");
							login = true;
						}
						else if(userInput.equalsIgnoreCase("No")) {
							System.out.println("Enter a new username and password");
							login = false;	
						}
						else {
							System.out.println("invalid input.");
							login = false;
							
						}
					}
					else if(userInput.equalsIgnoreCase("No")) {
						System.out.println("Enter a new username and password");
						login = false;
					}
					else {
						System.out.println("Invalid input");
						login = false;
					}
				}
			}
			String startJoin= "";
			Boolean correctOption = false;
			Boolean correctChoice = false;
			String nameGame = "";
			String numUsers = "";
			int numUsersInt = 0;
			while(!correctChoice) {
				System.out.println("1) Start a Game");
				System.out.println("2) Join a Game");
				System.out.println("Would you like to start a game or join a game?");
				startJoin = input.nextLine();
				Boolean checking = false;
				while(!checking) {
					if(startJoin.equals("1") || startJoin.equals("2")) {
						checking= true;
						
					}
					else {
						System.out.println("Incorrect Option. Please try again.");
						System.out.println("1) Start a Game");
						System.out.println("2) Join a Game");
						System.out.println("Would you like to start a game or join a game?");
						startJoin = input.nextLine();
						checking = false;
					}
				}
			
				//correctOp = false;
				System.out.println("What is the name of the game?");
				nameGame = input.nextLine();
				if(startJoin.equals("1")) {
					//wants to start a game called <gameName>.
					pr.println("newGame");
					pr.println(nameGame);
					pr.flush();
					String gameExist = bf.readLine();
					while(!correctOption) {
						if(gameExist.equals("gameExist")) {
							//<gameName> already exists, so unable to start <gameName>.
							System.out.println(nameGame+ " already exists. Try again.");
							System.out.println("What is the name of the game?");
							nameGame = input.nextLine();
							pr.println("newGame");
							pr.println(nameGame);
							pr.flush();
							gameExist = bf.readLine();
							correctOption = false;	
						}
						else if(gameExist.equals("gameDNE")) {
							correctOption = true;
							Boolean correctUsers = false;
							while(!correctUsers) {
								System.out.println("How many users will be playing (1-4)?");
								//<gameName> needs <numPlayers> to start game.
								numUsers = input.nextLine();
								if(numUsers.equals("1") || numUsers.equals("2") || numUsers.equals("3") || numUsers.equals("4")) {
									correctUsers = true;
									//correct number of users has enetered now play!
									if(numUsers.equals("1")) {
										System.out.println("All users have joined.");
									}else {
										System.out.println("Waiting for " + numUsers + " other user to join...");
										//user Tommy(other user from different client) is in the game
										//display tommys wins and losses
										System.out.println("All users have joined.");
									}
									correctChoice = true;
								}else {
									correctUsers = false;
									System.out.println("A game can only have between 1-4 players.");
								}
							}
							correctUsers = false;
						}
					}
					correctOption = false;
				}
				else if(startJoin.equals("2")) {
					//wants to join a game called <gameName>.
					pr.println("joinGame");
					pr.println(nameGame);
					pr.flush();
					String gameExist = bf.readLine();
					if(gameExist.equals("gameExist")) {
						
						//check to see if there is enough space in game....
						//if there is no space in game
							//output: The game OneGame does not have space for another user to join.
							//correctChoice is false
						//if there is space
							//join that game
							////user Tommy(other user from different client) is in the game
							//display tommy's records wins/losses
							System.out.println("All users have joined");
							correctChoice = true;
						//correctOption = true;	
					}
					else if(gameExist.equals("gameDNE")) {
						System.out.println("There is no game with name " + nameGame);
						correctChoice = false;
					}
					
				}
			}
			//}//while loop correctoption
			
			//play game
			System.out.println("Determining Secret Word.....");
			int count =7;
			pr.println("pickWord");
			pr.flush();
			//<gameName> has <numPlayers> so starting game. Secret word is <secretWord>.
			//System.out.println("after pickword command to server");
			String chosenWord = bf.readLine();
			String outputWord = "";
			System.out.print("Secret Word ");
			for(int i=0;i<chosenWord.length();i++) {
				System.out.print("_ ");
				outputWord+="_";
			}
			Boolean option = false;
			String inuser = "";
			Boolean winGame = false;
			while(!winGame) {
				int index = 0;
				while(!option) {
					System.out.println("\nYou have "+ count + "incorrect guesses reminaing");
					System.out.println("1) Guess a letter.");
					System.out.println("2) Guess the word.");
					System.out.println("What would you like to do?");
					inuser = input.nextLine();
					if(inuser.equals("1") || inuser.equals("2")) {
						option = true;
						if(inuser.equals("1")) {
							//guessed letter <letter>.
							System.out.print("Letter to guess –");
							inuser = input.nextLine();
							chosenWord = chosenWord.toLowerCase();
							inuser = inuser.toLowerCase();
							if(chosenWord.indexOf(inuser)>=0) {
								System.out.println("The letter '"+inuser+"'is in the secret word.");
								while(chosenWord.indexOf(inuser, index)>=0) {
									index = chosenWord.indexOf(inuser, index);
									outputWord = replaceLett(outputWord, inuser, index);
									index = index +1;
								}
							//<letter> is in <secretWord> in position(s) <positions>. Secret word now shows <secretWordDisplayed>.
								System.out.print("Secret Word: ");
								for(int i =0;i<chosenWord.length();i++) {
									System.out.print(outputWord.charAt(i) + " ");
								}
								System.out.println();
								winGame = false;
									
						
								
								
								
								
								
								
								//while(chosenWord.indexOf(inuser)>=0)
								
								
								
								
							}
							else {
								System.out.println("The letter ‘"+ inuser +"’ is not in the secret word");
								//output it 
								System.out.print("Secret Word: ");
								for(int i =0;i<chosenWord.length();i++) {
									System.out.print(outputWord.charAt(i) + " ");
								}
								System.out.println();
								//<letter> is not in <secretWord>. <gameName> now has <numGuessesRemaining> guesses remaining.
								count--;
								if(count==0) {
									System.out.println("You have 0 incorrect guesses remaining. You Lose!");
									System.out.println("The word was \""+ chosenWord+"\"");
									//update loss in database
									pr.println("updateLoss");
									pr.flush();
									//has record <numWins> wins and <numLosses> losses.
									System.out.println(username + "'s Record\n-----------");
									String holder =bf.readLine();
									String sec = bf.readLine();
									System.out.println(holder);
									System.out.println(sec);
									winGame = true;
									break;
								}
							}
							//if letter in word..display it
								//The letter ‘o’ is in the secret word
							
							//if letter not in word
								//The letter ‘o’ is not in the secret word
								//decrement count
								//if count = 0
									//userlost and break
							
							
						}
						else if(inuser.equals("2")) {
							System.out.println("What is the secret word?");
							inuser = input.nextLine();
							//guessed word <guessedWord>.
							if(inuser.toLowerCase().equals(chosenWord)) {
								pr.println("rightguess");
								pr.println(inuser);
								pr.flush();
								//<guessedWord> is correct. <username> wins game.
								System.out.println("That is correct! You win!");
								winGame = true;
								//update win in database
								pr.println("updateWin");
								pr.flush();
								//has record <numWins> wins and <numLosses> losses.
								System.out.println(username + "'s Record\n-----------");
								String holder =bf.readLine();
								String sec = bf.readLine();
								System.out.println(holder);
								System.out.println(sec);
							}
							else {
								pr.println("wrongguess");
								pr.println(inuser);
								pr.flush();
								//<guessedWord> is incorrect.<username> has lost and is no longer in the game.
								System.out.println("That is incorrect! You lose!");
								System.out.println("The word was \""+ chosenWord+"\". ");
								winGame = true;
								pr.println("updateLoss");
								pr.flush();
								//has record <numWins> wins and <numLosses> losses.
								System.out.println(username + "'s Record\n-----------");
								String holder =bf.readLine();
								String sec = bf.readLine();
								System.out.println(holder);
								System.out.println(sec);
								
								
							}
							
						}
						
						
					}else {
						option = false;
						System.out.println("That is not a valid option.");
					}		
				}//while option loop
				option = false;
			}//while loop wingame
			//display stats win/losses
//			pr.println("getRecords");
//			pr.flush();
//			System.out.println(username + "'s Record\n-----------");
//			System.out.println(bf.readLine());
//			System.out.println(bf.readLine());
			System.out.println("Thank you for playing Hangman!");
			
			
			
			
			
			
			
			
		}catch(NumberFormatException e) {
			e.printStackTrace();
			
		} catch (UnknownHostException e) {
			//e.printStackTrace();
			System.out.println("Trying to connect to server...Unable to connect to server " + serverHostname + " on port " + serverPort);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		
	
		
	}

}
