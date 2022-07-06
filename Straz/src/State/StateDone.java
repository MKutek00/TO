package State;

public class StateDone implements IState{

    @Override
    public String handle() {
        return "DONE";
    }
}
