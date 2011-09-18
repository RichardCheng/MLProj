public class NodeFactory {

    public static Criterion m_currCriterion = null;
    public static int m_currStoppingParam = -1;

    public static void changeCriterion(Criterion criterion) {
        m_currCriterion = criterion;
    }

    public static void changeStoppingParam(int stoppingParam) {
        m_currStoppingParam = stoppingParam;
    }

    public static Node returnNode() throws Exception {
        if (m_currCriterion == null || m_currStoppingParam == -1) 
            throw new Exception("Need to initalize factory");

        return new Node(m_currCriterion, m_currStoppingParam);
    }
}
