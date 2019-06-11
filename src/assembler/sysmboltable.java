package assembler;

import java.awt.List;
import java.util.ArrayList;
import java.util.HashMap;

public class sysmboltable {
public int startaddress;
private String name="r";
String opcode=" ";
HashMap<String,Integer> sympoltable;
int operand;
regex regex=new regex();
ArrayList<Integer> addresslist ;

public ArrayList<Integer> getAddresslist() {
	return addresslist;
}
public int getstartaddress() {
//System.out.println(Mains.output.get(0)[0]);
	return Integer.parseInt(Mains.output.get(0)[2],16);
}
public String getname() {
	return Mains.output.get(0)[0];
}
public int getoperand(int i) {
	
	return Integer.parseInt(Mains.output.get(i)[2],16);
}
public String getoperandstr(int i) {
	return Mains.output.get(i)[2];
}
public String getlabel(int i) {
	return Mains.output.get(i)[0];
}
public String getopcode(int i ) {
	return Mains.output.get(i)[1];
}
public void remove() {
	sympoltable.remove("");
}
public void filltable() {
	sympoltable=new HashMap<String,Integer>();
	addresslist = new ArrayList<Integer>();
	startaddress=getstartaddress();
	
	name=getname();
	
	sympoltable.put(Mains.output.get(1)[0],getstartaddress());
	addresslist.add(startaddress);
	for(int i=2;i<Mains.output.size();i++) {

		opcode=Mains.output.get(i)[1];
		//System.out.println(opcode);
		
		switch(opcode) {
		case "RESW":
			//c
			operand=getoperand(i);
			startaddress=operand*(0x3)+startaddress;
			sympoltable.put(getlabel(i),startaddress );
			addresslist.add(startaddress);
			if(i==Mains.output.size()-1) {
				
			}
			break;
		case "RESB":
			//System.out.println("done");

			operand=getoperand(i);
			//System.out.println(operand);
			startaddress=operand+startaddress;
			//System.out.println(startaddress);
			sympoltable.put(getlabel(i),startaddress );
			addresslist.add(startaddress);

			break;
		case "BYTE":
			String test =getoperandstr(i);
			//System.out.println(getoperandstr(i));
			boolean j=regex.check(test);
			

			int hexa;
			if(j) {
				//System.out.println("done");
				test=regex.cut(test,2,test.length()-1);
				  int f=test.length();
				    String g=Integer.toString(f);
				    int d=Integer.parseInt(g, 16);
				   startaddress=d+startaddress;
				   sympoltable.put(getlabel(i),startaddress );
				   addresslist.add(startaddress);

			}else {
				startaddress=(0x1)+startaddress;
				sympoltable.put(getlabel(i),startaddress );
				addresslist.add(startaddress);

			}
			
			
			break;
		case "WORD":
			
			String test1 =getoperandstr(i);
			int hexa1;
			if(regex.check(test1)) {
				test1=regex.cut(test1,2,test1.length()-1);
				  int f=test1.length();
				    String g=Integer.toString(f);
				    int d=Integer.parseInt(g, 16);
				    d=(0x3)*d;
				   startaddress=d+startaddress;
				   sympoltable.put(getlabel(i),startaddress );
					addresslist.add(startaddress);

			}else {
				startaddress=(0x3)+startaddress;
			
				sympoltable.put(getlabel(i),startaddress );
				addresslist.add(startaddress);

				
			}
			break;
		default:
			//System.out.print(getlabel(i)+"\n");
			startaddress=(0x3)+startaddress;
			sympoltable.put(getlabel(i),startaddress );
			
			addresslist.add(startaddress);

			break;
		}
	
	}
	
	
}


}
