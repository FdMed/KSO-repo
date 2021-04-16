package com.fd.kso.di

import android.content.Context
import com.fd.kso.ui.MainActivity
import com.fd.kso.ui.main.MainFragment
import dagger.BindsInstance
import dagger.Component

@Component(modules = [NetworkModule::class, ViewModelModule::class])
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(activity: MainActivity)
    fun inject(fragment: MainFragment)
}