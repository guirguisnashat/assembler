package assembler;

import java.awt.List;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class splitedarrayofstrings {
//LinkedList<String> input = new LinkedList<String>();
//LinkedList<String[]> output=new LinkedList<String[]>();
int i=0;
BufferedReader br = null;

public LinkedList read(LinkedList<String> input,String path) throws IOException {
	try {
		br = new BufferedReader(new FileReader(path));
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	try {
	    StringBuilder sb = new StringBuilder();
	    String everything = " ";
	    everything=	br.readLine();
	   
	  while (everything != null) {
	        sb.append(everything);
	       
	        everything = sb.toString();
	     //   System.out.println(everything);
	  	  input.add(everything);
	 sb.delete(0, sb.length());
	      everything = br.readLine();

	
	      
	    }
	    
	   
	} finally {
	    br.close();
	}
	return input;
}

	   
	



public LinkedList split(LinkedList<String> input,String key,LinkedList<String[]> output) {
	i=0;
	String[] s=new String[3];
	while(i<input.size()) {
		s=input.get(i).split(key);
		output.add(s);
		if(output.get(i).length == 4)
			output.get(i)[2]=output.get(i)[2]+output.get(i)[3];
		i++;
	}
	
	return output;
	
}














}
