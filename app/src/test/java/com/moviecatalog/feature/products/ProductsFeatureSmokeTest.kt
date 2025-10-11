package com.moviecatalog.feature.products

import com.moviecatalog.BuildConfig
import com.moviecatalog.feature.network.impl.di.networkFeatureModule
import com.moviecatalog.feature.products.api.ProductFeature
import com.moviecatalog.feature.products.impl.di.productFeatureModule
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertFalse
import org.junit.Before

import org.junit.Test
import org.koin.core.KoinApplication
import org.koin.dsl.koinApplication

/**
 * Simple integration smoke test to verify network pipeline wiring.
 * Requires WB_TEST_API_KEY defined in local.properties.
 */
class ProductsFeatureSmokeTest {

    private lateinit var koinApp: KoinApplication

    @Before
    fun setUp() {
        koinApp = koinApplication {
            modules(
                networkFeatureModule,
                productFeatureModule
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
    fun `load product dictionaries end-to-end`() = runBlocking {
        val feature: ProductFeature = koinApp.koin.get()

        val locale = "ru"

        val parents = feature.getProductParents(locale = locale)
        assertFalse("Expected non-empty parents list", parents.isEmpty())
        delay(1200)

        val subjects = feature.getProductSubjects(
            locale = locale,
            limit = 10,
            offset = 0,
        )
        assertFalse("Expected non-empty subjects list", subjects.isEmpty())
        delay(1200)
        val firstSubjectId = subjects.first().subjectID
        val characteristics = feature.getProductCharacteristics(
            subjectId = firstSubjectId,
            locale = locale,
        )

        println(
            "Loaded parents: ${parents.size}, subjects: ${subjects.size}, characteristics: ${characteristics.size}"
        )
    }

    @Test
    fun `load cards`() = runBlocking {
        val feature: ProductFeature = koinApp.koin.get()

        val cards = feature.getCards()

        println(
            "cards: $cards"
        )
    }

}
