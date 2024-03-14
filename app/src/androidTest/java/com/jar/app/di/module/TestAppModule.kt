package com.jar.app.di.module

import android.content.Context
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.jar.app.JarApp
import com.jar.app.base.di.module.AppModule
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import io.michaelrocks.libphonenumber.android.PhoneNumberUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import okhttp3.Dispatcher
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [AppModule::class]
)
class TestAppModule {

    @Provides
    @Singleton
    fun provideCrashlytics(): FirebaseCrashlytics {
        return FirebaseCrashlytics.getInstance()
    }

    @Provides
    @Singleton
    fun providePhoneNumberUtil(@ApplicationContext context: Context): PhoneNumberUtil {
        return PhoneNumberUtil.createInstance(context)
    }

    @Provides
    @Singleton
    fun provideAppScope(): CoroutineScope {
        return CoroutineScope(Dispatchers.Main)
    }
}