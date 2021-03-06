package com.library.sample.di.modules

import com.library.base.di.ActivityScoped
import com.library.sample.di.AppComponent
import com.library.sample.ui.MainActivity
import com.library.sample.ui.MainActivityModule
import com.library.sample.ui.func.BillingModule
import com.library.sample.ui.main.MainFragmentModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * We want Dagger.Android to create a Subcomponent which has a parent Component of whichever module
 * ActivityBindingModule is on, in our case that will be [AppComponent]. You never
 * need to tell [AppComponent] that it is going to have all these subcomponents
 * nor do you need to tell these subcomponents that [AppComponent] exists.
 * We are also telling Dagger.Android that this generated SubComponent needs to include the
 * specified modules and be aware of a scope annotation [@ActivityScoped].
 * When Dagger.Android annotation processor runs it will create 2 subcomponents for us.
 */
@Module
@Suppress("UNUSED")
abstract class ActivityBindingModule {
    @ActivityScoped
    @ContributesAndroidInjector(
        modules = [
            // activity
            MainActivityModule::class,
            // fragments
            MainFragmentModule::class,
            BillingModule::class
        ]
    )
    internal abstract fun mainActivity(): MainActivity
}