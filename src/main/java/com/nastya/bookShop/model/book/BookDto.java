package com.nastya.bookShop.model.book;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {
    private Integer id;
    private String bookName;
    private String author;
    private String genre;
    private Integer publicationYear;
    private Integer pages;

}
