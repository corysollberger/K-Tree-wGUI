//Holds Records
public class RecordManager{
	Record root;
	
	RecordManager(Record root){
		this.root = root;
	}
	
	//Finds a place for the new node to be placed, replacing the float value if already there
	//Uses the search parameters of a k-search-tree to locate a given key value
	public Record addNode(int key, Record r){
		if (r.getKey()==key){ //if key == root.key, returns root
			return r;
		} else if (r.checkChildren() == 0){ //If the root has open children, will add to the root
			return r;
		} else if (r.checkChildren() == 1){ //If the root doesn't have open children, check for branching
			int i = 0;
			while (key >= r.children[i].getKey() && i<root.children.length-1){
				if (key == r.children[i].getKey()){
					return r.children[i];
				}
				i++;
			}
			return addNode(key, r.children[i]);
		}
		return r;
	}
	
	//Finds a place for the new node to be placed, replacing the float value if already there
		//Uses the search parameters of a k-search-tree to locate a given key value
		public Record findNode(int key, Record r){
			if (r.getKey()==key){ //if key == root.key, returns root
				return r;
			} else if (r.checkChildren() == 0){ //If the root has open children, will add to the root
				return r;
			} else if (r.checkChildren() == 1){ //If the root doesn't have open children, check for branching
				int i = 0;
				while (key >= r.children[i].getKey() && i<root.children.length-1){
					if (key == r.children[i].getKey()){
						return r.children[i];
					}
					i++;
				}
				return findNode(key, r.children[i]);
			}
			return null;
		}
	
	//Returns the size of the tree (# of nodes starting at the root)
	public int size(Record r){
		Record temp = r;
		int size = 0;
		size+=1; //Each node traversal adds 1 to the size
		if (temp.children[0] != null){
			for (int i =0; i<temp.children.length;i++){
				
				if (temp.children[i] != null){
					size += size(temp.children[i]);
				}
			}
		} else
			return size;
		return size;
	}
	
	//Returns the height of the tree (the # of nodes from the deepest leaf)
		public int height(Record r){
			Record temp = r;
			int height = 0;
			if (temp.children[0] != null){
				int tempHeight = height;
				for (int i =0; i<temp.children.length;i++){
						if (height > tempHeight){
							tempHeight = height;
						}
					if (temp.children[i] != null){
						
						height+=height(temp.children[i]);
					}
				}
			} else
				return height;
			return height;
		}
	
	//Prints the nodes in preOrder form (Each node as traversed)
	public void preOrder(Record r){
		Record temp = r;
		System.out.print("(" + temp.getKey() + ", " + temp.getD(temp)+") ");
		if (temp.children[0] != null){
			for (int i =0; i<temp.children.length;i++){
				//System.out.print("current key: " + temp.children[i].getKey());
				if (temp.children[i] != null){
					preOrder(temp.children[i]);
				}
			}
			}
	}
	
	//Prints the nodes in postOrder form (Each node as traversed)
		public void postOrder(Record r){
			Record temp = r;
			
			if (temp.children[0] != null){
				for (int i =0; i<temp.children.length;i++){
					
					if (temp.children[i] != null){
						
						postOrder(temp.children[i]);
						
					}
				} 
			}
			System.out.print("(" + temp.getKey() + ", " + temp.getD(temp)+") ");
		}

}

