import AlgorithmSolving.ProblemSolver;
import P1_PROJECT.SpaceMarineManagement;

public class App_0 
{
    public static void main( String[] args )
    {
        ProblemSolver solver = new ProblemSolver();
        SpaceMarineManagement spaceMarineManagement = new SpaceMarineManagement();
        
        
        spaceMarineManagement.saveSpaceMarinesToFile();
    }
}
