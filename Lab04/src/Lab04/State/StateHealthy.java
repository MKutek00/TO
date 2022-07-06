package Lab04.State;

public class StateHealthy implements IState{

    @Override
    public String handle() {
        return "healthy";
    }
}
