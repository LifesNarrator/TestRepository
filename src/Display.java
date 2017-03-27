import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.*;


public class Display extends JFrame{

	private static final long serialVersionUID = 1L;	
	private static JLabel textField;
	private static JTextField tField;
	JTextField jtf, orig, neuw;
	JCheckBox sqBrac, shorten,parenthesis,relocate,titleCase,repFS;
	JButton button, goButton;
	JFileChooser jfc;
	JSpinner jsSL;
	
	public Display(){
		Init();
	}
	
	public Display(String [] args){
		Init();
		if(args.length>0){
			textField.setText(args[0]);
			File dir = new File(textField.getText());
			if (dir.isDirectory()) { // make sure it's a directory
				for (final File f : dir.listFiles()) {
					if (f.isFile()) {
						
					}
				}
			}
		}
	}
	
	public void Init(){

		this.setTitle("Change File Names");
		setSize(600, 308);
        setBackground(Color.WHITE);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
        
        JLabel lblTTC = new JLabel("Title Case ?");				
        lblTTC.setBounds(10, 112, 154, 20);
        getContentPane().add(lblTTC);
        
        titleCase = new JCheckBox();							//Make name title case
        titleCase.setBounds(250, 112, 30, 20);
        getContentPane().add(titleCase);
        

        JLabel l1 = new JLabel("Directory");				
        l1.setBounds(10, 11, 70, 20);
        getContentPane().add(l1);
        
        textField = new JLabel();							//directory goes here
        textField.setBounds(70, 11, 500, 20);
        textField.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                try {
					Desktop.getDesktop().open(new File(textField.getText()));
				} catch (IOException e1) {
					
				}
             }
         });
        getContentPane().add(textField);
        
        JLabel l5 = new JLabel("File Name");				
        l5.setBounds(10, 31, 70, 20);
        getContentPane().add(l5);
        
        tField = new JTextField();							//file goes here
        tField.setBounds(70, 31, 500, 20);
        getContentPane().add(tField);
        
        button = new JButton("Choose directory...");			//open JFileChooser
        button.setFont(new Font("Tahoma", Font.PLAIN, 14));
        button.setBounds(10, 51, 200, 26);
        getContentPane().add(button);
        button.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				jfc = new JFileChooser();
				if(textField.getText().length()>0){
					jfc.setCurrentDirectory(new java.io.File(textField.getText()));					
				}else{
					jfc.setCurrentDirectory(new java.io.File("Z:/Like_a_G1/Downloads"));					
				}
				jfc.setDialogTitle("choose a directory");
				if (jfc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) { 
					if(jfc.getSelectedFile().isFile()){
				      textField.setText(jfc.getCurrentDirectory().toString());
				      tField.setText(jfc.getSelectedFile().getName());
					}
					else{
						textField.setText(jfc.getSelectedFile().getName());
					    tField.setText("Select a file to see it here");
					}
			      }
			}
        });
        
        
        JLabel l2 = new JLabel("Remove Parenthesis () ?");				
        l2.setBounds(10, 76, 154, 20);
        getContentPane().add(l2);
        
        parenthesis = new JCheckBox();						//remove anything in parenthesis
        parenthesis.setBounds(250, 76, 30, 20);
        getContentPane().add(parenthesis);
        
