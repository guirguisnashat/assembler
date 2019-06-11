package assembler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class regex {
	// TODO Auto-generated method stub
			 // String to be scanned to find the pattern.
		      
		     

		      // Create a Pattern object
		      Pattern r = Pattern.compile("('$)");
		      Pattern p=Pattern.compile("(^X')");
		      Pattern i=Pattern.compile("(,X)$");

		      // Now create matcher object.
		      
		      
		      
		      
		      public boolean checkindex(String line) {
		    	Matcher o=i.matcher(line);
		    	if(o.find()) {
		    		return true;
		    	}
				return false;
		      }
		      
		      public boolean check(String line) {
		    	  Matcher m = p.matcher(line);
			      Matcher o=r.matcher(line);
			     
		    	  if (m.find( )&&o.find()) {
				        return true;				         

				      }
				return false;
		      }
		    public String cut(String line,int begin,int end) {
		    return	line.substring(begin, end);
		    }
}
