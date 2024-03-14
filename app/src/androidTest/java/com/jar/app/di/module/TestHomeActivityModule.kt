package com.jar.app.di.module

import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import com.jar.app.base.di.module.HomeActivityModule
import com.jar.app.feature.home.ui.activity.HomeActivity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn

@Module
@TestInstallIn(
    components = [ActivityComponent::class],
    replaces = [HomeActivityModule::class]
)object TestHomeActivityModule {

    @Provides
    @ActivityScoped
    fun provideNavController(activity: FragmentActivity): NavController {
        return (activity as HomeActivity).navController
    }
}