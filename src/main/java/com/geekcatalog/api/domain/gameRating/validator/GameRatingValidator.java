package com.geekcatalog.api.domain.gameRating.validator;

import com.geekcatalog.api.infra.exceptions.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GameRatingValidator {

    public void validateRatingValue(int rating) {
        if (rating < 0 || rating > 10) {
            throw new ValidationException("Rating must be an integer between 0 and 10");
        }
    }
}
