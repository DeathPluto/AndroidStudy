package com.halcyon.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.halcyon.R;
import com.halcyon.base.BaseFragment;
import com.halcyon.model.Student;
import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.view.ViewClickEvent;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * ©2015-2016 kcoin.inc.All Rights Reserved
 *
 * @author: L
 * Description:
 */
public class Fragment1 extends BaseFragment {
    LinearLayout mLinearLayout;
    String tag = "sample";

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_1);
        init();

    }


    /**
     * 设置button
     */
    private void init() {
        getViewById(R.id.subscribeInIOAndCallbackInUI).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subscribeInIOAndCallbackInUI();
            }
        });

        //绑定点击事件
        Button button = getViewById(R.id.btn);
        RxView.clickEvents(button) // 以 Observable 形式来反馈点击事件
                .throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribe(new Action1<ViewClickEvent>() {
                    @Override
                    public void call(ViewClickEvent event) {
                        // Click handling
                        TextView tv = getViewById(R.id.tv1);
                        Toast.makeText(getActivity(), "绑定成功", Toast.LENGTH_SHORT).show();
                        Random r = new Random();
                        tv.setText("防抖测试" + r.nextInt(500));
                        Log.e(tag, "绑定成功");
                    }
                });

        getViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                multiActionAfterSubscribe();
            }
        });

        getViewById(R.id.btn3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                from();
            }
        });


        getViewById(R.id.btn4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mapMethod();
            }
        });

        getViewById(R.id.btn5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inflateMapMethod();
            }
        });

        Observable.just(1, 2, 3, 4)
                .subscribeOn(Schedulers.io()) // 指定 subscribe() 发生在 IO 线程
                .observeOn(AndroidSchedulers.mainThread()) // 指定 Subscriber 的回调发生在主线程
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer number) {
                        Log.e(tag, "number:" + number);

                    }
                });


    }

    private ArrayList<Student.Course> getRandomCourse(){
        ArrayList<Student.Course> courses = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            courses.add(new Student.Course("课程"+new Random().nextInt(10)));
        }
        return courses;
    }


    private void inflateMapMethod() {
        Student[] students = new Student[]{
                new Student("0","张三",getRandomCourse()),
                new Student("1","李四",getRandomCourse()),
                new Student("2","王五",getRandomCourse()),
                new Student("3","赵六",getRandomCourse()),
                new Student("4","呵呵",getRandomCourse())
        };


        Subscriber<Student.Course> subscriber = new Subscriber<Student.Course>() {
            @Override
            public void onCompleted() {
                Toast.makeText(getActivity(), "onCompleted", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Student.Course course) {
                Toast.makeText(getActivity(), course.name, Toast.LENGTH_SHORT).show();
                Log.d(tag, course.name);
            }
        };
        Observable.from(students)
                .flatMap(new Func1<Student, Observable<Student.Course>>() {
                    @Override
                    public Observable<Student.Course> call(Student student) {
                        SystemClock.sleep(1000);
                        return Observable.from(student.courses);
                    }
                })
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);



    }

    /**
     * map转化  integer - > bitmap
     */
    private void mapMethod() {
        Observable.just(R.mipmap.ic_launcher)
                .map(new Func1<Integer, Bitmap>() {
                    @Override
                    public Bitmap call(Integer integer) {
                        SystemClock.sleep(1000);
                        return BitmapFactory.decodeResource(getResources(),integer);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Bitmap>() {
                    @Override
                    public void call(Bitmap bitmap) {
                        ImageView iv = getViewById(R.id.iv4);
                        iv.setImageBitmap(bitmap);
                    }
                });
    }

    private void from() {
        String[] names = new String[]{"a", "b", "c", "d"};
        Observable.from(names)
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String name) {
                        Log.d(tag, name);
                    }
                });
    }


    /**
     * io线程订阅,ui线程回调
     */
    private void subscribeInIOAndCallbackInUI() {
        final ImageView imageView = getViewById(R.id.iv);
        Observable.create(new Observable.OnSubscribe<Bitmap>() {
            @Override
            public void call(Subscriber<? super Bitmap> subscriber) {
                SystemClock.sleep(2000);
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
                subscriber.onNext(bitmap);
                subscriber.onCompleted();
            }
        })
                .subscribeOn(Schedulers.io()) // 指定 subscribe() 发生在 IO 线程
                .observeOn(AndroidSchedulers.mainThread()) // 指定 Subscriber 的回调发生在主线程
                .subscribe(new Observer<Bitmap>() {
                    @Override
                    public void onNext(Bitmap drawable) {
                        imageView.setImageBitmap(drawable);
                        Log.e(tag, "设置图片");
                    }

                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(getActivity(), "Error!", Toast.LENGTH_SHORT).show();
                    }
                });
    }


    /**
     * 订阅后多次行为 + io线程订阅,ui线程回调
     */
    private void multiActionAfterSubscribe() {
        Observable observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("Hello");
                SystemClock.sleep(1000);
                subscriber.onNext("Hi");
                SystemClock.sleep(1000);
                subscriber.onNext("Aloha");
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io()) // 指定 subscribe() 发生在 IO 线程
                .observeOn(AndroidSchedulers.mainThread()) ;// 指定 Subscriber 的回调发生在主线程
        final TextView tv2 = getViewById(R.id.tv2);
        Action1<String> onNextAction = new Action1<String>() {
            // onNext()
            @Override
            public void call(String s) {

                Log.d(tag, s);
                tv2.setText(s);
            }
        };
        Action1<Throwable> onErrorAction = new Action1<Throwable>() {
            // onError()
            @Override
            public void call(Throwable throwable) {
                // Error handling
            }
        };
        Action0 onCompletedAction = new Action0() {
            // onCompleted()
            @Override
            public void call() {
                Log.d(tag, "completed");
                Toast.makeText(getActivity(), "completed", Toast.LENGTH_SHORT).show();
            }
        };
        // 自动创建 Subscriber ，并使用 onNextAction 来定义 onNext()
//        observable.subscribe(onNextAction);
        // 自动创建 Subscriber ，并使用 onNextAction 和 onErrorAction 来定义 onNext() 和 onError()
//        observable.subscribe(onNextAction, onErrorAction);
        // 自动创建 Subscriber ，并使用 onNextAction、 onErrorAction 和 onCompletedAction 来定义 onNext()、 onError() 和 onCompleted()
        observable.subscribe(onNextAction, onErrorAction, onCompletedAction);
    }


}
