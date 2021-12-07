package basicMultithreading;

public abstract  class Counter {
    public abstract boolean incrementCounter1() throws InterruptedException;
    public abstract boolean incrementCounter2() throws InterruptedException;
    public abstract boolean incrementCounter3() throws InterruptedException;
}
