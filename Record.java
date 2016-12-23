import java.lang.reflect.Array;
import java.util.Arrays;

//A "Record", used in the RecordManager class to instantiate individual nodes holding (k,d)
//Where k is a non-negative integer, and d is a floating point number
public class Record {
	
	int k; // A non-negative integer
	float d; //Floating point #
	Record[] children;
	
	//Constructor
	Record(int k, float d, int size){
		this.k = k;
		this.d = d;
		Record[] children= new Record[size];
		this.children = children;
	}
	
	//Returns the integer, k
	public int getKey(){
		return k;
	}
	
	public void setD(float d){
		this.d = d;
	}
	
	//Returns the float, d
	public float getD(Record r){
		return r.d;
	}
	//Checks if all children have been filled
	public int checkChildren(){
		if (children[children.length-1] == null){
			return 0; //returns false
		} else
			return 1; //returns true
	}
	
	//Sets the next "null" child to the argument r
	public void setChildren(Record r){
		for(int i=0;i<children.length;i++){
			if (children[(children.length-(children.length-i))] == null){
				children[(children.length-(children.length-i))] = r;
				break;
			}
		}
	}
	
	//Prints the children of the current k-tree node
	public Record[] getChildren(){
		for(int i=0;i<children.length;i++){
			if (children[(children.length-(children.length-i))] != null){
				System.out.println(children[(children.length-(children.length-i))].getKey());
			} else {
				break;
			}
		}
		return children;
	}
	
	//Orders the children by label
	void orderChildren(){
		if (children[0] != null && children[1] != null){
		for (int i=0;i<(children.length-1);i++){
			for(int j=i+1;j<children.length;j++){
				if (children[j] != null){
				if (children[i].getKey() > children[j].getKey()){
					Record temp = children[i]; //temp holds children[i]
					children[i] = children[j]; //sets children[i] to children[i+1]
					children[j] = temp; //sets children[i+1] to temp
				}
				}
			}
		}
		}
	}
}
