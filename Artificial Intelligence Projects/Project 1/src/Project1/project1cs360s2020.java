package Project1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.File;

class Board {
	public Integer totDrone;
	public Integer totalPacks;
	public Map <String,Integer> packLocation;
	public Integer maxDrone;
	public Set <String> okayPlaces; 
	public int size;
	
	Board(int numDrones, int sumPack, Map <String,Integer> packages, Set <String> goodplace, int maxD, int boardSize) {
		totDrone = numDrones;
		totalPacks = sumPack;
		packLocation = packages;
		okayPlaces = goodplace;
		maxDrone = maxD;
		size = boardSize;
		
	}
}
class ASTBoard{
	public Integer totDrone;
	public Integer totalPacks;
	public Map <String,Integer> packLocation;
	public Integer maxDrone;
	public Set <String> okayPlaces; 
	public int size;
	public int hval;
	public int gval;
	public int fval;
	public String parentCord;
	public String currCord;
	public int packageNumber;
	ASTBoard(Map <String,Integer> packages, Set <String> goodplace, int h, int g, int sumPacks, int numDrones, int maxD, int boardSize, int fscore, String coordP, String coordC){
		totDrone = numDrones;
		totalPacks = sumPacks;
		packLocation = packages;
		okayPlaces = goodplace;
		maxDrone = maxD;
		size = boardSize;
		hval = h;
		gval = g;
		fval = fscore;
		parentCord = coordP;
		currCord = coordC;
		if(packLocation.containsKey(coordC)) {
			packageNumber = packLocation.get(coordC);
		}
		else {
			packageNumber = 0;
		}
		
	}	
}
class MaxNumComparator implements Comparator<ASTBoard>{ 

    public int compare(ASTBoard a1, ASTBoard a2) { 
        if (a1.fval < a2.fval) 
            return 1; 
        else if (a1.fval > a2.fval) 
            return -1; 
        else
        	return 0;
                       
        }   
} 

public class project1cs360s2020 {
	static Integer maxNum = 0;
	static Integer maxPack = 0;
	static Integer maxSinglePack = 0;
	static Integer maxMultiplePack = 0;
	static Integer checkingSum;
	static Integer newCheckSum;
	
	public static int DFS(Board board) {
		Integer num = 0;
		int sum = 0;
		Set <String> greatPlaces;
		Set <String> diag = new HashSet<String>();
		if(board.maxDrone==1) {
			return maxPack;
		}
		for(int i =0; i<board.size; i++) {
			for(int j =0; j<board.size;j++) {
				greatPlaces = new HashSet<String>();
				String word = i+","+j;
				if(board.packLocation.containsKey(word)) {
					sum+=board.packLocation.get(word);
				}
				diag = diag(i,j,board.size);
				for(int u = 0; u<board.size;u++) {
					for(int t = 0; t<board.size;t++) {
						if(!(i==u)) {
							if(!(j==t)) {
								String newword = u+","+t;
								if(!diag.contains(newword)) {
									greatPlaces.add(newword);
								}
							}
						}
						
					}
				}
				Board newboard = new Board(1,sum, board.packLocation, greatPlaces,board.maxDrone, board.size);
				DFSHelper(newboard);
				sum = 0;
			}
		}
		return maxNum;
		
		
	}
	
