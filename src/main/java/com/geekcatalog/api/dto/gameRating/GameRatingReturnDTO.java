package com.geekcatalog.api.dto.gameRating;

import com.geekcatalog.api.domain.gameRating.GameRating;

public record GameRatingReturnDTO(
        String id,
        String userId,
        String gameId,
        Integer rating
) {
    public GameRatingReturnDTO(GameRating gameRating) {
        this(
                gameRating.getId(),
                gameRating.getUser() != null ? gameRating.getUser().getId() : null,
                gameRating.getGame() != null ? gameRating.getGame().getId() : null,
                gameRating.getRating()
        );
    }
}
