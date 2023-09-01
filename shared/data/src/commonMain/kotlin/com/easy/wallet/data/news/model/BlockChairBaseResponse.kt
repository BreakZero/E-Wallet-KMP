package com.easy.wallet.data.news.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class BlockChairBaseResponse<T>(
    @SerialName("data")
    val data: T,
    @SerialName("context")
    val context: BlockChairContextDto
)
