package com.geekcatalog.api.service;

import com.geekcatalog.api.domain.gameRating.useCase.AddOrUpdateGameRating;
import com.geekcatalog.api.dto.gameRating.GameRatingDTO;
import com.geekcatalog.api.dto.gameRating.GameRatingResultDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GameRatingService {
    private final AddOrUpdateGameRating addOrUpdateGameRating;

    public GameRatingResultDTO addOrUpdateRating(GameRatingDTO data) {
        return addOrUpdateGameRating.addOrUpdateRating(data);
    }

}
