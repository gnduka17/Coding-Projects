import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.io.IOException;
public class project2cs360s2020 {
	
	public static void main (String[] args) {
		Integer numLines = 0;
		String algo = "";
		String stringHolder="";
		Double sumA = 0.0;
		Double sumB = 0.0;
		int count = 0;
		int depth = 0;
		ArrayList<Integer> untouched = new ArrayList<Integer>();
		ArrayList<Integer> touchA = new ArrayList<Integer>();
		ArrayList<Integer> touchB = new ArrayList<Integer>();
		Map<Integer, Double[]>avail = new HashMap<Integer,Double[]>();
		try {
			Scanner sc = new Scanner(new File("input.txt"));
			FileWriter fw = new FileWriter("output.txt");
			numLines = sc.nextInt();
			algo = sc.nextLine();
			algo = sc.nextLine();
			while(sc.hasNextLine()) {
				stringHolder=sc.nextLine();
				String[] holder = stringHolder.split(",");
				if(holder[4].equals("0")) {
					untouched.add(Integer.parseInt(holder[0]));
					Double [] list= new Double[2];
					list[0]= (Double.parseDouble(holder[1])*Double.parseDouble(holder[2]));
					list[1]= (Double.parseDouble(holder[1])*Double.parseDouble(holder[3]));
					avail.put(Integer.parseInt(holder[0]), list);
						
				}
				else if(holder[4].equals("1")) {
					count++;
					touchA.add(Integer.parseInt(holder[0]));
					sumA+=(Double.parseDouble(holder[1])*Double.parseDouble(holder[2]));
				}
				else {
					count++;
					touchB.add(Integer.parseInt(holder[0]));
					sumB+=(Double.parseDouble(holder[1])*Double.parseDouble(holder[3]));
					
				}
			}
			Collections.sort(untouched);
			System.out.println(untouched);
			System.out.println("sumA is: "+ sumA);
			System.out.println("sumB is: "+ sumB);
			
			depth = (5*2)-count;
//			depth = depth+1;
			System.out.println("depth:" + depth);
			if(algo.equalsIgnoreCase("minimax")) {
				Double [] val = new Double [2];
				Integer id;
				val = minimax(untouched,avail, touchA, touchB,sumA, sumB,depth,depth,true);
				
				double hold1 = val[1];
				int hold = (int)hold1;
				System.out.println("the Minimax advantage value: " + val[0]);
				System.out.println(hold);
				fw.write(Integer.toString(hold));
				fw.flush();
			}
			else if(algo.equalsIgnoreCase("ab")) {
				Double [] alpha = new Double[2];
				Double [] beta = new Double[2];
				alpha[0] = -100000000000000000.0;
				beta[0] = 1000000000000000000.0;
				
				Double [] val = new Double [2];
				Integer id;
				System.out.println("Running AB");
				val = ab(alpha, beta, untouched,avail,touchA, touchB,sumA, sumB,depth,depth, true);
				double hold1 = val[1];
				int hold = (int)hold1;
				System.out.println("the AB advantage value: " + val[0]);
				System.out.println(hold);
				fw.write(Integer.toString(hold));
				fw.flush();
				
				
			}
		}
		catch(FileNotFoundException e) {
			System.out.println("FILE IS NOT FOUND");
		}
		catch(IOException e) {
			System.out.println("not writing to file");
		}
	}
	
	public static Double [] minimax(ArrayList<Integer> untouch, Map<Integer, Double[]> rest, ArrayList<Integer> touchedA, ArrayList<Integer> touchedB, Double suma, Double sumb, int d, int di, boolean maxPlay) {
		if(d==0) {
			Set <Integer> holder = new HashSet<Integer>();
			holder.add(touchedA.get(0)%10);
			holder.add(touchedA.get(1)%10);
			holder.add(touchedA.get(2)%10);
			holder.add(touchedA.get(3)%10);
			holder.add(touchedA.get(4)%10);
			if(holder.size()==5) {
				suma+=120;
			}
			Set <Integer> holderB = new HashSet<Integer>();
			holderB.add(touchedB.get(0)%10);
			holderB.add(touchedB.get(1)%10);
			holderB.add(touchedB.get(2)%10);
			holderB.add(touchedB.get(3)%10);
			holderB.add(touchedB.get(4)%10);
			if(holderB.size()==5) {
				sumb+=120;
			}
			Double [] hold = new Double [2];
			hold[0] = suma - sumb;
			hold[1] = (double)touchedA.get((10-di)/2);
			return hold;
		}
		
		if(maxPlay) {
			Double [] maxEval = new Double[2];
			maxEval[0] = Double.NEGATIVE_INFINITY;
			Double[] newhold = new Double [2];
			ArrayList<Integer> untouchCopy = new ArrayList<Integer>(untouch);
			for(Integer i:untouch) {
				touchedA.add(i);
				suma+=rest.get(i)[0];
				untouchCopy.remove(i);
				d = d-1;
				newhold = minimax(untouchCopy,rest, touchedA, touchedB,suma,sumb,d,di,false);
				d = d+1;
				suma = suma-rest.get(i)[0];
				touchedA.remove(touchedA.size()-1);
				untouchCopy.add(i);

				//implement tie breaker
				if(maxEval[0]==newhold[0]) {
					if(maxEval[1]>newhold[1]) {
						maxEval[0] = newhold[0];
						maxEval[1] = newhold[1];
					}
					
				}
				else if(maxEval[0]<newhold[0]) {
					maxEval[0] = newhold[0];
					maxEval[1] = newhold[1];
				}
				
			}
			return maxEval;
		}
		else {
			Double [] minEval = new Double[2];
			minEval[0] = Double.POSITIVE_INFINITY;
			ArrayList<Integer> untouchCopy = new ArrayList<Integer>(untouch);
			for(Integer i:untouch) {
				touchedB.add(i);
				sumb+=rest.get(i)[1];
				untouchCopy.remove(i);
				d = d-1;
				Double[] newhold = new Double [2];
				newhold = minimax(untouchCopy,rest,touchedA, touchedB,suma,sumb,d,di,true);
				d = d+1;
				touchedB.remove(touchedB.size()-1);
				sumb = sumb - rest.get(i)[1];
				untouchCopy.add(i);
				if(minEval[0]==newhold[0]) {
					if(minEval[1]>newhold[1]) {
						minEval[0] = newhold[0];
						minEval[1] = newhold[1];
					}
				}
				else if(minEval[0]>newhold[0]) {
					minEval[0] = newhold[0];
					minEval[1] = newhold[1];
				}
			}
			return minEval;
		}

	}
	
