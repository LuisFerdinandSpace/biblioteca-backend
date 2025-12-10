package com.biblioteca.backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "libros", uniqueConstraints = {
        @UniqueConstraint(name = "uq_libros_isbn", columnNames = {"isbn"})
})
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String autor;

    @Column
    private String genero;

    @Column(nullable = false)
    private Integer paginas;

    @Column(nullable = false, unique = true)
    private String isbn;

    public Libro() {}

    public Libro(String titulo, String autor, String genero, Integer paginas, String isbn) {
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.paginas = paginas;
        this.isbn = isbn;
    }

    // getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }

    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }

    public Integer getPaginas() { return paginas; }
    public void setPaginas(Integer paginas) { this.paginas = paginas; }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }
}
