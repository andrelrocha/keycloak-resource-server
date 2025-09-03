package com.geekcatalog.api.dto.gameRating;

import com.geekcatalog.api.domain.gameRating.GameRating;
import com.geekcatalog.api.dto.game.GameReturnDTO;
import com.geekcatalog.api.dto.user.UserReturnDTO;

public record GameRatingReturnDTO(
        UserReturnDTO user,
        GameReturnDTO game,
        Integer rating
) {
    public GameRatingReturnDTO(GameRating gameRating) {
        this(
                gameRating.getUser() != null ? new UserReturnDTO(gameRating.getUser()) : null,
                gameRating.getGame() != null ? new GameReturnDTO(gameRating.getGame()) : null,
                gameRating.getRating()
        );
    }
}
