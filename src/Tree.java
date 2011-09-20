import java.util.ArrayList;


public class Tree {
    public Node root;
    
    public void growTree(ArrayList<Entry> data) throws Exception {
    	root = NodeFactory.returnNode();
    	root.m_entries = data;
    	root.split();
    }
    
    public int nodeCount() {
    	return root.nodeCount(); 
    }
    
    //predicts the label of a predictee using the tree
    public int predictLabel(Entry predictee){
    	
    	Node currentNode = root; 
    	
    	while (!currentNode.p_leafNode) {
    	    int splitting_feature = currentNode.m_splitting_feature;
    	    int splitting_value = currentNode.m_splitting_value;
    	    
    	    if (predictee.features[splitting_feature] <= splitting_value)
    	    	currentNode = currentNode.m_lchild; 
    	    else
    	    	currentNode = currentNode.m_rchild; 
    	}
    	
    	return currentNode.m_label;
    }

    //count how many errors are made using a set of entries (this could be the validation set or test set)
    public double getError(ArrayList<Entry> testLst){
    	double errorNumber = 0; 
    	for (Entry e : testLst){
    		if (predictLabel(e) != e.label)
    			errorNumber++; 
    	}
    	return errorNumber/testLst.size(); 
    }
    
    private class PruneData {
    	public Node n; 
    	public double error; 
    	
    	public PruneData(Node n, double error){
    		this.n = n; 
    		this.error = error; 
    	}
    }
    
    //prune at the node that minimizes error
    public void Prune(ArrayList<Entry> validationLst) {
    	
    	while (true) {
    		//error without pruning
    		double default_error = getError(validationLst);
    		
    		PruneData best = whereToPrune(root, validationLst); 
    		
    		//if root == leaf (edge case)
    		if (best.n == null)
    			break;
    		//best to not prune
    		else if (best.error >= default_error)
    			break; 
    		//prune
    		else {
    			best.n.m_lchild = null; 
    			best.n.m_rchild = null; 
    			best.n.p_leafNode = true; 
    		}
    	}
    }
    
    //find the best place to prune (node that minimizes error)
    public PruneData whereToPrune(Node n, ArrayList<Entry> validationLst){
    	
    	//Stop at leaf node
		if (n.m_lchild == null && n.m_rchild == null)
			return new PruneData(null, Double.MAX_VALUE);
		
		//prune at current node
		n.p_leafNode = true; 
		double error_current = getError(validationLst);
		n.p_leafNode = false; 
		
		//prune somewhere in left branch
		PruneData p_left = whereToPrune(n.m_lchild, validationLst); 
		
		//prune somewhere in right branch
		PruneData p_right = whereToPrune(n.m_rchild,validationLst); 
    	
		//find best place to prune
		PruneData best; 
		if (error_current > p_left.error){
			if (p_left.error > p_right.error)
				best = p_right; 
			else
				best = p_left; 
		} else {
			if (error_current > p_right.error)
				best = p_right; 
			else 
				best = new PruneData(n, error_current); 
		}
		
		return best; 
    }

    private void printNode(Node e, int indentation) {
    	
    	for (int i = 0; i < indentation; ++i) {
    		System.out.printf("\t");
    	}
    	
    	if (e.p_leafNode) {
    		System.out.printf("Classify as %d with [", e.m_label);
    		for (Entry j : e.m_entries) {
    			System.out.printf("%d ", j.label);
    		}
    		System.out.println("]");
    	}
    	else {
    		System.out.printf("Split on feature %d, at threshold = %d\n",
    				e.m_splitting_feature, e.m_splitting_value);
    		printNode(e.m_lchild, indentation+1);
    		printNode(e.m_rchild, indentation+1);
    	}
    }
    
    public void printTree() {
    	
    	printNode(root, 0);
    }
}
