package com.example.samplecategoryapplication.di

import com.example.samplecategoryapplication.di.DataModule
import dagger.Module

/**
 * Created by Noushad N on 14-01-2022.
 */
@Module(
    includes = [
        ViewModelModule::class,
        DataModule::class
    ]
)
class AppModule