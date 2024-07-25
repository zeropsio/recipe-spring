package io.zerops.recipespring;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "file")
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID id;

    @Column(nullable = false)
    public String name;

    @Column(nullable = false)
    public Integer size;

    @Column(nullable = false)
    public String path;

    @Column(nullable = false)
    public String type;

    @Column(nullable = false)
    public LocalDateTime createdAt;

    public File() {
        this.createdAt = LocalDateTime.now();
    }
}
