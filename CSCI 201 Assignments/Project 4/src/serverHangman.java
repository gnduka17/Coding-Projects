import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.Random;
import java.util.Scanner;

public class serverHangman {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		boolean success = false;
		String userInputFile ="";
		String fileLine = "";
		String serverPort ="";
		String DBConnection = "";
		String DBUsername = "";
		String DBPassword = "";
		String SecretWordFile = "";
		String serverHostname = "";
		String username = "";
		String password = "";
		InputStreamReader in = null;
		BufferedReader bf = null;
		PrintWriter pr = null;
		boolean userExist = false;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Properties prop = new Properties();
		
		while(!success) {
      
			try {
				System.out.println("What is the name of the configuration file?");
				userInputFile = input.nextLine();
				InputStream into = new FileInputStream(userInputFile);
				prop.load(into);
				serverHostname = prop.getProperty("ServerHostname", "NULL");
				serverPort = prop.getProperty("ServerPort", "NULL");
				DBConnection = prop.getProperty("DBConnection", "NULL");
				DBUsername = prop.getProperty("DBUsername", "NULL");
				DBPassword = prop.getProperty("DBPassword", "NULL");
				SecretWordFile = prop.getProperty("SecretWordFile", "NULL");

				if(serverPort.equals("NULL")) {
					throw new Exception("ServerPort is a required parameter in the configuration file");
				}else if(serverHostname.equals("NULL")){
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
				}else {
					success = true;
					System.out.println("server running...");
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
		}
		
			try {
				ServerSocket ss = new ServerSocket(Integer.parseInt(serverPort));
				Socket s = ss.accept();
				in = new InputStreamReader(s.getInputStream());
				bf = new BufferedReader(in);
				pr = new PrintWriter(s.getOutputStream());
				pr.println("serverconnected");
				pr.flush();
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection(DBConnection, DBUsername, DBPassword);
				System.out.println("Trying to connect to database...Connected!");
				
				String tempPass = "";
				String samePass = "";
				Boolean noExist = false;
				String gameName = "";
				
				for(String line = bf.readLine(); line!=null; line=bf.readLine()) {
					if(line.equals("userInfo")) {
						username = bf.readLine();
						password = bf.readLine();
						System.out.println(Util.getCurrentTime() + " <"+username+"> - trying to log in with password <"+ password+">.");
						ps=conn.prepareStatement("SELECT * FROM User WHERE username=? AND password=?");
						ps.setString(1, username);
						ps.setString(2,password);
						rs = ps.executeQuery();
						if(rs.next()) {
							userExist = true;
							//keep track of LOGGED IN
							pr.println("yesExists");
							System.out.println(Util.getCurrentTime() + " <"+username+"> - successfully logged in.");
							pr.println("Wins - " + rs.getInt("wins"));
							pr.println("Losses - " + rs.getInt("losses"));
							
						}else {
							noExist = true;
							pr.println("noExists");
							System.out.println(Util.getCurrentTime() + " <"+username+"> - does not have an account so not successfully logged in.");

							
						}
						pr.flush();
					}
					else if(line.equals("insertUser")) {
						System.out.println(Util.getCurrentTime() + " <"+username+"> - created an account with password <"+ password+">");

						ps=conn.prepareStatement("INSERT INTO User(username,password,wins,losses) VALUES (?,?,?,?)");
						ps.setString(1, username);
						ps.setString(2,password);
						ps.setInt(3, 0);
						ps.setInt(4, 0);
						ps.executeUpdate();
						ps.close();
						System.out.println(Util.getCurrentTime() + " <"+username+"> - has record 0 wins and 0 losses.");

					}
					else if(line.equals("newGame")) {
						gameName = bf.readLine();
						System.out.println(Util.getCurrentTime() + " <"+username+"> - wants to start a game called " + gameName);
						ps=conn.prepareStatement("SELECT * FROM Game WHERE gameName=?");
						ps.setString(1, gameName);
						rs = ps.executeQuery();
						if(rs.next()) {
							//game exists
							
							pr.println("gameExist");
							System.out.println(Util.getCurrentTime() + " <"+username+"> - "+gameName+" already exists, so unable to start " + gameName);

							
						}
						else {
							//game DNE
							//successfully started game <gameName>.
							System.out.println(Util.getCurrentTime() + " <"+username+"> - successfully started game " + gameName);
							pr.println("gameDNE");
							ps=conn.prepareStatement("INSERT INTO Game(username,gameName) VALUES (?,?)");
							ps.setString(1, username);
							ps.setString(2, gameName);
							ps.executeUpdate();
							ps.close();
						}
						pr.flush();
						
						
					}
					else if(line.equals("joinGame")) {
						gameName = bf.readLine();
						System.out.println(Util.getCurrentTime() + " <"+username+"> - wants to join a game called " + gameName);
						ps=conn.prepareStatement("SELECT * FROM Game WHERE gameName=?");
						ps.setString(1, gameName);
						rs = ps.executeQuery();
						if(rs.next()) {
							//game exists
							pr.println("gameExist");
							System.out.println(Util.getCurrentTime() + " <"+username+"> - successfully joined game " + gameName);
							ps=conn.prepareStatement("INSERT INTO Game(username,gameName) VALUES (?,?)");
							ps.setString(1, username);
							ps.setString(2, gameName);
							ps.executeUpdate();
							ps.close();
						}
						else {
							//game DNE
							pr.println("gameDNE");
							System.out.println(Util.getCurrentTime() + " <"+username+"> - unsuccessfully joined game " + gameName + " because game does not exist");

						}
						pr.flush();
					}
					else if(line.equals("pickWord")) {
						try {
						ArrayList<String> words = new ArrayList<String>();
						BufferedReader reader = new BufferedReader(new FileReader(SecretWordFile));
						int numOfWords = 0;
						while((fileLine=reader.readLine())!=null) {
							numOfWords++;
							words.add(fileLine.toLowerCase());
						}
						Random rand = new Random();
						int n = rand.nextInt(numOfWords);
						pr.println(words.get(n));
						reader.close();	
						pr.flush();
						}catch(FileNotFoundException e) {
							e.printStackTrace();
							
						}
					}
					else if(line.equals("updateLoss")) {
						System.out.println(Util.getCurrentTime() + " <"+username+"> - has loss the game " + gameName);

						int loss = 0;
						ps=conn.prepareStatement("SELECT * FROM User WHERE username=? AND password=?");
						ps.setString(1, username);
						ps.setString(2,password);
						rs = ps.executeQuery();
						if(rs.next()) {
							loss = rs.getInt("losses");
						}
						loss+=1;
						ps = conn.prepareStatement("Update User SET losses=? WHERE username=?");
						ps.setInt(1, loss);
						ps.setString(2,username);
						int r = ps.executeUpdate();
						ps.close();
						
						ps=conn.prepareStatement("SELECT * FROM User WHERE username=? AND password=?");
						ps.setString(1, username);
						ps.setString(2,password);
						rs = ps.executeQuery();
						int newloss = 0;
						int newwin = 0;
						if(rs.next()) {
							newwin = rs.getInt("wins");
							newloss = rs.getInt("losses");
						}
						pr.println("Wins - " + newwin);
						pr.println("Losses - " + newloss);
						pr.flush();
						System.out.println(Util.getCurrentTime() + " <"+username+"> - has record "+ newwin+ " wins and "+ newloss+" losses.");

						ps=conn.prepareStatement("DELETE FROM Game WHERE gameName=?");
						ps.setString(1, gameName);
						ps.executeUpdate();
						ps.close();
						
						
						
//						
//						String query1 = "Update User SET losses="+ Integer.toString(loss)+" WHERE username="+username;
//						System.out.println("output string:" + query1);
//						st = conn.createStatement();
//						st.executeUpdate(query1);
					}
					else if(line.equals("updateWin")) {
						System.out.println(Util.getCurrentTime() + " <"+username+"> - has won the game " + gameName);

						int wins = 0;
						ps=conn.prepareStatement("SELECT * FROM User WHERE username=?");
						ps.setString(1, username);
						rs = ps.executeQuery();
						if(rs.next()) {
							wins = rs.getInt("wins");
						}
						wins+=1;
						ps = conn.prepareStatement("Update User SET wins=? WHERE username=?");
						ps.setInt(1, wins);
						ps.setString(2,username);
						int r = ps.executeUpdate();
						ps.close();
						
						ps=conn.prepareStatement("SELECT * FROM User WHERE username=?");
						ps.setString(1, username);
						rs = ps.executeQuery();
						int newloss = 0;
						int newwin = 0;
						if(rs.next()) {
							newwin = rs.getInt("wins");
							newloss = rs.getInt("losses");
						}
						pr.println("Wins - " + newwin);
						pr.println("Losses - " + newloss);
						pr.flush();
						System.out.println(Util.getCurrentTime() + " <"+username+"> - has record "+ newwin+ " wins and "+ newloss+" losses.");

						
						//delete from game 
						ps=conn.prepareStatement("DELETE FROM Game WHERE gameName=?");
						ps.setString(1, gameName);
						ps.executeUpdate();
						ps.close();
						
						
						
						
//						String query1 = "Update User SET wins="+ Integer.toString(wins)+" WHERE username="+username;
//						st = conn.createStatement();
//						st.executeUpdate(query1);
						
					}
//					else if(line.equals("getRecords")) {
//						ps=conn.prepareStatement("SELECT * FROM User WHERE username=? AND password=?");
//						ps.setString(1, username);
//						ps.setString(2,password);
//						rs = ps.executeQuery();
//						if(rs.next()) {
//							System.out.println("in if statememt within the getRecords");
//							pr.println("Wins - " + rs.getInt("wins"));
//							pr.println("Losses - " + rs.getInt("losses"));
//						}
//						System.out.println("end of getRecords");
//					}
					else if(line.equals("wrongguess")) {
						String word = bf.readLine();
						//<guessedWord> is incorrect.<username> has lost and is no longer in the game.
						System.out.println(Util.getCurrentTime() + " <"+username+"> - "+word+" is incorrect. "+username+" has lost and is no longer in the game.");

						
					}
					else if(line.equals("rightguess")) {
						String word = bf.readLine();
						//<guessedWord> is incorrect.<username> has lost and is no longer in the game.
						System.out.println(Util.getCurrentTime() + " <"+username+"> - "+word+" is correct. "+username+" wins game.");

						
					}
				}

			}
			catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				//pr.println("not connected to server");
				pr.println("notserverconnected");
				pr.flush();
			}catch (ClassNotFoundException e) {
				//e.printStackTrace();
				System.out.println("Trying to connect to database...Unable to connect to database " + DBConnection + " with username " + DBUsername + " and password " + DBPassword);
				e.printStackTrace();
			} catch (SQLException e) {
				//e.printStackTrace();
				System.out.println("Trying to connect to database...Unable to connect to database " + DBConnection + " with username " + DBUsername + " and password " + DBPassword);
				e.printStackTrace();
			}
			
			
				
			
		
		
		input.close();
	}//end of main
	

}
