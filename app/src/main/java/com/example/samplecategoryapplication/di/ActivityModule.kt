package com.example.samplecategoryapplication.di

import com.example.samplecategoryapplication.ui.activities.category_create.CategoryCreateActivity
import com.example.samplecategoryapplication.ui.activities.category_list.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector
/**
 * Created by Noushad N on 14-01-2022.
 */
@Suppress("unused")
@Module
abstract class ActivityModule {
    @ContributesAndroidInjector
    abstract fun  contributeMainActivity() : MainActivity

    @ContributesAndroidInjector
    abstract fun  contributeCategoryDetailsActivity() : CategoryCreateActivity
}