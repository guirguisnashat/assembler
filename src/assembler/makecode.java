package assembler;

public class makecode {
	objectcode o=new objectcode();
	public String makestart(int start,String name,String size) {
		start--;
		String startstr=Integer.toHexString(start);
		int sizestr=6;
		do {
			startstr="0"+startstr;
		}while(startstr.length()<sizestr);
		return String.format("H "+name+" "+startstr+" "+size, null);
	}
	public String makeend(int end) {
		String startstr=Integer.toHexString(end);
		int sizestr=6;
		do {
			startstr="0"+startstr;
		}while(startstr.length()<sizestr);
		return String.format("E "+startstr, null);
	}
	public String makerecord(String start) {
		
		char[] in=new char[60];
		
		in=o.instruction(objectcode.start);
		
		//System.out.println(in);
		String out=String.copyValueOf(in);
		String size=o.getsize();
		out="T"+" "+start+" "+size+" "+out;
		return out;
	}
	
	
}
