package com.geekcatalog.api.dto.gameRating;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record GameRatingDTO(
        @NotEmpty String userId,
        @NotEmpty String gameId,
        @NotNull Integer rating
) {}
