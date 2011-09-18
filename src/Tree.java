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
    public double getError(){
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
    public void Prune() {
    	
    	do {
    		//error without pruning
    		double default_error = getValidationError();
    		
    		PruneData best = whereToPrune(root); 
    		
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
    private PruneData whereToPrune(Node n){
    	
    	//Stop at leaf node
		if (n.m_lchild == null && n.m_rchild == null)
			return new PruneData(null, Double.MAX_VALUE);
		
		//prune at current node
		n.p_leafNode = true; 
		double error_current = getValidationError();
		n.p_leafNode = false; 
		
		//prune somewhere in left branch
		PruneData p_left = whereToPrune(n.m_lchild); 
		
		//prune somewhere in right branch
		PruneData p_right = whereToPrune(n.m_rchild); 
    	
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
    public String predictLabel(){
    	return null;
    }
    
}
