package com.geekcatalog.api.dto.game;

import jakarta.validation.constraints.NotEmpty;

public record GameDTO(@NotEmpty String name, String description, @NotEmpty Integer releaseYear) {
}