	public static Double[] ab(Double[] alph, Double[] bet, ArrayList<Integer> untouch, Map<Integer, Double[]> rest, ArrayList<Integer> touchedA, ArrayList<Integer> touchedB, Double suma, Double sumb, int d, int di, boolean maxPlay) {
		if(d==0||(touchedA.size()==5&&touchedB.size()==5)) {
			Set <Integer> holder = new HashSet<Integer>();
			holder.add(touchedA.get(0)%10);
			holder.add(touchedA.get(1)%10);
			holder.add(touchedA.get(2)%10);
			holder.add(touchedA.get(3)%10);
			holder.add(touchedA.get(4)%10);
			if(holder.size()==5) {
				suma+=120;
			}
			Set <Integer> holderB = new HashSet<Integer>();
			holderB.add(touchedB.get(0)%10);
			holderB.add(touchedB.get(1)%10);
			holderB.add(touchedB.get(2)%10);
			holderB.add(touchedB.get(3)%10);
			holderB.add(touchedB.get(4)%10);
			if(holderB.size()==5) {
				sumb+=120;
			}
			Double [] hold = new Double [2];
			hold[0] = suma - sumb;
			hold[1] = (double)touchedA.get((10-di)/2);
//			if(hold[1]==26604) {
//				System.out.println("hi:"+ sumb);
//			}
			
			return hold;
		}
		if(maxPlay) {
			Double [] maxEval = new Double[2];
			maxEval[0] = Double.NEGATIVE_INFINITY;
			Double[] newhold = new Double [2];
			ArrayList<Integer> untouchCopy = new ArrayList<Integer>(untouch);
//			System.out.println("THE DEPTH BEFORE: "+ d);
			for(Integer i:untouch) {
				touchedA.add(i);
				suma+=rest.get(i)[0];
//				System.out.println("untouch: "+ untouchCopy + "d: "+ d);
				d = d -1;
				
				untouchCopy.remove(i);
				
				newhold = ab(alph, bet, untouchCopy,rest,touchedA, touchedB,suma,sumb,d,di,false);
				d = d+1;
				suma = suma-rest.get(i)[0];
				touchedA.remove(touchedA.size()-1);
				untouchCopy.add(i);
				if(Double.compare(maxEval[0], newhold[0])==0) {
					if(maxEval[1]>newhold[1]) {
						maxEval[0] = newhold[0];
						maxEval[1] = newhold[1];
					}
				}
				else if(maxEval[0]<newhold[0]) {
					maxEval[0] = newhold[0];
					maxEval[1] = newhold[1];	
				}
				if(maxEval[0]>=bet[0]) {
					return maxEval;
				}
				
				if(bet[0]<maxEval[0]) {
					alph[0]=maxEval[0];
					alph[1]=maxEval[1];
				}
				
			}
			return maxEval;
		}
		else {
			Double [] minEval = new Double[2];
			minEval[0] = Double.POSITIVE_INFINITY;
			Double eval = 0.0;
			ArrayList<Integer> untouchCopy = new ArrayList<Integer>(untouch);
			for(Integer i:untouch) {
				touchedB.add(i);
				sumb+=rest.get(i)[1];
//				System.out.println(untouchCopy +" min d: "+ d);
				untouchCopy.remove(i);
				
				d = d-1;
				
				Double[] newhold = new Double [2];
				
				newhold = ab(alph, bet, untouchCopy,rest,touchedA, touchedB,suma,sumb,d,di,true);
				d = d+1;
				touchedB.remove(touchedB.size()-1);
				sumb = sumb - rest.get(i)[1];
				untouchCopy.add(i);
				
				if(Double.compare(minEval[0], newhold[0])==0) {
					if(minEval[1]>newhold[1]) {
						minEval[0] = newhold[0];
						minEval[1] = newhold[1];
					}
					
				}
				else if(minEval[0]>newhold[0]) {
					minEval[0] = newhold[0];
					minEval[1] = newhold[1];
				}
				if(alph[0]>=minEval[0]) {
					return minEval;
				}
				if(alph[0]>minEval[0]) {
					bet[0]=minEval[0];
					bet[1] = minEval[1];
				}
				

				
			}
			return minEval;

			
		}

	}

}
