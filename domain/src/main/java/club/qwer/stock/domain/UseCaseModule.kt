package club.qwer.stock.domain

import club.qwer.stock.domain.usecase.GetRandomStockListUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
internal abstract class UseCaseModule {

    @Binds
    abstract fun getRandomStockListUseCaseBind(getRandomStockListUseCase: GetRandomStockListUseCase): GetRandomStockListUseCase
}