package com.yum.tummly.core.modules

import com.yum.tummly.data.api.RecipeApi
import com.yum.tummly.data.repository.RecipeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class RepositoryModule {

    @Provides
    @ViewModelScoped
    fun provideRestaurantsRepository(api : RecipeApi) : RecipeRepository {
        return RecipeRepository(api)
    }
}