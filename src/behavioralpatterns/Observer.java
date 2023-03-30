package behavioralpatterns;

import java.util.ArrayList;
import java.util.List;

/**
 * Observer is a behavioral design pattern that lets you define a subscription mechanism to notify
 * multiple objects about any events that happen to the object they’re observing.
 */
public class Observer {
    public static void main(String[] args) {
    }

    private interface IObserverListener<T> {
        void onNext(T value);
    }

    private interface IPublisher<T> {
        void subscribe(IObserverListener<T> observerListener);

        boolean unsubscribe(IObserverListener<T> observerListener);

        void submit(T element);
    }

    private static final class Publisher<T> implements IPublisher<T> {
        private final List<IObserverListener<T>> observers;

        public Publisher() {
            this.observers = new ArrayList<>();
        }

        @Override
        public void subscribe(IObserverListener<T> observerListener) {
            observers.add(observerListener);
        }

        @Override
        public boolean unsubscribe(IObserverListener<T> observerListener) {
            try {
                return observers.remove(observerListener);
            } catch (Exception exception) {
                exception.printStackTrace();
                return false;
            }
        }

        @Override
        public void submit(T element) {
            for (IObserverListener<T> observer : observers) {
                observer.onNext(element);
            }
        }
    }
}
