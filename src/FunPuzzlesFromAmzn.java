import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class FunPuzzlesFromAmzn {


	public static void main(String[] args) throws IOException {
          
        List<String> groups = Arrays.asList("1100", "1101", "0010", "0101");
        int result = countGroups(groups);
        System.out.println(String.join(" ", "Result", String.valueOf(result), "groups"));
	}

	
	public static int countGroups(List<String> groups) {
		System.out.println(groups);
		int groupsSize = 0;
		char[][] values = new char[groups.get(0).length()][groups.size()];
		
		//convert List to 2D array
		for(int i = 0; i < groups.size(); i++) {
			int groupIdx = i;
			char[] chs = new char[groups.get(i).length()];
			IntStream.range(0, chs.length).forEach(idx -> values[groupIdx][idx] = groups.get(groupIdx).charAt(idx));
	    }
		
	    int noRelations = 0;
		for (int i = 0; i < values.length; i++) {
		  boolean isRelated = false;	
          for (int j = 0; j < groups.get(i).length(); j++) {
        	  
              if(values[i][j] == '1' && values[i][j] == values[j][i] && i != j) {
            	 groupsSize++;
              	 isRelated = true;
              	 
              	 //mark relation so no counting it anymore
              	 values[j][i] = 'x';
             	System.out.println("Added to groups " + groupsSize);
              }
              else if(values[i][j] == 'x') {
            	  isRelated = true;
              }
            }
          	
            //if not related to any - add relation to itself
          	if(!isRelated)
          		noRelations++;
	    }
		System.out.println(Arrays.deepToString(values));
	    System.out.println("No relations: " + noRelations);
		return groupsSize + noRelations;
	}
}