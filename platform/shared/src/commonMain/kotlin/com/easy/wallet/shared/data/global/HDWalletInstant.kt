package com.easy.wallet.shared.data.global

import com.trustwallet.core.HDWallet
import kotlinx.atomicfu.locks.SynchronizedObject
import kotlinx.atomicfu.locks.synchronized

class HDWalletInstant internal constructor() : SynchronizedObject() {
    private var hdWallet: HDWallet? = null
    private var mnemonic: String = ""

    fun loadInMemory(
        mnemonic: String,
        passphrase: String = ""
    ) {
        synchronized(this) {
            this.mnemonic = mnemonic
            hdWallet = HDWallet(mnemonic, passphrase)
        }
    }

    /**
     * when using this function, please add check exception
     */
    fun hdWallet() = synchronized(this) {
        checkNotNull(hdWallet)
        mnemonic
    }

    fun release() {
        synchronized(this) {
            hdWallet = null
        }
    }
}
