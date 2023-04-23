package com.cheezycode.daggerexample

import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class UserRepositoryModule {
    @Named("fb")
    @Provides
    fun getFireBaseRepository(): UserRepository{
        return FireBaseRepository()
    }

    @Named("localdb")
    @Provides
    fun getLocalRepository(): UserRepository{
        return LocalRepository()
    }
}