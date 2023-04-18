package com.cheezycode.daggerexample

import dagger.Module
import dagger.Provides

@Module
class UserRepositoryModule {
    @Provides
    fun getUserRepository(): UserRepository{
        return LocalRepository()
    }
}