package com.easy.wallet.network.source.opensea

import com.easy.wallet.network.source.opensea.dto.NftListDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class OpenseaDataSource internal constructor(
    private val httpClient: HttpClient
) : OpenseaApi {
    override suspend fun retrieveNFTsByAccount(
        account: String,
        chain: String,
        limit: Int
    ): NftListDto {
        val nfts: NftListDto = httpClient.get("chain/$chain/account/$account/nfts?limit=$limit").body()
        return nfts
    }
}
