package mvc;

import java.util.ArrayList;

public class Publisher {

    ArrayList<Subscriber> subscriberList = new ArrayList<>();

    public void notifySubscribers() {
        for (Subscriber s : subscriberList) {
            s.update();
        }
    }

    public void subscribe(Subscriber s) {
        this.subscriberList.add(s);
    }

    public void unsubscribe(Subscriber s) {
        this.subscriberList.remove(s);
    }
}
