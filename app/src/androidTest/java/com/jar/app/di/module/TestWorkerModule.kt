package com.jar.app.di.module

import android.content.Context
import androidx.work.WorkManager
import com.jar.app.base.di.module.WorkerModule
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [WorkerModule::class]
)
class TestWorkerModule {

    @Provides
    @Singleton
    fun provideWorkManger(@ApplicationContext context: Context): WorkManager {
        return WorkManager.getInstance(context)
    }
}