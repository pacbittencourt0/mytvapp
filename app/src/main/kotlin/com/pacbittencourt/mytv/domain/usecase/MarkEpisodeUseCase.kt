package com.pacbittencourt.mytv.domain.usecase

import com.pacbittencourt.mytv.data.repository.EpisodeRepository
import com.pacbittencourt.mytv.data.source.remote.Dispatcher
import com.pacbittencourt.mytv.data.source.remote.MyTvDispatchers.IO
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
