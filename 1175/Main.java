import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class Main {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int testcases = sc.nextInt();
		sc.nextLine();
		while(testcases-- > 0) {
			sc.nextLine();
			int couplesNum = sc.nextInt();
			sc.nextLine();
			
			
			Map<Integer, List<Integer>> boysPreferences = new HashMap<Integer, List<Integer>> ();
			Map<Integer, List<Integer>> girlsPreferences = new HashMap<Integer, List<Integer>> ();
			
			//Initialize the girls preference matrix
			for(int i = 1; i <= couplesNum; i++) {
				ArrayList<Integer> preferences = new ArrayList<Integer> ();
				for(int j = 1; j <= couplesNum; j++) {
					preferences.add(sc.nextInt());
				}
				boysPreferences.put(i, preferences);
				sc.nextLine();
			}
			
			//Initialize the boys preference matrix
			for(int i = 1; i <= couplesNum; i++) {
				ArrayList<Integer> preferences = new ArrayList<Integer> ();
				for(int j = 1; j <= couplesNum; j++) {
					preferences.add(sc.nextInt());
				}
				girlsPreferences.put(i, preferences);
				sc.nextLine();
			}
			
			
			stableMarriage(boysPreferences, girlsPreferences);
			
			if(testcases > 0) {
				System.out.println();
			}
		}
		
	}
	
	static void stableMarriage(Map<Integer, List<Integer>> boysPreferences, Map<Integer, List<Integer>> girlsPreferences) {
		
		HashMap<Integer, Integer> girlsMatch = new HashMap<Integer, Integer> (); // contains as the keys the index of the girls and the value her match.
		List<Integer> singleMen = new ArrayList<Integer> (); // List
		HashMap<Integer, Integer> boysMatch = new HashMap<Integer, Integer> (); //That's the result
		
		//Initialize everyone as single: -1
		for(int i = 1; i <= boysPreferences.size(); i++) {
			girlsMatch.put(i, -1);
			singleMen.add(i);
		}
		
		while(!singleMen.isEmpty()) { // while we have single men
			
			int single_boy = singleMen.remove(0); //takes the first single men in the list
			
			List<Integer> girlsHeLikes = boysPreferences.get(single_boy);
			Iterator<Integer> itr = girlsHeLikes.iterator();
			
			while(itr.hasNext()) { // while until we find a match to the single_boy
				int girl = itr.next();
				
				if(girlsMatch.get(girl) == -1) { // girl is single
					girlsMatch.put(girl, single_boy);
					boysMatch.put(single_boy, girl);
					break; // found a date
					
				} else { // girl is not single
					
					int boyfriend = girlsMatch.get(girl);
					List<Integer> girlPreferences = girlsPreferences.get(girl);
					if(girlPreferences.indexOf(boyfriend) > girlPreferences.indexOf(single_boy)) { // she likes single_boy more than she's actual boyfriend, then change
						girlsMatch.put(girl, single_boy);
						boysMatch.put(single_boy, girl);
						singleMen.add(boyfriend);
						break; // found a date
						
					} 
					// else do nothing, keep the search
				}
			}
		}
		
		result(boysMatch);
		
	} 
	
	static void result(Map<Integer, Integer> boysMatch) {
		Iterator itr = boysMatch.entrySet().iterator();
		
	    while (itr.hasNext()) {
	        Map.Entry<Integer, Integer> pair = (Map.Entry<Integer, Integer>) itr.next();
	        System.out.println(pair.getValue());
	    }
	    
	}
	
}
