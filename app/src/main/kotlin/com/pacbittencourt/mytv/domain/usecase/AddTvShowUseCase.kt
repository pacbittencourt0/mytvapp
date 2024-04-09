package com.pacbittencourt.mytv.domain.usecase

import com.pacbittencourt.mytv.data.model.ShowModel
import com.pacbittencourt.mytv.data.repository.ShowRepository
import javax.inject.Inject

class AddTvShowUseCase @Inject constructor(
    private val showRepository: ShowRepository
) {
    suspend operator fun invoke(show: ShowModel) {
        showRepository.insertShowToWatch(show)
    }
}