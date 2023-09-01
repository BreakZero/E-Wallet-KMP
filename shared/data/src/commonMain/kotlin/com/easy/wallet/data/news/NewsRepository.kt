package com.easy.wallet.data.news

import com.easy.wallet.data.news.model.BlockChairNewDto
import com.easy.wallet.data.news.model.mapper.externalModel
import com.easy.wallet.model.news.News
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class NewsRepository internal constructor(
    private val newsApi: NewsApi
) {
    fun loadNewsStream(limit: Int, offset: Int): Flow<List<News>> {
        return flow {
            emit(newsApi.loadNews(limit, offset).map(BlockChairNewDto::externalModel))
        }
    }

    suspend fun loadNews(limit: Int, offset: Int): List<News> {
        return newsApi.loadNews(limit, offset).map(BlockChairNewDto::externalModel)
    }

}