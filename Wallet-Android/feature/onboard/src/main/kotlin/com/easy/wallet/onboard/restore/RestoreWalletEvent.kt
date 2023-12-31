package com.easy.wallet.onboard.restore

internal sealed interface RestoreWalletEvent {
    data class SeedChanged(val seed: String) : RestoreWalletEvent
    data class PasswordChanged(val password: String) : RestoreWalletEvent
    data class ConfirmPasswordChanged(val confirmPassword: String) : RestoreWalletEvent

    data object OnImport : RestoreWalletEvent
}
