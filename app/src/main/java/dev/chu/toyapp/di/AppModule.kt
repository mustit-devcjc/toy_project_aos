package dev.chu.toyapp.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import dev.chu.toyapp.GlobalApplication
import javax.inject.Singleton

@Module
@Singleton
class AppModule {

    @Provides
    @Singleton
    fun provideAppContext(app: GlobalApplication): Context = app

    @Provides
    @Singleton
    fun provideApplication(app: GlobalApplication): Application = app
}
