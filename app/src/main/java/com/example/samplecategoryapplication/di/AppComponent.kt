package com.example.samplecategoryapplication.di

import android.app.Application
import com.example.samplecategoryapplication.MyApplication

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton
/**
 * Created by Noushad N on 14-01-2022.
 */
@Singleton
@Component(modules = [ AndroidInjectionModule::class, ActivityModule::class,
    AppModule::class])
interface AppComponent : AndroidInjector<MyApplication> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    override fun inject(app: MyApplication)
}