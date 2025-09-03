package com.geekcatalog.api.controller;

import com.geekcatalog.api.dto.gameRating.GameRatingDTO;
import com.geekcatalog.api.dto.gameRating.GameRatingReturnDTO;
import com.geekcatalog.api.dto.utils.ApiResponseDTO;
import com.geekcatalog.api.service.GameRatingService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/games/rating")
@Tag(name = "GameRating")
@RequiredArgsConstructor
public class GamesRatingController {
    private final GameRatingService service;

    @PostMapping
    public ResponseEntity<ApiResponseDTO<GameRatingReturnDTO>> addOrUpdateRating(@RequestBody @Valid GameRatingDTO data) {
        var result = service.addOrUpdateRating(data);
        var status = result.isNew() ? HttpStatus.CREATED : HttpStatus.OK;
        return ResponseEntity.status(status).body(ApiResponseDTO.success(result.dto()));
    }

    @GetMapping
    public ResponseEntity<ApiResponseDTO<Page<GameRatingReturnDTO>>> getGameRatings(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "createdAt") String sortField,
            @RequestParam(defaultValue = "desc") String sortOrder,
            @RequestParam(required = false) String userId,
            @RequestParam(required = false) String gameId,
            @RequestParam(required = false) String gameName
    ) {
        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by(Sort.Direction.fromString(sortOrder), sortField)
        );

        Page<GameRatingReturnDTO> result = service.getRatingsPageable(
                pageable,
                userId,
                gameId,
                gameName
        );

        return ResponseEntity.ok(ApiResponseDTO.success(result));
    }
}
