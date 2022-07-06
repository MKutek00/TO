import Action.Action;
import JRG.Car.Car;
import JRG.JRG;
import State.ActionState;
import Strategy.IStrategy;
import Strategy.StrategyThree;
import Strategy.StrategyTwo;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        List<JRG> jrgList = new ArrayList<>();
        List<Action> actionList = new ArrayList<>();
        List<Double> distances = new ArrayList<>();

        jrgList.add(new JRG( 19.94308,50.05995, "jrg 1"));
        jrgList.add(new JRG(19.93579,50.03341, "jrg 2"));
        jrgList.add(new JRG(19.88775,50.07559, "jrg 3"));
        jrgList.add(new JRG(20.00569,50.0377, "jrg 4"));
        jrgList.add(new JRG(19.92201,50.09224, "jrg 5"));
        jrgList.add(new JRG(20.0101,50.01612, "jrg 6"));
        jrgList.add(new JRG(19.97741,50.09407, "jrg 7"));
        jrgList.add(new JRG(20.03000,50.07675, "PSP"));
        jrgList.add(new JRG(19.79943,49.96842, "Skawina"));
        jrgList.add(new JRG(19.78613,50.07324, "Balice"));

        // Print information about JRG
        for (JRG jrg : jrgList){
            System.out.print("Name: " + jrg.getName() + ", numberOfFreeCars: " );
            jrg.getCarList().forEach(Car::write);
            System.out.println();
        }
        System.out.println();
        System.out.println();

        int ala = 100;
        while(ala > 0){
            // Create action
            Action action = new Action();
            System.out.println("Action coordinates: (" + action.getY() + ", " + action.getX() + "), state of action: " + action.getState().name());


            // Add action to action list
            actionList.add(action);

            // Add observers to actions
            for (JRG jrg : jrgList){
                action.registerObserver(jrg);
            }

            // Inform observers about action
            action.notifyObservers();

            // Set distance from action to closest JRG
            for (JRG jrg : jrgList){
                double jrgDistance = jrg.getDistance();
                distances.add(jrgDistance);
            }

            // Sorting map<String, Double> by values
            Collections.sort(distances);


            //Display sorted list
//        for(double d : distances){
//            System.out.println(d);
//        }

            if (action.getState().equals(ActionState.STATEMZ)){
                IStrategy strategy = new StrategyTwo();
                int number = strategy.execute();
                int k = 0;

                while (number != 0){
                    double minDistance = distances.get(k++);

                    for (JRG jrg : jrgList){
                        if (minDistance == jrg.getDistance()){
                            jrg.setFalseAlarm(action.isFalseAlarm());
                            jrg.setTime(action.getTime());
                            jrg.setStrategy(number);
                            number = jrg.getStrategyNumber();

                        }
                    }
                }
            }else{
                IStrategy strategy = new StrategyThree();
                int number = strategy.execute();
                int k = 0;

                while (number != 0){
                    double minDistance = distances.get(k++);

                    for (JRG jrg : jrgList){
                        if (minDistance == jrg.getDistance()){
                            jrg.setFalseAlarm(action.isFalseAlarm());
                            jrg.setTime(action.getTime());
                            jrg.setStrategy(number);
                            number = jrg.getStrategyNumber();

                        }
                    }
                }
            }


            System.out.println("-----------------------------------------------------");
            for (JRG jrg : jrgList){
                System.out.print("Name: " + jrg.getName() + ", numberOfFreeCars: " );
                jrg.getCarList().forEach(Car::write);
                System.out.println();
            }
            System.out.println();
            System.out.println();


            action.setState(ActionState.STATEDONE);

            // Check if action is DONE
            if(action.getState().equals(ActionState.STATEDONE)){
                for (JRG jrg : jrgList){
                    action.removeObserver(jrg);
                }
            }

            // Remove actions from action list if action is DONE
            actionList.removeIf(n -> (n.getState().equals(ActionState.STATEDONE)));


            try{
                Thread.sleep(2000);
            }catch (InterruptedException e){
                System.out.println(e.getMessage());
            }

            ala--;
        }

    }
}
