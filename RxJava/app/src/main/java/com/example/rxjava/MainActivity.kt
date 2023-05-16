package com.example.rxjava

import android.annotation.SuppressLint
import io.reactivex.rxjava3.core.Observable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.util.Objects
import java.util.Random
import java.util.concurrent.TimeUnit

private const val TAG = "ANKIT"


class MainActivity : AppCompatActivity() {
    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        Observable.just(1, 2, 3).subscribe { value -> Log.d(TAG, value.toString()) }

        val obs2 = Observable.fromIterable(listOf(10, 20, 30, 40, 50))
        obs2.subscribe { v -> Log.d(TAG, "Value is : $v") }

        val obs3 = Observable.create<Int> {
            it.onNext(100)
            it.onNext(200)
            it.onNext(300)
            it.onComplete()
        }
        obs3.subscribe(
            { v -> Log.d(TAG, "Value is : $v") },
            { err -> Log.d(TAG, err.toString()) },
            { Log.d(TAG, "Completed") }
        )

        val obs4 = Observable.just(10, 20, 30)
        val observer = object : Observer<Int> {
            override fun onSubscribe(d: Disposable) {
            }

            override fun onError(e: Throwable) {

            }

            override fun onComplete() {

            }

            override fun onNext(t: Int) {
                Log.d(TAG, "Value is : $t")
            }
        }
        obs4.subscribe(observer)

        val obs5 = Observable.just(-10,-20,-30).publish()
        obs5.subscribe{v -> Log.d("ANKIT", "Observable5 > Observer1 > $v")}
        obs5.connect()
        obs5.subscribe{v -> Log.d("ANKIT", "Observable5 > Observer2 > $v")}


        Observable.range(10,5).subscribe{v -> Log.d(TAG, "Value of range element is : $v")}

        var start = 10
        var count = 5
        val obs6 = Observable.defer {
            Log.d(TAG, "Observable is created....")
            Observable.range(start, count)
        }
        obs6.subscribe(observer)
        Log.d(TAG,"=======================")
        obs6.subscribe(observer)
        Log.d(TAG,"=======================")
        count = 2
        obs6.subscribe(observer)

        Observable.fromCallable {
            Log.d(TAG, "Creating own Observable")
            Random().nextInt()
        }

        Observable.fromCallable {
            Log.d(TAG, "Creating own Observable 2")
            Random().nextInt()
        }.subscribe()

        Observable.fromCallable {
            Log.d(TAG, "Creating own Observable 3")
            Random().nextInt() / 0
        }.subscribe({}, {e -> Log.d(TAG, e.localizedMessage)})


        val obs7 = Observable.interval(1, TimeUnit.SECONDS).publish()
        //obs7.connect()
        obs7.subscribe{e -> Log.d(TAG, "$e")}
        runBlocking {
            delay(2000)
        }
        //obs7.connect()
        obs7.subscribe{e -> Log.d(TAG, "$e")}
        finish()
    }
}