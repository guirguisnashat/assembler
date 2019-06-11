package assembler;

public class Errors {
sysmboltable sympol=new sysmboltable();
objectcode objectcode=new objectcode();
regex regex=new regex();
public boolean start() {
	
	if(Mains.output.get(0)[1].equals("START")) {
		return true;
	}
	System.out.println("The program must start with opcode \"START\"");
	return false;
}
public boolean end() {
	// System.out.println(Mains.output.get(Mains.output.size()-1)[1]);
	if(Mains.output.get(Mains.output.size()-1)[1].equals("END")) {
		return true;
	}
	System.out.println("The program must end with opcode \"END\"");
	return false;
}
public boolean endstart() {
	if(Mains.output.get(Mains.output.size()-1)[2].equals(Mains.output.get(1)[0])) {
		return true;
	}
	System.out.println("The last oprand must be equal to the first label");
	return false;
}
public boolean opcode() {
	sympol.filltable();
	String ins="s";
//	System.out.println(Mains.output.get(6)[1]);
	for(int i=1;i<Mains.output.size()-1;i++) {
		ins=objectcode.search(Mains.output.get(i)[1]);
	//	System.out.println(objectcode.search("LDA"));
	//	System.out.println(ins);
		if(ins!=null) {
			int label;
			String oprand="b";
			oprand=Mains.output.get(i)[2];
		//	System.out.println(oprand);
			if(regex.checkindex(oprand)) {
			//	System.out.println("done");
				oprand=regex.cut(oprand,0,oprand.length()-2);
				try {
					label=sympol.sympoltable.get(oprand);
				//	System.out.println(label);
				}catch(Exception e) {
					System.out.println("This label in line number "+i+1+" isnot existed");
					return false;
				}
			}else {
				try {
					//	System.out.println("done");
					//	System.out.println(oprand);
						
						label=sympol.sympoltable.get(oprand);
					//	System.out.println(label);
					}catch(Exception e) {
						System.out.println("This label in line number "+(i+1)+" isnot existed");
						return false;
					}
			}
			
          
		}else {
			ins=Mains.output.get(i)[1];
	//		System.out.println(ins);
			switch(ins) {
			case"RESW":
				break;
			case"RESB":
				break;
			case"WORD":
			//	System.out.println("done");
				break;
			case"BYTE":
		        break;
			default:
				System.out.println("This opcode is not an assembler directive or from the instruction set");
				return false;
			}
		}
		
	}
	return true;
}
public boolean errors() {
	boolean st=start();
	boolean en=end();
	boolean sten=endstart();
	boolean op=opcode();
	if(st&&en&&sten&&op) {
		return true;
	}
	return false;
}
}
