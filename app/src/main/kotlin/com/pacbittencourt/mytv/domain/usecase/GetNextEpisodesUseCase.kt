package com.pacbittencourt.mytv.domain.usecase

import com.pacbittencourt.mytv.data.model.NextEpisodeModel
import com.pacbittencourt.mytv.data.repository.NextEpisodeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNextEpisodesUseCase @Inject constructor(
    private val nextEpisodeRepository: NextEpisodeRepository
) {
    operator fun invoke(): Flow<List<NextEpisodeModel>> {
        return nextEpisodeRepository.getNextEpisodeList()
    }
}