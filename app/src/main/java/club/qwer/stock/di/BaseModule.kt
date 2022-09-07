package club.qwer.stock.di

import android.content.Context
import club.qwer.stock.base.StringResource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class BaseModule {

    @Provides
    @Singleton
    fun provideStringResource(@ApplicationContext context: Context) = StringResource(context)
}