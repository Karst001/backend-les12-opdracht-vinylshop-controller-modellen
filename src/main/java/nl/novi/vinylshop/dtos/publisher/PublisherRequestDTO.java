package nl.novi.vinylshop.dtos.publisher;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;

public class PublisherRequestDTO {
    @NotNull(message = "Name cannot be empty")
    @Max(value = 50, message = "Warning: name cannot exceed 50 characters")
    String name;

    String address;
    String contactDetails;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(String contactDetails) {
        this.contactDetails = contactDetails;
    }
}
