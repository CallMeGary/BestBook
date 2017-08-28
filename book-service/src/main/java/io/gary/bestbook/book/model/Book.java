package io.gary.bestbook.book.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 50, nullable = false)
    private String isbn;

    @Column(nullable = false)
    private Long price;

    @Column(length = 50, nullable = false)
    private String author;

    @Column(nullable = false)
    private LocalDate publishedAt;

    @Column(length = 100, nullable = false)
    private String publishedBy;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(length = 100, nullable = false)
    private String createdBy;

    @Column(nullable = false)
    private LocalDateTime lastModifiedAt;

    @Column(length = 100, nullable = false)
    private String lastModifiedBy;

}
