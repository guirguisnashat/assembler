package assembler;

public class objectcode {
sysmboltable s=new sysmboltable();
regex x=new regex();
char[] input;
char[] output;
static int start=1;
static int endadd=0;
int end=0;
public String search(String opcode) {
//	System.out.println(Mains.setout.get(1)[0]);
	for(int i=0;i<Mains.setout.size();i++) {
		
		if(opcode.equalsIgnoreCase(Mains.setout.get(i)[0])) {
	//		System.out.println("found");
			return Mains.setout.get(i)[1];
		}
	}
	return null;
	
}
public void copy(char[] out,char[] in,int end) {
	for(int i=0;i<in.length;i++) {
		out[end]=in[i];
		end++;
	}
	
}
public char[] instruction(int st) {
	output=new char[60];
	end=0;
	s.filltable();
	int i;
	String test="vs";
	String ins="d";
	String opr="ds";
	int address;
	String temp="fsv";
	char[] index; 
	String returned="ddf";
	for(i=st;i<Mains.output.size()-1;i++) {
	
	//	System.out.println(i);
		test=s.getopcode(i);
	//	System.out.println(test);
		ins=search(test);
	//	System.out.println(test);
	//	System.out.println(ins);
	//	System.out.println(ins);
		if(ins!=null) {
			opr=s.getoperandstr(i);
			
			if(x.checkindex(opr)) {
				opr=x.cut(opr, 0, opr.length()-2);
				
				address=s.sympoltable.get(opr);
				temp=Integer.toHexString(address);
				
				index=new char[temp.length()];
				index=temp.toCharArray();
				
				if(index[0]=='0'||index[0]=='1'||index[0]=='2'||index[0]=='3'||index[0]=='4'||index[0]=='5'||index[0]=='6'||index[0]=='7') {
					switch(index[0]) {
					case'0':
						index[0]='8';
						break;
					case'1':
						index[0]='9';
						break;
					case'2':
						index[0]='a';
						break;
					case'3':
						index[0]='b';
						break;
					case'4':
						index[0]='c';
						break;
					case'5':
						index[0]='d';
						break;
					case'6':
						index[0]='e';
						break;
					case'7':
						index[0]='f';
						break;
					default:
						break;
	
					}
					
				}
				temp=String.copyValueOf(index);
				
				returned=temp;
				int size=4;
				while(returned.length()<size) {
					returned="0"+returned;
				}
				returned=ins+returned;
				input=new char[returned.length()];
				input=returned.toCharArray();
				if(end+input.length<=output.length) {
					copy(output,input,end);
					start++;
					end=end+input.length;
				}
			}else {
				s.filltable();
				//System.out.println(s.sympoltable);
				//System.out.println(opr);
				address=s.sympoltable.get(opr);
				
				returned=Integer.toHexString(address);
				
				int size=4;
				while(returned.length()<size) {
					returned="0"+returned;
				}
				returned=ins+returned;
				input=new char[returned.length()];
				input=returned.toCharArray();
				if(end+input.length<=output.length) {
					copy(output,input,end);
					start++;
					end=end+input.length;
				}
			}
			
		}else {
			switch(test) {
			case"BYTE":
				int ascii;
				int a=0;
				String y="svd";
				char[] asciiarr=new char[3];
				
				temp=s.getoperandstr(i);
				
				boolean d=x.check(temp);
				if(d) {
					
					temp=x.cut(temp, 2, temp.length()-1);
					index=new char[temp.length()];
					index=temp.toCharArray();
					input=new char[2*temp.length()];
					for(int j=0;j<index.length;j++) {
						ascii=(int)index[j];
						
						y=Integer.toHexString(ascii);
						asciiarr=y.toCharArray();
						
						input[a]=asciiarr[0];
						
						input[a+1]=asciiarr[1];
						a=a+2;
					}
				}else {
					int size=6;
					temp=s.getoperandstr(i);
					do {
						temp="0"+temp;
					}while(temp.length()<size);
					input=new char[temp.length()];
					input=temp.toCharArray();
				}
				if(end+input.length<=output.length) {
					copy(output,input,end);
					start++;
					end=end+input.length;
				}
				break;
			case"WORD":
			//	System.out.println("done");
				int size=6;
				temp=s.getoperandstr(i);
				int ascii2=Integer.parseInt(temp);
				temp=Integer.toHexString(ascii2);
				do {
					temp="0"+temp;
				}while(temp.length()<size);
				input=new char[temp.length()];
				input=temp.toCharArray();
				if(end+input.length<=output.length) {
					copy(output,input,end);
					start++;
					end=end+input.length;
				}
				break;
			default:
				break;
			}
		}
		
		
	}
	
	
	
	return output;
	
}
	
public String getstart(int st) {
	
	s.filltable();
	
	String start =Integer.toHexString(s.addresslist.get(st-1));
	int size=6;
	do {
		start="0"+start;
	}while(start.length()<size);
	return start;
}
public int getstartint(int st) {
	
	s.filltable();
	return s.addresslist.get(st);
}

public String getsize() {
//	System.out.println(s.addresslist.size());
	if(start>s.addresslist.size()||start==s.addresslist.size()) {
	//	System.out.println("done");
		start=s.addresslist.size()-1;
	}
	int size=getstartint(start)-getstartint(endadd);
//	System.out.println(start);
//	System.out.println(endadd);
	endadd=start;
	
	return Integer.toHexString(size);
}
public String getprogramsize() {
    s.filltable();
    String startstr=Integer.toHexString(s.addresslist.get(s.addresslist.size()-1)-s.addresslist.get(0));
	int sizestr=6;
	do {
		startstr="0"+startstr;
	}while(startstr.length()<sizestr);
	return startstr;
}







}
