package com.xinbo.app.appbaselibrary.rxbus;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

/**
 * Created by LC on 2017/6/26.
 */

public class RxBus {
    private static RxBus defaultRxBus;
    private Subject<Object> bus;

    private RxBus() {
        bus = PublishSubject.create().toSerialized();
    }

    public static RxBus getInstance() {
        if (null == defaultRxBus) {
            synchronized (RxBus.class) {
                if (null == defaultRxBus) {
                    defaultRxBus = new RxBus();
                }
            }
        }
        return defaultRxBus;
    }

    public void post(Object o){
        bus.onNext(o);
    }

    public boolean hasObservable() {
        return bus.hasObservers();
    }
    /*
     * 转换为特定类型的Obserbale
     */
    public <T> Observable<T> toObservable(Class<T> type) {
        return bus.ofType(type);
    }
}
