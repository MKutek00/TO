package pl.retsuz.shell.variations.mv;

import pl.retsuz.filesystem.Composite;
import pl.retsuz.filesystem.IComposite;
import pl.retsuz.shell.gen.ICommand;
import pl.retsuz.shell.variations.gen.CommandVariation;
import pl.retsuz.shell.variations.gen.ICommandVariation;

public class Mv_Path extends CommandVariation {
    public Mv_Path(ICommandVariation next, ICommand parent){
        super(next,parent,"[a-zA-Z0-9.l\\s\\/_]*");
    }


    @Override
    public void make(String params) {
        Composite c = (Composite) (this.getParent().getContext().getCurrent());

        String firstPath = params.substring(0, params.lastIndexOf(' '));
        String secondPath = params.substring(params.lastIndexOf(' ')+1);

        try{
            Composite elem1;
            Composite elem2;

            if (firstPath.contains("/")){
                String firstPathWithoutCatalogName = firstPath.substring(0, firstPath.lastIndexOf("/"));
                String catalogName = firstPath.substring(firstPath.lastIndexOf("/")+1);

                elem1 = (Composite)  c.findElementByPath(firstPathWithoutCatalogName);

                IComposite element3 = new Composite();
                element3.setName(catalogName);

                if (secondPath.equals("root")){
                    Composite.moveElement(elem1, c, element3);

                }else{
                    elem2 = (Composite) c.findElementByPath(secondPath);
                    Composite.moveElement(elem1, elem2, element3);
                }

            }else{

                elem1 = (Composite) c.findElementByPath(firstPath);
                if (firstPath.equals(secondPath)){
                    System.out.println("Sciezki sa rowne!");
                }else{
                    if (!secondPath.equals("root")){
                        elem2 = (Composite) c.findElementByPath(secondPath);
                        Composite.moveElement(c, elem2, elem1);
                    }
                }
            }

        }catch (Exception e){
            System.out.println("Nie można przeniesc katalogu");
        }
    }
}
