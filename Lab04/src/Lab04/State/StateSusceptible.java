package Lab04.State;

public class StateSusceptible implements IState{

    @Override
    public String handle() {
        return "susceptible";
    }
}
