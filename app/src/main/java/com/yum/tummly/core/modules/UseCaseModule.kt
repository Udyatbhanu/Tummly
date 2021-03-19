package com.yum.tummly.core.modules

import com.yum.tummly.domain.recipe.SearchRecipesUseCase
import com.yum.tummly.domain.recipe.impl.SearchRecipesUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class UseCaseModule {
    @Binds
    abstract fun searchRecipes(searchRecipesUseCase: SearchRecipesUseCaseImpl): SearchRecipesUseCase
}