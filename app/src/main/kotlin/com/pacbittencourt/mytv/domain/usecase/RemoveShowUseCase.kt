package com.pacbittencourt.mytv.domain.usecase

import com.pacbittencourt.mytv.data.repository.ShowRepository
import javax.inject.Inject

class RemoveShowUseCase @Inject constructor(
    private val repository: ShowRepository
) {
    suspend operator fun invoke(showId: Int) {
        repository.removeShowFromWatch(showId)
    }
}