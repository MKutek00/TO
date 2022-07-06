package pl.retsuz.shell.variations.mkdir;

import pl.retsuz.filesystem.Composite;
import pl.retsuz.filesystem.IComposite;
import pl.retsuz.shell.gen.ICommand;
import pl.retsuz.shell.variations.gen.CommandVariation;
import pl.retsuz.shell.variations.gen.ICommandVariation;


public class MKDIR_Path extends CommandVariation {

    public MKDIR_Path(ICommandVariation next, ICommand parent){
        super(next,parent,"[a-zA-Z0-9.l\\/_]*");
    }

    @Override
    public void make(String params) {

        Composite c = (Composite) (this.getParent().getContext().getCurrent());
        try{
            IComposite element = new Composite();

            if(!params.contains("/")){
                element.setName(params);
                c.addElement(element);
            }else{
                String catalogName = params.substring(params.lastIndexOf('/') + 1);
                String first = params.substring(0, params.lastIndexOf('/'));

                Composite elem = (Composite) c.findElementByPath(first);

                if (Composite.class.isInstance(elem)){
                    element.setName(catalogName);
                    elem.addElement(element);
                }
            }

        }catch (Exception e){
            System.out.println("Nie można utworzyc katalogu");
        }
    }
}
