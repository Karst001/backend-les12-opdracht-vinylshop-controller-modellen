package nl.novi.vinylshop.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.GenerationType;

import java.time.LocalDateTime;

@MappedSuperclass
public abstract class BaseEntity {
    //define the properties
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime createDate;
    private LocalDateTime editDate;

    //event on Create, Hibernate scans lifecycle hooks inside entities
    @PrePersist
    protected void onCreate() {
        this.createDate = LocalDateTime.now();
        this.editDate = LocalDateTime.now();
    }

    //event on Update, Hibernate scans lifecycle hooks inside entities
    @PreUpdate
    protected void onUpdate() {
        this.editDate = LocalDateTime.now();
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public LocalDateTime getEditDate() {
        return editDate;
    }
}

