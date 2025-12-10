package com.biblioteca.backend.service;

import com.biblioteca.backend.entity.Libro;
import com.biblioteca.backend.repository.LibroRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class LibroService {

    private final LibroRepository repo;

    public LibroService(LibroRepository repo) {
        this.repo = repo;
    }

    public List<Libro> listar() {
        return repo.findAll();
    }

    public Optional<Libro> obtenerPorId(Long id) {
        return repo.findById(id);
    }

    @Transactional
    public Libro guardar(Libro libro) {
        validarLibroBasico(libro);

        if (repo.existsByIsbn(libro.getIsbn())) {
            throw new IllegalStateException("ISBN duplicado");
        }

        return repo.save(libro);
    }

    @Transactional
    public Libro actualizar(Long id, Libro datos) {
        validarLibroBasico(datos);

        Libro existente = repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Libro no encontrado"));

        String nuevoIsbn = datos.getIsbn();
        if (nuevoIsbn != null && !nuevoIsbn.equals(existente.getIsbn())) {
            if (repo.existsByIsbn(nuevoIsbn)) {
                throw new IllegalStateException("ISBN duplicado");
            }
            existente.setIsbn(nuevoIsbn);
        }

        existente.setTitulo(datos.getTitulo());
        existente.setAutor(datos.getAutor());
        existente.setGenero(datos.getGenero());
        existente.setPaginas(datos.getPaginas());

        return repo.save(existente);
    }

    @Transactional
    public void eliminar(Long id) {
        if (!repo.existsById(id)) {
            throw new IllegalArgumentException("Libro no encontrado");
        }
        repo.deleteById(id);
    }

    // Validaciones que solicitaste (manual)
    private void validarLibroBasico(Libro libro) {
        if (libro.getTitulo() == null || libro.getTitulo().trim().isEmpty()) {
            throw new IllegalArgumentException("El título es obligatorio");
        }
        if (libro.getAutor() == null || libro.getAutor().trim().isEmpty()) {
            throw new IllegalArgumentException("El autor es obligatorio");
        }
        if (libro.getIsbn() == null || libro.getIsbn().trim().isEmpty()) {
            throw new IllegalArgumentException("El ISBN es obligatorio");
        }
        if (libro.getPaginas() == null || libro.getPaginas() <= 0) {
            throw new IllegalArgumentException("Páginas debe ser mayor a 0");
        }
    }
}
