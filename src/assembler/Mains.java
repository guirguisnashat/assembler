package assembler;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;

public class Mains {
	Errors error;
	makecode makecode;
	objectcode object;
	regex reg;
	splitedarrayofstrings arrays;
	sysmboltable sympol;
	static LinkedList<String> input = new LinkedList<String>();
	static LinkedList<String[]> output=new LinkedList<String[]>();
	static LinkedList<String> set=new LinkedList<String>();
	static LinkedList<String[]> setout=new LinkedList<String[]>();
	static char[] record=new char[60];
	PrintWriter out = null;
Mains(String filename){
error=new Errors();
object=new objectcode();
reg=new regex();
arrays=new splitedarrayofstrings();
sympol=new sysmboltable();
makecode=new makecode();
try {
    input=arrays.read(input,filename);
	set=arrays.read(set,"Sic Instruction Set.txt");
//	System.out.println(input);
}catch(Exception e) {
	e.printStackTrace();
}
output=arrays.split(input,"\\s+",output);
setout=arrays.split(set, ":",setout);
//System.out.println(output.get(0)[1]);
sympol.filltable();
sympol.remove();
//System.out.println(sympol.sympoltable);
if(error.errors()) {
	try {
		  out = new PrintWriter(new OutputStreamWriter(
		      new BufferedOutputStream(new FileOutputStream("out.txt")), "UTF-8"));
		  
		  out.println(sympol.sympoltable);
		  out.println(sympol.addresslist);
		  
		  out.println(makecode.makestart(objectcode.start, sympol.getname(), object.getprogramsize()));

		  while( objectcode.start < sympol.addresslist.size()-1) {
			 
		    out.println(makecode.makerecord(object.getstart(objectcode.start)));
		  }  
		  out.println(makecode.makeend(object.getstartint(0)));
		 
		
		} catch (UnsupportedEncodingException e) {
		  e.printStackTrace();
		} catch (FileNotFoundException e) {
		  e.printStackTrace();
		} finally {
		  if(out != null) {
		    out.flush();
		    out.close();
		  }
		}
}else {
	System.out.println("Error");
}

	
}
}
