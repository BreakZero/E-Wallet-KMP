package com.easy.wallet.database.dao

import com.easy.wallet.database.BlockChain
import com.easy.wallet.model.token.Token

interface BlockChainDao {
    suspend fun insert(blockChain: BlockChain)

    suspend fun allSupportedToken(): List<Token>

    suspend fun allSupportedTokenByChain(chainName: String)
}
