package com.example.samplecategoryapplication.di

import android.content.Context
import com.example.samplecategoryapplication.data.repositories.CategoryRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
/**
 * Created by Noushad N on 14-01-2022.
 */

@Module
object DataModule {
    @Singleton
    @Provides
    fun providesRepository(): CategoryRepository =
        CategoryRepository()

}