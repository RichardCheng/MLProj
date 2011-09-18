import java.util.ArrayList;


public class Tree {
    public Node root;
    
    public void growTree(ArrayList<Entry> data) throws Exception {
    	root = NodeFactory.returnNode();
    	
    	root.m_entries = data;
    	root.Split();
    }
    
    //TODO
    public double getValidationError(){
    	return -1; 
    }

    //TODO: implement
    public int getError(ArrayList<Entry> testLst){
    	return -1; 
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
    	
    	do {
    		//error without pruning
    		double default_error = getError(validationLst);
    		
    		PruneData best = whereToPrune(root, validationLst); 
    		
    		//if root == leaf (edge case)
    		if (best.n == null)
    			break;
    		//best to not prune
    		else if (best.error > default_error)
    			break; 
    		//prune
    		else{
    			best.n.m_lchild = null; 
    			best.n.m_rchild = null; 
    			best.n.p_leafNode = true; 
    		}
    		
    	} while (true); 
    	
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

    //TODO
    public int predictLabel(Entry predictee){
    	
    	Node currentNode = root; 
    	
    	while (true) {
    		
    		//if (currentNode.p_leafNode )
    		
    	    //int splitting_feature = currentNode.m_splitting_feature;
    	   // int splitting_value = currentNode.m_splitting_value;
    	    
    	    //if (predictee.features[splitting_feature] <= splitting_value)
    	}
    	
    	
    	//return -1;
    }
    
}
