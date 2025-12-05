package nl.novi.vinylshop.dtos.genre;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class GenreRequestDTO {
    @NotNull(message = "Name cannot be empty")
    @Min(value = 2, message = "Warning: name must have at least 2 characters")
    @Max(value = 100, message = "Warning: name cannot exceed 100 characters")
    String name;

    @Max(value = 255, message = "Warning: name cannot exceed 255 characters")
    String description;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
