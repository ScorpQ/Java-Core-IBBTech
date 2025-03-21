import AlgorithmSolving.ProblemSolver;
import P1_PROJECT.dao.SpaceMarineDao;

public class App_0 
{
    public static void main( String[] args )
    {
        ProblemSolver solver = new ProblemSolver();
        SpaceMarineDao spaceMarineManagement = new SpaceMarineDao();
        spaceMarineManagement.choose();
    }
}
