package pl.retsuz.shell.variations.mkdir;

import pl.retsuz.shell.gen.ICommand;
import pl.retsuz.shell.variations.gen.CommandVariation;
import pl.retsuz.shell.variations.gen.ICommandVariation;

public class MKDIR_def extends CommandVariation {
    public MKDIR_def(ICommandVariation next, ICommand parent){
        super(next, parent, "");
    }


    @Override
    public void make(String params) {
        System.out.println("Zbyt mala liczba parametrów!");
    }
}
