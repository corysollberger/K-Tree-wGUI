import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class recordGUI extends Frame implements ActionListener{
	
	//Global Variable(s)
	String command; //Holds the current command
	String arg; //Argument to the current command
	String arg2;
	int children;
	int fontCount = 0;
	
	JFrame frame;
	JTextField argument;
	JTextField argument2;
	JTextArea console;
	
	Font font1;
	Font font2;
	Font font3;
	
	Record root;
	RecordManager rm;
	
	public recordGUI(){
		
		//Creates frame
		frame = new JFrame();
		frame.setSize(400, 400);
		frame.setLayout(new GridLayout(4,3));
		
		//Creates Panels
		Panel panel = new Panel();
		panel.setPreferredSize(new Dimension (200, 200));
		//panel.setBackground(Color.RED);
		
		Panel panel1 = new Panel();
		panel1.setPreferredSize(new Dimension(200, 200));
		//panel1.setBackground(Color.GREEN);
		
		Panel panel2 = new Panel();
		panel2.setPreferredSize(new Dimension(200,200));
		//panel2.setBackground(Color.BLACK);
		
		Panel panel3 = new Panel();
		panel3.setPreferredSize(new Dimension(200,200));
		//panel3.setBorder(new TitledBorder(new EtchedBorder(), "Command Output"));
		
		/*
		Creates Components
		*/
		
		JLabel comLabel = new JLabel("Select a Command: ");
		JLabel argLabel = new JLabel("Enter Argument: ");
		
		
		console = new JTextArea("Commands will be here...", 5, 32);
		//console.setEditable(false);
		
		argument = new JTextField();
		argument.setPreferredSize(new Dimension(50,20));
		
		argument2 = new JTextField();
		argument2.setPreferredSize(new Dimension(50,20));
		
		JScrollPane scroll = new JScrollPane(console);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		Button exitButton = new Button("Exit");
		exitButton.setPreferredSize(new Dimension(100,50));
		exitButton.addActionListener(this);
		
		Button goButton = new Button("GO");
		goButton.setPreferredSize(new Dimension(100,50));
		goButton.addActionListener(this);
		
		String commands[] = {" ", "c_k", "s_k_d", "e_k", "r_k", "xs", "xh", "xa", "xb"}; //Each Command
		JComboBox comBox = new JComboBox(commands);
		comBox.addActionListener(this);
		
		font1 = new Font("Courier", Font.BOLD, 12);
		font2 = new Font("TimesRoman", Font.BOLD, 12);
		font3 = new Font("Helvetica", Font.BOLD, 12);
		
		//Adds to the frame
		panel.add(comLabel);
		panel.add(comBox);
		panel1.add(argLabel);
		panel1.add(argument);
		panel1.add(argument2);
		panel2.add(goButton);
		panel2.add(exitButton);
		panel3.add(scroll);
		frame.add(panel);
		frame.add(panel1);
		frame.add(panel2);
		frame.add(panel3);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		//Checks for the "Exit" button command first
		if (arg0.getActionCommand() == "Exit"){
			System.exit(0);
		} else if (arg0.getActionCommand() == "GO"){
			
			arg = argument.getText();
			arg2 = argument.getText();
			frame.setVisible(true);
			if (command == "c_k"){
				children = Integer.parseInt(arg);
				root = new Record(1,2,children);
				rm = new RecordManager(root);
				console.setText(console.getText() + "\n" + root);
				frame.setVisible(true);
				//System.exit(0);
			} else if (command == "s_k_d"){
				Record temp;
				Record rec = new Record(Integer.parseInt(arg),Integer.parseInt(arg2), children);
				temp = rm.addNode(children, root);
				
				//Replaces the float value associated with the key
				if (temp.getKey() == Integer.parseInt(arg)){
					temp.setD(Integer.parseInt(arg2));
					console.setText(console.getText() + "\nFloat Value of node Swapped" );
				} else if (temp != null) { //Sets the child of the returned node to the new node
					temp.setChildren(rec);
					temp.orderChildren();
					console.setText(console.getText() + "\nNode Added to RecordManager" );
				}
				root.orderChildren();
				frame.setVisible(true);
				//System.exit(0);
			} else if (command == "e_k"){
				int k = Integer.parseInt(arg);
				Record temp = rm.addNode(k, root);
				if (temp.getKey() == k){
					console.setText(console.getText() + "\n1, the node is in the tree" );
				} else {
					console.setText(console.getText() + "\n0, the node isn't in the tree");
				}
				frame.setVisible(true);
			} else if (command == "r_k"){
				int k = Integer.parseInt(arg);
				Record temp = rm.findNode(k, root);
				console.setText(console.getText() + "\nNode key: " + temp.getKey() + " Float value: " + temp.getD(temp));
				frame.setVisible(true);
			} else if (command == "xh"){
				console.setText(console.getText() + "\nHeight: " + rm.height(root));
			} else if (command == "xs"){
				console.setText(console.getText() + "\nSize: " + rm.size(root));
			} else if (command == "xa"){
				rm.preOrder(root);
			} else if (command == "xb"){
				rm.postOrder(root);
			}
			if (fontCount%3 == 0){
				console.setFont(font1);
				console.setForeground(Color.RED);
				frame.setVisible(true);
			} else if (fontCount%3 == 1){
				console.setFont(font2);
				console.setForeground(Color.GREEN);
				frame.setVisible(true);
			} else {
				console.setFont(font3);
				console.setForeground(Color.BLUE);
				frame.setVisible(true);
			}
			fontCount++;
			
		} else {
			//Parses a command from the action triggered by the event
			JComboBox cb = (JComboBox)arg0.getSource();
			command = (String)cb.getSelectedItem();
		}
		
		
	}
	
}
