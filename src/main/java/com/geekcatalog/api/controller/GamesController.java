package com.geekcatalog.api.controller;

import com.geekcatalog.api.dto.utils.ApiResponseDTO;
import com.geekcatalog.api.dto.game.GameReturnDTO;
import com.geekcatalog.api.service.GameService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/games")
@Tag(name = "Game routes mapped on Controller.")
@RequiredArgsConstructor
public class GamesController {
    private final GameService service;

    @GetMapping
    public ResponseEntity<ApiResponseDTO<Page<GameReturnDTO>>> getGames(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "name") String sortField,
            @RequestParam(defaultValue = "asc") String sortOrder,
            @RequestParam(required = false) String id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer startYear,
            @RequestParam(required = false) Integer endYear
    ) {
        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by(Sort.Direction.fromString(sortOrder), sortField)
        );

        Page<GameReturnDTO> result = service.getGamesPageable(
                pageable,
                id,
                name,
                startYear,
                endYear
        );

        return ResponseEntity.ok(ApiResponseDTO.success(result));
    }
}
