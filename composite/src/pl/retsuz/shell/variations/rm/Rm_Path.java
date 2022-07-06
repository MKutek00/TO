package pl.retsuz.shell.variations.rm;

import pl.retsuz.filesystem.Composite;
import pl.retsuz.filesystem.IComposite;
import pl.retsuz.shell.gen.ICommand;
import pl.retsuz.shell.variations.gen.CommandVariation;
import pl.retsuz.shell.variations.gen.ICommandVariation;

public class Rm_Path extends CommandVariation {
    public Rm_Path(ICommandVariation next, ICommand parent){
        super(next,parent,"[a-zA-Z0-9.l\\/_]*");
    }


    @Override
    public void make(String params) {
        Composite c = (Composite) (this.getParent().getContext().getCurrent());

        try{
            if (!params.contains("/")){
                IComposite element = new Composite();
                element.setName(params);

//                List<IComposite> list = c.find(element);
//                c.removeElement(list.get(0));

                int j = 0;
                for (IComposite composite : c.find(element)){
                    if (composite.getName().equals(params)){
                        j++;
                        c.removeElement(composite);
                    }
                }
                if (j == 0){
                    System.out.println("Nie usunięto elementu.");
                }

            }else {
                String catalogName = params.substring(params.lastIndexOf('/') + 1);
                String first = params.substring(0, params.lastIndexOf('/'));

                Composite elem = (Composite) c.findElementByPath(first);

                if (Composite.class.isInstance(elem)){
                    IComposite element = new Composite();
                    element.setName(catalogName);

                    int j = 0;
                    for (IComposite composite : elem.find(element)){
                        if (composite.getName().equals(catalogName)){
                            j++;
                            elem.removeElement(composite);
                        }
                    }
                    if (j == 0){
                        System.out.println("Nie usunięto elementu.");
                    }
                }
            }
        }catch (Exception e){
            System.out.println("Nie można usunąć katalogu/pliku.");
        }
    }
}
