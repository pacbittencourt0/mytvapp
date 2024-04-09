package com.pacbittencourt.mytv.domain.usecase

import com.pacbittencourt.mytv.data.repository.EpisodeRepository
import com.pacbittencourt.mytv.data.source.remote.model.EpisodeResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetEpisodeUseCase @Inject constructor(
    val repository: EpisodeRepository
) {
    operator fun invoke(episodeId: Int): Flow<EpisodeResponse> {
        return repository.getEpisodeById(episodeId)
    }
}
