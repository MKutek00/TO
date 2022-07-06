package JRG;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class JRG {

    private ArrayList<Car> carList = new ArrayList<>();

    private final double Y1 = 50.154564013341734;
    private final double Y2 = 49.95855025648944;
    private final double X1 = 19.688292482742394;
//    private final double X2 = 20.04070275868903;
    private final double X2 = 20.02470275868903;

    private final double x;
    private final double y;

    private String text;

    private double boardX;
    private double boardY;
    private int pixelX = 0;
    private int pixelY = 0;
    private int widthOfRec = 5;
    private int heightOfRec = 5;

    public JRG(double y, double x, JPanel panel, String text){
        this.x = x;
        this.y = y;
        this.text = text;

        Rectangle rectangle = panel.getBounds();
        boardX = X1;
        boardY = Y1;

        double width = X2 - X1;
        double height = Y1 - Y2;
        double rectangleWidth =  rectangle.getMaxX() -  rectangle.getMinX();
        double rectangleHeight =  rectangle.getMaxY() -  rectangle.getMinY();
        double trueX = width / (rectangleWidth-35);
        double trueY = height / (rectangleHeight-10);

        for (int i = (int) rectangle.getMinX(); i <= (int) rectangle.getMaxX(); i++){
            if (boardX < this.x){
                boardX += trueX;
                pixelX++;
            }
        }
        for (int j = (int) rectangle.getMinY(); j <= (int) rectangle.getMaxY(); j++){
            if (boardY > this.y){
                boardY -= trueY;
                pixelY++;
            }
        }
    }

    public boolean checkIfIsClose(int number1, int number2){
        if (Math.abs(pixelX - number1) >= 10 && Math.abs(pixelY - number2) >= 10){
            return true;
        }
        return false;
    }

    public int getPixelX() {
        return pixelX;
    }

    public int getPixelY() {
        return pixelY;
    }

    public int getWidthOfRec() {
        return widthOfRec;
    }

    public int getHeightOfRec() {
        return heightOfRec;
    }

    public String getText() {
        return text;
    }

    public ArrayList<Car> getCarList() {
        return carList;
    }

    public void addCar(Car car){
        this.carList.add(car);
    }

    public Car getLastCar(){
        return this.carList.get(carList.size()-1);
    }

    public Car getCar(int i){
        return carList.get(i);
    }

}