	public static int Astar(ASTBoard board) {
		Set <ASTBoard> exploredBoards = new HashSet<ASTBoard>();
		int max =0;
		Set <String> newGoodPlace= new HashSet<String>();
		PriorityQueue<ASTBoard> frontierQueue = new PriorityQueue<ASTBoard>(board.size*board.size, new MaxNumComparator());
		frontierQueue.add(board);
		boolean found = false;
		while(!frontierQueue.isEmpty()) {
			ASTBoard newboard = frontierQueue.poll();
			exploredBoards.add(newboard);
			if(newboard.totDrone==newboard.maxDrone) {
				found=true;
				if(max<newboard.fval) {
					max = newboard.fval;
				}
			}

			for(String coords: newboard.okayPlaces) {
				int hval = 0;
				int totalDrone = newboard.totDrone;
				totalDrone++;
				if(totalDrone!=newboard.maxDrone) {
					newGoodPlace = updateOkayPlaces(newboard.okayPlaces, coords, newboard.size, newboard.totalPacks, newboard.packLocation, newCheckSum);
					hval = hvalFunc(newGoodPlace, newboard.packLocation);
					
				}
				int tempGval =0;
				int tempFval = 0;
				if(newboard.packLocation.containsKey(coords)) {
					tempGval = newboard.gval + newboard.packLocation.get(coords);
				}
				else {
					tempGval = newboard.gval;
				}
				
				tempFval = hval+tempGval;
				ASTBoard child = new ASTBoard(newboard.packLocation, newGoodPlace, hval, tempGval,newboard.totalPacks, totalDrone, newboard.maxDrone, newboard.size, hval+tempGval,newboard.currCord, coords);
				if((exploredBoards.contains(child))&&(tempFval<=child.fval)) {
					continue;
				}
				else if((!frontierQueue.contains(child)) || (tempFval>child.fval)  ) {
					child.gval = tempGval;
					child.fval = tempFval;
					if(frontierQueue.contains(child)) {
						frontierQueue.remove(child);
					}
					frontierQueue.add(child);
				}
			}
		}
			
		
		return max;
		
	}
	public static int hvalFunc(Set<String>goodSet, Map<String, Integer> mapPack) {
		int sum = 0;
		for( String coord: goodSet) {
			if(mapPack.containsKey(coord)) {
				sum+=mapPack.get(coord);
			}
			else {
				sum+=0;
			}
		}
		return sum;
		
	}
	public static void DFSHelper(Board board) {
		Set <String> newGoodPlaces = new HashSet<String>();
		int hold;
		int sum = board.totalPacks;
		if(board.totDrone==board.maxDrone) {
			if(sum > maxNum)
				maxNum = sum;
		}
		else {
			for(String coord: board.okayPlaces) {
				hold = board.totDrone;
				hold++;
				if(hold!=board.maxDrone) {
					newGoodPlaces = updateOkayPlaces(board.okayPlaces, coord, board.size, board.totalPacks, board.packLocation, newCheckSum);
				}
				int v = 0;
				if(board.packLocation.containsKey(coord)) {
					sum+=board.packLocation.get(coord);
					v = board.packLocation.get(coord);
				}
				Board newBoard = new Board(hold, sum, board.packLocation, newGoodPlaces, board.maxDrone, board.size);
				DFSHelper(newBoard);
				sum -= v;
			}	
		}
	}
	public static Set<String> updateOkayPlaces(Set<String> holder, String dronePlace, int size, int maxPacks, Map<String, Integer> listPacks, int prevCheckingSum){
		Set <String> newOk = new HashSet<String>();
		Set <String> diag = new HashSet<String>();
		for(String words: holder) {
			newOk.add(words);
		}
		int a = Character.getNumericValue(dronePlace.charAt(0));
		int b = Character.getNumericValue(dronePlace.charAt(2));
		diag = diag(a,b,size);
		
		for(String word: holder) {
			//if row the same  same xvals 
			if(word.charAt(0)==dronePlace.charAt(0)) {
				newOk.remove(word);
			}
			//if col same 
			if(word.charAt(2)==dronePlace.charAt(2)) {
				newOk.remove(word);
				
			}
			if(diag.contains(word)) {
				newOk.remove(word);
			}
		}
		//newcheckingsum
		return newOk;
	}
	public static Set<String> diag(int i, int j, int size){
		Set <String> diagnals = new HashSet<String>();
		int r;
		int c;
		String word = "";
		for(r = i+1, c = j+1; r<size && r<size; r++, c++) {
			word = r+","+c;
			diagnals.add(word);
			word = "";
		}
		for(r = i-1, c = j-1; r>=0 && c>=0; r--, c--) {
			word = r+","+c;
			diagnals.add(word);
			word = "";
		}
		for(r = i-1, c = j+1; r>=0 && c<size; r--, c++) {
			word = r+","+c;
			diagnals.add(word);
			word = "";
		}
		for(r = i+1, c = j-1; r<size && c>=0; r++, c--) {
			word = r+","+c;
			diagnals.add(word);
			word = "";
		}
		return diagnals;
	}
	
	public static void main(String[] args) {
		Integer boardSize = 0;
		Integer dronesNum = 0;
		Integer packagesNum = 0;
		String algo= "";
		String coord = "";
		Integer singlePackNum = 0;
		int sum = 0;
		
		
		Map <String,Integer> locationPackages = new HashMap<String, Integer>();
		Set <String> correctDroneLocations = new HashSet<String>();
		Set <String> correctDroneLocationsAST = new HashSet<String>();
		
		
		try {
			Scanner sc = new Scanner(new File("input.txt"));
			FileWriter writer = new FileWriter("output.txt");
			boardSize = sc.nextInt();
			dronesNum = sc.nextInt();
			packagesNum = sc.nextInt();
			algo = sc.nextLine();
			algo = sc.nextLine();
			while(sc.hasNextLine()) {
				//get string from file
				coord = sc.nextLine();
				if(locationPackages.containsKey(coord)) {
					singlePackNum = locationPackages.get(coord);
					singlePackNum++;
					maxMultiplePack = singlePackNum;
					if(maxPack<maxMultiplePack) {
						maxPack = maxMultiplePack;
					}
					locationPackages.put(coord,singlePackNum);	
				}
				else {
					locationPackages.put(coord,1);
					maxSinglePack = 1;
					if(maxPack<maxSinglePack) {
						maxPack = maxSinglePack;
					}
				}
			}
			
			for(int i =0; i<boardSize; i++) {
				for(int j =0; j<boardSize;j++) {
					String word = i+","+j;
					correctDroneLocationsAST.add(word);
				}
			}
			Board gameBoard = new Board(0, packagesNum, locationPackages, correctDroneLocations, dronesNum, boardSize);
			ASTBoard AstGameBoard = new ASTBoard(locationPackages, correctDroneLocationsAST, packagesNum, 0, packagesNum, 0, dronesNum, boardSize, packagesNum, "","");
			if(algo.equalsIgnoreCase("astar")) {
				newCheckSum = packagesNum;
				
				int num = Astar(AstGameBoard);
				writer.write(Integer.toString(num));
				writer.flush();	
			}
			else if(algo.equalsIgnoreCase("dfs")) {
				newCheckSum = packagesNum;
				int num = DFS(gameBoard);
				writer.write(Integer.toString(num));
				writer.flush();
			}
 	    	
			
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found");
		}
		catch(IOException e) {
			System.out.println("Can't Write to Output FIle");
		}
		
	}
	

}
