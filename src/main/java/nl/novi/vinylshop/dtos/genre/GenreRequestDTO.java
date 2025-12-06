package nl.novi.vinylshop.dtos.genre;

import jakarta.validation.constraints.Size;

public class GenreRequestDTO {
    @Size(min = 2, message = "Warning: name must have at least 2 characters")
    @Size(max = 100, message = "Warning: name cannot exceed 100 characters")
    String name;

    @Size(max = 255, message = "Warning: name cannot exceed 255 characters")
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
