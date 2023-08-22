package com.easy.wallet

import android.app.Application
import com.easy.wallet.home.di.homeModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class WalletApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(homeModule)
        }
    }
}