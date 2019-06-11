package assembler;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

public class GUI {

	public static void main(String[] args) {
		 JFileChooser chooser = new JFileChooser();
	        FileNameExtensionFilter filter = new FileNameExtensionFilter(
	                ".doc", "txt", "gif");
	        chooser.setFileFilter(filter);
	        int returnVal = chooser.showOpenDialog(null);
	        String filename="sdv";
	        if(returnVal == JFileChooser.APPROVE_OPTION) {
	          
	                 filename=chooser.getSelectedFile().getName();
	               Mains m=new Mains(filename);
	        }
//Mains m=new Mains(filename);
	}

}
