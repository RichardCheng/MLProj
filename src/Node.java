import java.util.ArrayList;


public class Node<LabelType, FeatureType> {

    public Criterion<LabelType> m_splitting_criterion;
    public ArrayList<Entry<LabelType, FeatureType>> m_entries;
    public Node<LabelType, FeatureType> m_lchild;
    public Node<LabelType, FeatureType> m_rchild;
    public String m_type;

    public int m_stoppingParam;

    public int m_splitting_feature;
    public int m_splitting_value;

	public Node(Criterion<LabelType> criterion, int stoppingParam) {
        m_splitting_criterion = criterion;
        m_stoppingParam = stoppingParam;
    }
	
    /// Split the current tree into left/right children
    // Set the splitting feature and values and create l/r child
    public void Split() {
        m_type = null;
    }
	
    /// Find the type of this node(i.e., find all children/decedents 
    // of this node, and set type to the majority.
    public void CalculateType() {
        
    }

}
