package com.example.samplecategoryapplication.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.samplecategoryapplication.ViewModelFactory
import com.example.samplecategoryapplication.ui.activities.category_create.CategoryCreateViewModel
import com.example.samplecategoryapplication.ui.activities.category_list.CategoryListViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass
/**
 * Created by Noushad N on 14-01-2022.
 */
@Suppress("unused")
@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(CategoryListViewModel::class)
    abstract fun bindCategoryListViewModel(categoryListViewModel: CategoryListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CategoryCreateViewModel::class)
    abstract fun bindCategoryDetailsViewModel(categoryCreateViewModel: CategoryCreateViewModel): ViewModel



    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}

@MustBeDocumented
@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)
