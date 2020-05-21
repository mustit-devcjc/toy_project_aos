package dev.chu.toyapp.di

import dagger.BindsInstance
import dagger.Component
import dev.chu.toyapp.GlobalApplication
import javax.inject.Singleton

@Component(modules = [AppModule::class])
@Singleton
interface AppComponent {


    fun inject(app: GlobalApplication)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: GlobalApplication, appModule: AppModule)
    }

}