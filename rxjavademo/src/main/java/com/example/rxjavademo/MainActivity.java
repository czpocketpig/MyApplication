package com.example.rxjavademo;

import android.support.annotation.MainThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.operators.flowable.FlowableWindow;
import io.reactivex.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Flowable<String> flowable = Flowable.create(new FlowableOnSubscribe<String>() {
//            @Override
//            public void subscribe(FlowableEmitter<String> e) throws Exception {
//                e.onNext("hellow rxjava 2");
//                e.onComplete();
//
//            }
//        }, BackpressureStrategy.BUFFER);
//
//        Subscriber subscriber=new Subscriber<String>() {
//            @Override
//            public void onSubscribe(Subscription s) {
//                System.out.println("onSubscribe");
//                s.request(Long.MAX_VALUE);
//            }
//
//            @Override
//            public void onNext(String s) {
//                System.out.println(s);
//            }
//
//            @Override
//            public void onError(Throwable t) {
//
//            }
//
//            @Override
//            public void onComplete() {
//                System.out.println("onComplete");
//            }
//        };
//          flowable.subscribe(subscriber);
//
//
//
//       Observable.create(new ObservableOnSubscribe<Integer>() {
//           @Override
//           public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
//               Log.d(TAG, "emit 1");
//               emitter.onNext(1);
//               Log.d(TAG, "emit 2");
//               emitter.onNext(2);
//               Log.d(TAG, "emit 3");
//               emitter.onNext(3);
//               Log.d(TAG, "emit complete");
//               emitter.onComplete();
//               Log.d(TAG, "emit 4");
//               emitter.onNext(4);
//           }
//       }).subscribe(new Observer<Integer>() {
//           @Override
//           public void onSubscribe(Disposable d) {
//               Log.d(TAG, "subscribe");
//           }
//           @Override
//           public void onNext(Integer value) {
//               Log.d(TAG, "" + value);
//           }
//           @Override
//           public void onError(Throwable e) {
//               Log.d(TAG, "error");
//           }
//           @Override
//           public void onComplete() {
//               Log.d(TAG, "complete");
//           }
//       });

        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                Log.d(TAG, "Observable thread is : " + Thread.currentThread().getName());
                Log.d(TAG, "emit 1");
                emitter.onNext(1);
            }
        });

        Consumer<Integer> consumer = new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.d(TAG, "Observer thread is :" + Thread.currentThread().getName());
                Log.d(TAG, "onNext: " + integer);
            }
        };

//        observable.subscribeOn(Schedulers.newThread())
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .observeOn(Schedulers.io())
//                .subscribe(consumer);


        observable.subscribeOn(Schedulers.newThread())
                  .subscribeOn(Schedulers.io())
                  .observeOn(AndroidSchedulers.mainThread())
                  .doOnNext(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.d(TAG, "After observeOn(mainThread), current thread is: " + Thread.currentThread().getName());
            }
        }).observeOn(Schedulers.io()).doOnNext(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.d(TAG, "After observeOn(io), current thread is : " + Thread.currentThread().getName());
            }
        }).subscribe(consumer);


    }
}
