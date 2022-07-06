package Observer;

public interface ISubject {
    void registerObserver(IObserver observer);
    void notifyObservers();
    void removeObserver(IObserver observer);
}