/*      JLabel sortFol = new JLabel("Move Files to One Folder?");				
        sortFol.setBounds(290, 91, 352, 20);
        getContentPane().add(sortFol);
        
        relocate = new JCheckBox();						//put all files in the same folder 
        relocate.setBounds(510, 91, 30, 20);
        getContentPane().add(relocate);*/
        
        JLabel l3 = new JLabel("Remove Square Brackets [] ?");				
        l3.setBounds(10, 95, 177, 20);
        getContentPane().add(l3);
        
        sqBrac = new JCheckBox();							//remove anything in square brackets
        sqBrac.setBounds(250, 95, 30, 20);
        getContentPane().add(sqBrac);

        JLabel l6 = new JLabel("Shorten File Length (for random serials)?");				
        l6.setBounds(10, 161, 223, 20);
        getContentPane().add(l6);
        
        shorten = new JCheckBox();							//remove random characters
        shorten.setBounds(250, 161, 30, 20);
        getContentPane().add(shorten);
        shorten.setToolTipText("a S01E21.avi is 8");
        
        jsSL = new JSpinner(new SpinnerNumberModel());
        jsSL.setBounds(290, 161, 40, 20);
        getContentPane().add(jsSL);
        jsSL.setEnabled(false);
        
        shorten.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(shorten.isSelected()){
					jsSL.setEnabled(true);
				}else
					jsSL.setEnabled(false);
			}
        });
        
        JLabel l4 = new JLabel("Remove This Text:");				
        l4.setBounds(10, 185, 150, 20);
        getContentPane().add(l4);
        
        jtf = new JTextField();								//remove this part
        jtf.setBounds(120, 185, 150, 20);
        getContentPane().add(jtf);
        
        
        JLabel l7 = new JLabel("Replace This Text:");				
        l7.setBounds(10, 205, 150, 20);
        getContentPane().add(l7);
        
        orig = new JTextField();								//replace this part
        orig.setBounds(120, 205, 150, 20);
        getContentPane().add(orig);
        
        JLabel l8 = new JLabel("With This Text:");				
        l8.setBounds(290, 205, 150, 20);
        getContentPane().add(l8);
        
        neuw = new JTextField();								//with this part
        neuw.setBounds(385, 205, 150, 20);
        getContentPane().add(neuw);
        
        goButton = new JButton("Start");					//start
        goButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
        goButton.setBounds(10, 235, 200, 26);
        goButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				go();
				JOptionPane.showMessageDialog(null, "Complete", "InfoBox: ", JOptionPane.INFORMATION_MESSAGE);
			}
        });
        getContentPane().add(goButton);
        
        repFS = new JCheckBox();
        repFS.setBounds(250, 130, 30, 20);
        getContentPane().add(repFS);
        
        JLabel lblFS = new JLabel("Replace full stops ?");
        lblFS.setBounds(10, 130, 154, 20);
        getContentPane().add(lblFS);
	}
	
	public void go(){
		File dir = new File(textField.getText());
		if (dir.isDirectory()) { // make sure it's a directory
			for (final File f : dir.listFiles()) {
				if (f.isFile()) {
					String s = f.getName();
					String ext=s.substring(s.length()-4,s.length());
					s = s.substring(0,s.length()-4);
					if(parenthesis.isSelected()==true){
						s = removePar(s);
					}
					if(sqBrac.isSelected()==true){
						s = removeSqBr(s);
					}
					
					if(jtf.getText().equals("")==false){
						s=s.replace(jtf.getText(), "");
					}
					
					if(shorten.isSelected()==true){
						s = s.substring(0,(int)jsSL.getValue());
					}
					
					if(orig.getText().equals("")==false){
						s = s.replace(orig.getText(),neuw.getText());
					}
					
					if(titleCase.isSelected()==true){
						s = toTitleCase(s);
					}
					
					if(repFS.isSelected()==true){
						s = replaceFS(s);
					}
					s = s+ext;
					//s = s.replace("..avi",".avi");
					//System.out.println(s);/*

					try {
						if (f.renameTo(new File(textField.getText()+"/" + s))) {
							//System.out.println("Rename successful"+ f.getName());
						} else {
							//System.out.println("Rename failed");
						}
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}//*/
					
				}
			}
		}
	}
	
	public static String replaceFS(String s){
		String s2="";
		s2 = s.substring(0, s.length()-4);
		s2 = s2.replace('.', ' ');
		s2 = s2+(s.substring(s.length()-4, s.length()));
		return s2;
	}

	
	public static String toTitleCase(String givenString) {
	    String[] arr = givenString.split(" ");
	    StringBuffer sb = new StringBuffer();

	    for (int i = 0; i < arr.length; i++) {
	        sb.append(Character.toUpperCase(arr[i].charAt(0)))
	            .append(arr[i].substring(1)).append(" ");
	    }          
	    return sb.toString().trim();
	} 
	
	public static String removePar(String s){
		String s2="";
		boolean inBrackets = false;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '(') {
				inBrackets = true;
			} else if (s.charAt(i) == ')') {
				inBrackets = false;
			} else if (inBrackets == false) {
				if (s.charAt(i) == ' ') {
					if (s.charAt(i - 1) == ')') {
						
					} else {
						s2 = s2 + s.charAt(i);
					}

				} else {
					s2 = s2 + s.charAt(i);
				}
			}
		}
		return s2;
	}
	
	public static String removeSqBr(String s){
		String s2="";
		boolean inBrackets = false;
		for(int i=0;i<s.length();i++){
			if(s.charAt(i)=='['){
				inBrackets = true;
			}else if(s.charAt(i)==']'){
				inBrackets = false;
			}else if(inBrackets==false){
				if(s.charAt(i)==' '){
					if(s.charAt(i-1) ==']'){
						
					//}else if(s.charAt(i+1) =='['){
						
					}else{
						s2 = s2+s.charAt(i);
					}
					
				}else{
					s2 = s2+s.charAt(i);
				}
			}
		}
		return s2;
	}
}
