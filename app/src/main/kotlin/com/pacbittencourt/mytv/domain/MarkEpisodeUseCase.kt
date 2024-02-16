package com.pacbittencourt.mytv.domain

import com.pacbittencourt.mytv.data.repository.EpisodeRepository
import com.pacbittencourt.mytv.network.Dispatcher
import com.pacbittencourt.mytv.network.MyTvDispatchers.IO
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MarkEpisodeUseCase @Inject constructor(
    private val episodeRepository: EpisodeRepository,
    @Dispatcher(IO) private val ioDispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(showId: Int, episodeId: Int) {
        withContext(ioDispatcher) {
            episodeRepository.markEpisodeWatched(showId, episodeId)
        }
    }
}
