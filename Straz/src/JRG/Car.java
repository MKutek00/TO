package JRG;

public class Car {

    private int x;
    private int y;

    private boolean available = true;

    private int dx = 1;
    private int dy = 1;

    public Car(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void moveCar(){
        x += dx;
        y += dy;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
