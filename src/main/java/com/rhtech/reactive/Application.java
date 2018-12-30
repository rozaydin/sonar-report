package com.rhtech.reactive;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


public class Application {

    public Application() {

    }

    public Observable<String> createObservable() {

        return Observable.create((observer) -> {
            observer.onNext("value-1");
            observer.onNext("value-2");
            observer.onNext("value-3");
            observer.onComplete();
        });
    }

    public Observer<String> createObserver() {

        return new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println(d.isDisposed());
                // d.dispose();
                System.out.println("Ihave subscribered!");
            }

            @Override
            public void onNext(String s) {
                System.out.println(s);

            }

            @Override
            public void onError(Throwable e) {
                System.out.println(e);

            }

            @Override
            public void onComplete() {
                System.out.println("Completed!");

            }
        };

    }

    public static void main(String[] args) {

        Application app = new Application();
        Observable<String> messages = app.createObservable();

        messages.subscribe(app.createObserver());
    }

}
