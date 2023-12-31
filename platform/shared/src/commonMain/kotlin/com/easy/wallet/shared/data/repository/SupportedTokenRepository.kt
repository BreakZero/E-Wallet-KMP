package com.easy.wallet.shared.data.repository

import com.easy.wallet.database.dao.BlockChainDao
import com.easy.wallet.model.token.Token
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.experimental.ExperimentalObjCRefinement
import kotlin.native.HiddenFromObjC

@OptIn(ExperimentalObjCRefinement::class)
class SupportedTokenRepository internal constructor(
    private val blockChainDao: BlockChainDao
) {
    @HiddenFromObjC
    fun allSupportedTokenStream(): Flow<List<Token>> {
        return flow {
            emit(allSupportedToken())
        }
    }

    @HiddenFromObjC
    suspend fun allSupportedToken(): List<Token> {
        return blockChainDao.allSupportedToken()
    }
}
