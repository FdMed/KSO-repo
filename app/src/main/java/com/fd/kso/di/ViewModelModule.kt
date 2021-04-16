package com.fd.kso.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fd.kso.ui.ViewModelFactory
import com.fd.kso.ui.main.MainViewmodel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewmodel::class)
    abstract fun bindsMainViewModel(viewModel: MainViewmodel): ViewModel

    abstract fun bindsViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

}