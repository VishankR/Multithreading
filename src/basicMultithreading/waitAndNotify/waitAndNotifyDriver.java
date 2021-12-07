package basicMultithreading.waitAndNotify;

import basicMultithreading.threads.Receiver;
import basicMultithreading.threads.Sender;

public class waitAndNotifyDriver {
    public static void main(String[] args) {
        Data data = new Data();
        Thread sender = new Thread(new Sender(data));
        Thread receiver = new Thread(new Receiver(data));

        sender.start();
        receiver.start();
    }
}
