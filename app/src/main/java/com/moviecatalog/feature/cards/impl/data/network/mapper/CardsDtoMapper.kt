package com.moviecatalog.feature.cards.impl.data.network.mapper

import com.moviecatalog.feature.cards.api.entity.Card
import com.moviecatalog.feature.cards.impl.data.network.dto.CardsResponseDto
import com.moviecatalog.feature.cards.impl.data.network.dto.CursorResultDto
import com.moviecatalog.feature.cards.impl.domain.CardsResult
import com.moviecatalog.feature.cards.impl.domain.CursorResult

internal class CardsDtoMapper {

    fun map(cardsResponseDto: CardsResponseDto) : CardsResult {
        return CardsResult(
            cards = cardsResponseDto.cards.map { Card(it.nmID) },
            cursor = mapCursor(cardsResponseDto.cursor)
        )
    }

    private fun mapCursor(cursorResultDto: CursorResultDto) : CursorResult {
        return CursorResult(
            nmID = cursorResultDto.nmID,
            total = cursorResultDto.total
        )
    }
}