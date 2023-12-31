package com.easy.wallet.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.text.isDigitsOnly
import androidx.navigation.compose.NavHost
import com.easy.wallet.discover.navigation.discoverTabScreen
import com.easy.wallet.home.navigation.HOME_GRAPH_ROUTE_PATTERN
import com.easy.wallet.home.navigation.homeGraph
import com.easy.wallet.home.navigation.toTransactionList
import com.easy.wallet.marketplace.navigation.marketplaceTabScreen
import com.easy.wallet.news.navigation.newsGraph
import com.easy.wallet.onboard.create.navigation.createGraph
import com.easy.wallet.onboard.create.navigation.toCreateWallet
import com.easy.wallet.onboard.restore.navigation.importWalletScreen
import com.easy.wallet.onboard.restore.navigation.toImportWallet
import com.easy.wallet.settings.navigation.settingsGraph
import com.easy.wallet.settings.navigation.toSettings
import com.easy.wallet.ui.WalletAppState

@Composable
fun WalletNavHost(
    appState: WalletAppState,
    onShowSnackbar: suspend (String, String?) -> Boolean,
    modifier: Modifier = Modifier,
    startDestination: String = HOME_GRAPH_ROUTE_PATTERN
) {
    val navController = appState.navController
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination,
    ) {
        homeGraph(
            onCreateWallet = navController::toCreateWallet,
            onRestoreWallet = navController::toImportWallet,
            navigateToSettings = navController::toSettings,
            onTokenClick = {
                navController.toTransactionList()
            },
            nestedGraphs = {},
        )
        createGraph(navController)
        importWalletScreen(navController)
        newsGraph(navController)
        marketplaceTabScreen()
        discoverTabScreen()
        settingsGraph(popBack = navController::popBackStack)
    }
}
