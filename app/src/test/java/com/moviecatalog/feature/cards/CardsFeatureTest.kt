package com.moviecatalog.feature.cards

import com.moviecatalog.BuildConfig
import com.moviecatalog.feature.cards.api.CardsFeature
import com.moviecatalog.feature.cards.impl.di.cardsFeatureModule
import com.moviecatalog.core.network.impl.di.networkFeatureModule
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before

import org.junit.Test
import org.koin.core.KoinApplication
import org.koin.dsl.koinApplication

class CardsFeatureTest {

    private lateinit var koinApp: KoinApplication

    @Before
    fun setUp() {
        koinApp = koinApplication {
            modules(
                networkFeatureModule,
                cardsFeatureModule,
            )
        }
        require(BuildConfig.WB_TEST_API_KEY.isNotBlank()) {
            "WB_TEST_API_KEY should be defined in local.properties for smoke test"
        }
    }

    @After
    fun tearDown() {
        koinApp.close()
    }

    @Test
    fun `load cards`() = runBlocking {
        val feature: CardsFeature = koinApp.koin.get()

        val cards = feature.getCards()

        println(
            "====== cards: $cards"
        )
    }

}
