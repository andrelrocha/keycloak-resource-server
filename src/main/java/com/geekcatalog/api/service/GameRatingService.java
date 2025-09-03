package com.geekcatalog.api.service;

import com.geekcatalog.api.domain.gameRating.useCase.AddOrUpdateGameRating;
import com.geekcatalog.api.domain.gameRating.useCase.GetGameRatings;
import com.geekcatalog.api.dto.gameRating.GameRatingDTO;
import com.geekcatalog.api.dto.gameRating.GameRatingResultDTO;
import com.geekcatalog.api.dto.gameRating.GameRatingReturnDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GameRatingService {
    private final AddOrUpdateGameRating addOrUpdateGameRating;
    private final GetGameRatings getGameRatings;

    public GameRatingResultDTO addOrUpdateRating(GameRatingDTO data) {
        return addOrUpdateGameRating.addOrUpdateRating(data);
    }

    public Page<GameRatingReturnDTO> getRatingsPageable(
            Pageable pageable,
            String userId,
            String gameId,
            String gameName
    ) {
        return getGameRatings.getRatingsPageable(pageable, userId, gameId, gameName);
    }

}
