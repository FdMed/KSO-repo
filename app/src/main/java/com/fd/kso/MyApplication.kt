package com.fd.kso

import android.app.Application
import com.fd.kso.di.AppComponent
import com.fd.kso.di.DaggerAppComponent

open class MyApplication : Application() {
    open val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(applicationContext)
    }
}