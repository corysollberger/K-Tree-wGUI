import java.util.Scanner;

public class RecordTester {
	public static void main(String[] args){
		Record root = null;
		RecordManager rm = null;
		int kInt = 0;
		boolean run = true;
		
		//Console Commands Begin
		while(run){
			System.out.println("\nPlease Enter a Command: ");
			Scanner console = new Scanner(System.in);
			String command = console.nextLine();
			
			//Parse the arguments of string s
			String[] s = command.split(" ");
			System.out.println(s[0]);
			
			//Command c_k   where k is an integer value specified
			//Creates a k-search-tree
			
			//c_k Command
			if (s[0].equals("c")){
				kInt = Integer.parseInt(s[1]);
				root = new Record(1,2,kInt);
				rm = new RecordManager(root);
				System.out.println("The root: " + root + ". The RM: " + rm);
				for (int i=0;i<5;i++){
					Record r = new Record((4-i),2,4);
					root.setChildren(r);
				}
				//root.getChildren();
				root.orderChildren();
				//root.getChildren();
			} else if (s[0].equals("s")){ //s_k_d
				int k = Integer.parseInt(s[1]);
				float d = Float.parseFloat(s[2]);
				Record temp;
				Record rec = new Record(k,d,kInt);
				temp = rm.addNode(k, root);
				
				//Replaces the float value associated with the key
				if (temp.getKey() == k){
					temp.setD(d);
				} else if (temp != null) { //Sets the child of the returned node to the new node
					temp.setChildren(rec);
					temp.orderChildren();
					System.out.println("temp key: " + temp.getKey());
				}
			} else if (s[0].equals("e")){ //Checks if a node exists in the tree
				int k = Integer.parseInt(s[1]);
				Record temp = rm.addNode(k, root);
				if (temp.getKey() == k){
					System.out.println("1");
				} else {
					System.out.println("0");
				}
			} else if (s[0].equals("r")){
				int k = Integer.parseInt(s[1]);
				Record temp = rm.findNode(k, root);
				System.out.println("Node key: " + temp.getKey());
				System.out.println("Float value: " + temp.getD(temp));
			} else if (s[0].equals("xh")){
				System.out.println("Height: " + rm.height(root));
			} else if (s[0].equals("xs")){ //Prints the height of the tree
				System.out.println("Size: " + rm.size(root));
			} else if (s[0].equals("xa")){ //Prints the nodes in preOrder, on a single line
				rm.preOrder(root);
			} else if (s[0].equals("xb")){
				rm.postOrder(root);
			} else if (s[0].equals("exit")){ //Exits the loop
				run = false;
			}
		}
		
	}
}
