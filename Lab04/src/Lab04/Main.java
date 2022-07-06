package Lab04;

import Lab04.Frame.MyFrame;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = 0, n = 0;
        int numberOfPopulation = 0;


        while(true){
            if (n < 100){
                System.out.print("Enter width: ");
                n  = scanner.nextInt();
            }
            if (m < 100){
                System.out.print("Enter height: ");
                m = scanner.nextInt();
            }

            if (numberOfPopulation <= 0 || numberOfPopulation > 100){
                System.out.println("Enter the numberOfPopulation: ");
                numberOfPopulation = scanner.nextInt();
            }

            if (n >= 100 && m >= 100 && (numberOfPopulation <= 100 && numberOfPopulation > 0)){
                new MyFrame(n, m, numberOfPopulation).setVisible(true);
                break;
            }else{
                System.out.println("Too small parameters, I can't create frame");
            }
        }

        scanner.close();

    }
}
