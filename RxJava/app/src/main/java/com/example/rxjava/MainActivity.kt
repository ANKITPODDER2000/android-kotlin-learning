package com.example.rxjava

import android.annotation.SuppressLint
import io.reactivex.rxjava3.core.Observable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
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

        val o1 = Observable.just("Hello", "World", "from", "Java").subscribeOn(Schedulers.io())
        val o2 = Observable.fromIterable(listOf("Hello", "World", "from", "Java"))
        val o3 = Observable.create<Int> {
            for (i in 0 until 10) {
                if (!it.isDisposed) it.onNext(i + 1)
            }
            if (!it.isDisposed) it.onComplete()
        }

        /*Time to subscribe above observers...*/
        val subscriberFunc: (String, String) -> Unit =
            { it: String, TAG: String ->
                Log.d(
                    "MainActivityRxJava",
                    "$TAG Value of it is : $it | Thread : ${Thread.currentThread().name}"
                )
            }

        o1.subscribe { subscriberFunc(it, "Observer1") }
        o2.subscribeOn(Schedulers.newThread()).subscribe { subscriberFunc(it, "Observer2") }
        o3.subscribe { subscriberFunc(it.toString(), "Observer3") }

        Observable.range(1, 20).skip(10).take(5).map { "${it * it}" }
            .subscribe { subscriberFunc(it, "Observer 4") }


    }
}