package com.cleosilva.library.controller;

import com.cleosilva.library.model.Livro;
import com.cleosilva.library.service.LivroService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class LivroController {
    private final LivroService livroService;

    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    @PostMapping
    public ResponseEntity<Livro> addBook(@RequestBody Livro livro){
        return ResponseEntity.ok(livroService.cadastrarLivro(livro));
    }

    @GetMapping
    public ResponseEntity<List<Livro>> listBooks(){
        return ResponseEntity.ok(livroService.listarLivros());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Livro> findBookById(@PathVariable Long id){
        return ResponseEntity.ok(livroService.buscarLivroPorId(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Livro> updateBook(@PathVariable Long id, @RequestBody Livro livro){
        Livro atualizado = livroService.atualizarLivro(id, livro);
        return ResponseEntity.ok(atualizado);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id){
        livroService.deletarLivro(id);
        return ResponseEntity.noContent().build();
    }
}
