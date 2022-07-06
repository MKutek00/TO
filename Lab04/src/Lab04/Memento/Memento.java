package Lab04.Memento;

import Lab04.State.IState;

public class Memento {
    private IState state;
    private IState state2;
    private IState state3;

    public Memento(IState state, IState state2, IState state3){
        this.state = state;
        this.state2 = state2;
        this.state3 = state3;
    }

    public IState getState() {
        return state;
    }

    public IState getState2() {
        return state2;
    }

    public IState getState3() {
        return state3;
    }

    public String writeState(){
        return state.handle() + ", " + state2.handle() + ", " + state3.handle();
    }
}
