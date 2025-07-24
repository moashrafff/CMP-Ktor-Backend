package org.moashrafff.showcase

import androidx.compose.ui.window.ComposeUIViewController
import org.moashrafff.showcase.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = { initKoin() }
){ App() }