import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class project3cs360s2020 {
	static Map <String, ArrayList<String>> actions;
	static Map <String, Double> values = new HashMap<String, Double>();
	static double gamma = 0.9;
	static Map <String,Integer> rewards = new HashMap<String, Integer>();
	
	public static void main(String [] args) {
		int gridSize;
		int numObst;
		Scanner sc = null;
		FileWriter fw = null;
		String holder;
		String destPt;
		String intString; 
		String check;
		ArrayList<String> coords = new ArrayList<String>();
		ArrayList seeing = new ArrayList();
		actions = new HashMap<String, ArrayList<String>>();
		ArrayList <String> movesList = new ArrayList<String>();
		String [][]grid;
		String bestAct = "";
		ArrayList hold = new ArrayList();
		String [] mainActions = new String[4];
		mainActions[0] = ">";
		mainActions[1] = "<";
		mainActions[2] = "v";
		mainActions[3] = "^";
		double delta;
		double oldVal;
		double newVal = 0.0;
		double epsilon = 0.01;
		int count;
		
		try {
			sc = new Scanner (new File("input.txt"));
			fw = new FileWriter("output.txt");
			gridSize = sc.nextInt();
			numObst = sc.nextInt();
			count = numObst;
			while(count>0) {
				holder = sc.nextLine();
				if(!holder.equals("")) {
					coords.add(holder);
					rewards.put(holder, -101);
					count--;
				}
			}
			destPt = sc.nextLine();
			rewards.put(destPt, 99);
			
			for(int i =0; i<gridSize;i++) {//col
				for(int j = 0; j<gridSize; j++) { //row
					movesList = new ArrayList<String>();
					intString = "";
					intString = Integer.toString(i);
					intString+=",";
					intString+=Integer.toString(j);
					values.put(intString, 0.0);
					//if this point is not in obstacle list or destpt then add to rewards list  
					if(!rewards.containsKey(intString)) {
						rewards.put(intString, -1);
					}
					if(i+1<gridSize) {
						
						movesList.add(">");
					}
					if(i-1>=0) {
						movesList.add("<");
					}
					if(j+1<gridSize) {
						movesList.add("v");
					}
					if(j-1>=0) {
						movesList.add("^");
					}
					actions.put(intString, movesList);
				}
			}
			while(true) {
				
				delta = 0;
				for(int i = 0; i<gridSize; i++) {//col
					for(int j=0; j<gridSize; j++) {//row
						intString = "";
						intString = Integer.toString(i);
						intString+=",";
						intString+=Integer.toString(j);
						oldVal = values.get(intString);
						if(intString.equals(destPt)) {
							continue;
						}
						else {
							//best action value
							seeing = bestActVal(intString);
							String hold1 = seeing.get(0).toString();
							newVal = Double.parseDouble(hold1);
							values.put(intString, newVal);
							delta = Math.max(delta, Math.abs(oldVal-newVal));	
						}
						
						
						
					}
				}
				if(delta<epsilon) {
					break;
				}
			}
			
			//calculate policy
			grid = new String[gridSize][gridSize];
			
			for(int i = 0; i<gridSize; i++) {//col
				for(int j=0; j<gridSize; j++) {//row
					intString = "";
					intString = Integer.toString(i);
					intString+=",";
					intString+=Integer.toString(j);
					oldVal = values.get(intString);
					if(intString.equals(destPt)) {
						grid[j][i] = ".";
						continue;
					}
					else {
						
						seeing = bestActVal(intString);
						bestAct = seeing.get(1).toString();
						if(coords.contains(intString)) {
							grid[j][i] = "o";	
						}
						else {
							grid[j][i] = bestAct;
						}
					
					}
				}
			}
			//writw to file
			String lineHolder="";
			for(int i=0;i<gridSize;i++) {
				for(int j =0; j<gridSize;j++) {
					lineHolder+=grid[i][j];
				}
				fw.write(lineHolder+"\n");
				lineHolder="";
			}
			
			fw.close();
			sc.close();
			
		}
		catch(FileNotFoundException e) {
			System.out.println("File is not found");
			
		}catch(IOException e) {
			System.out.println("Not writing to file");
		}
		
	}
	public static ArrayList bestActVal(String coord) {
		String [] mainActions = new String[4];
		String bestAct = "";
		double bestVal = Double.NEGATIVE_INFINITY;
		ArrayList<ArrayList> transitions = new ArrayList<ArrayList>();
		ArrayList returnVal = new ArrayList();
		double exVal;
		double exR;
		mainActions[0] = ">";
		mainActions[1] = "<";
		mainActions[2] = "v";
		mainActions[3] = "^";
		double val;
		for(String act: mainActions) {

			transitions = trans(act, coord);
			exVal = 0.0;
			exR = 0.0;
			for(int i = 0; i <transitions.size();i++) {
				String word = (transitions.get(i).get(0)).toString();
				String re = (transitions.get(i).get(1)).toString();
				exR+=Double.parseDouble(word)* Integer.parseInt(re);
				exVal+= Double.parseDouble(word) * values.get((transitions.get(i).get(2)).toString());
			}
			val = exR + gamma * exVal;
			if(val>bestVal) {
				bestVal = val;
				bestAct = act;
			}
		}
		returnVal.add(bestVal);
		returnVal.add(bestAct);
		
		return returnVal;	
	}
	public static ArrayList<ArrayList> trans(String action, String coord) {
		ArrayList holder = new ArrayList();
		ArrayList holder1 = new ArrayList();
		ArrayList holder2 = new ArrayList();
		ArrayList holder3 = new ArrayList();
		ArrayList holder4 = new ArrayList();
		String [] coord1 = coord.split(",");
		ArrayList <ArrayList> transOutput = new ArrayList <ArrayList>();
		String intString;
		int col;
		int row;
		int reward;
		col = Integer.parseInt(coord1[0]); 
		row = Integer.parseInt(coord1[1]);
		if(actions.get(coord).contains(action)) {
			
			if(action.equals(">")) {
				col+=1;
				
			}else if(action.equals("<")) {
				col-=1;
				
			}else if(action.equals("v")) {
				row+=1;
				
			}else if(action.equals("^")) {
				row-=1;
			}
			
		}
		intString = Integer.toString(col);
		intString+=",";
		intString+=Integer.toString(row);
		reward = rewards.get(intString);
		//find probabs 70,10,10,10
		holder.add(0.7);
		holder.add(reward);
		holder.add(intString);
		transOutput.add(holder);
		intString = "";
		col = Integer.parseInt(coord1[0]); 
		row = Integer.parseInt(coord1[1]);
		
		if(action.equals(">")) {
			if(actions.get(coord).contains("^")) {
				row-=1;
			}
			intString = Integer.toString(col);
			intString+=",";
			intString+=Integer.toString(row);
			reward = rewards.get(intString);
			holder1.add(0.1);
			holder1.add(reward);
			holder1.add(intString);
			transOutput.add(holder1);
			intString = "";
			row = Integer.parseInt(coord1[1]);
			if(actions.get(coord).contains("v")) {
				row+=1;
			}
			intString = Integer.toString(col);
			intString+=",";
			intString+=Integer.toString(row);
			reward = rewards.get(intString);
			holder2.add(0.1);
			holder2.add(reward);
			holder2.add(intString);
			transOutput.add(holder2);

			intString = "";
 
			row = Integer.parseInt(coord1[1]);
			if(actions.get(coord).contains("<")) {
				col-=1;
			}
			intString = Integer.toString(col);
			intString+=",";
			intString+=Integer.toString(row);
			reward = rewards.get(intString);
			holder3.add(0.1);
			holder3.add(reward);
			holder3.add(intString);
			transOutput.add(holder3);

			intString = "";
			col = Integer.parseInt(coord1[0]); 

		}else if(action.equals("<")) {
			if(actions.get(coord).contains("v")) {
				row+=1;
			}
			intString = Integer.toString(col);
			intString+=",";
			intString+=Integer.toString(row);
			reward = rewards.get(intString);
			holder1.add(0.1);
			holder1.add(reward);
			holder1.add(intString);
			transOutput.add(holder1);
			intString = "";
			row = Integer.parseInt(coord1[1]);
			
			if(actions.get(coord).contains("^")) {
				row-=1;
			}
			intString = Integer.toString(col);
			intString+=",";
			intString+=Integer.toString(row);
			reward = rewards.get(intString);
			holder2.add(0.1);
			holder2.add(reward);
			holder2.add(intString);
			transOutput.add(holder2);

			intString = "";
			row = Integer.parseInt(coord1[1]);
			if(actions.get(coord).contains(">")) {
				col+=1;
			}
			intString = Integer.toString(col);
			intString+=",";
			intString+=Integer.toString(row);
			reward = rewards.get(intString);
			holder3.add(0.1);
			holder3.add(reward);
			holder3.add(intString);
			transOutput.add(holder3);
			intString = "";
			col = Integer.parseInt(coord1[0]); 
			
		}else if(action.equals("v")) {
			if(actions.get(coord).contains("^")) {
				row-=1;
			}
			intString = Integer.toString(col);
			intString+=",";
			intString+=Integer.toString(row);
			reward = rewards.get(intString);
			holder1.add(0.1);
			holder1.add(reward);
			holder1.add(intString);
			transOutput.add(holder1);
			intString = "";
			row = Integer.parseInt(coord1[1]);
			if(actions.get(coord).contains(">")) {
				col+=1;
			}
			intString = Integer.toString(col);
			intString+=",";
			intString+=Integer.toString(row);
			reward = rewards.get(intString);
			holder2.add(0.1);
			holder2.add(reward);
			holder2.add(intString);
			transOutput.add(holder2);
			intString = "";
			col = Integer.parseInt(coord1[0]); 
			if(actions.get(coord).contains("<")) {
				col-=1;
			}
			intString = Integer.toString(col);
			intString+=",";
			intString+=Integer.toString(row);
			reward = rewards.get(intString);
			holder3.add(0.1);
			holder3.add(reward);
			holder3.add(intString);
			transOutput.add(holder3);
			intString = "";
			col = Integer.parseInt(coord1[0]); 
			
			
		}else if(action.equals("^")) {
			if(actions.get(coord).contains(">")) {
				col+=1;
			}
			intString = Integer.toString(col);
			intString+=",";
			intString+=Integer.toString(row);
			reward = rewards.get(intString);
			holder1.add(0.1);
			holder1.add(reward);
			holder1.add(intString);
			transOutput.add(holder1);

			intString = "";
			col = Integer.parseInt(coord1[0]); 
			if(actions.get(coord).contains("<")) {
				col-=1;
			}
			intString = Integer.toString(col);
			intString+=",";
			intString+=Integer.toString(row);
			reward = rewards.get(intString);
			holder2.add(0.1);
			holder2.add(reward);
			holder2.add(intString);
			transOutput.add(holder2);
			intString = "";
			col = Integer.parseInt(coord1[0]); 
			if(actions.get(coord).contains("v")) {
				row+=1;
			}
			intString = Integer.toString(col);
			intString+=",";
			intString+=Integer.toString(row);
			reward = rewards.get(intString);
			holder3.add(0.1);
			holder3.add(reward);
			holder3.add(intString);
			transOutput.add(holder3);
			intString = "";
			row = Integer.parseInt(coord1[1]);
		}
		return transOutput;
	}
	

}
