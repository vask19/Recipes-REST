package com.example.recipesrest.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import java.time.LocalDateTime;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
public class RecipeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Column
    @NotBlank
    private String name;

    @Column
    @NotBlank
    private String category;

    @Column
    private LocalDateTime date;

    @Column
    @NotBlank
    private String description;

    @Column
    @ElementCollection
    @NotEmpty
    private List<String> ingredients;

    @Column
    @ElementCollection
    @NotEmpty
    private List<String> directions;

    {
        date = LocalDateTime.now();
    }

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

}
