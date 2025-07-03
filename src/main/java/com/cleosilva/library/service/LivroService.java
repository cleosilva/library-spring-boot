package com.cleosilva.library.service;

import com.cleosilva.library.model.Livro;
import com.cleosilva.library.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivroService {
    @Autowired
    private  LivroRepository livroRepository;


    public Livro cadastrarLivro(Livro livro){
        return livroRepository.save(livro);
    }

    public List<Livro> listarLivros(){
        return livroRepository.findAll();
    }

    public Livro buscarLivroPorId(Long id){
        return livroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado!"));
    }

        public Optional<Livro> atualizarLivro(Long id, Livro novoLivro){
            Optional<Livro> livroOptimal = livroRepository.findById(id);
            livroOptimal.ifPresent(livro -> {
                livro.setTitulo(novoLivro.getTitulo());
                livro.setAutor(novoLivro.getAutor());
                livro.setAnoPublicacao(novoLivro.getAnoPublicacao());
                livro.setDisponivel(novoLivro.isDisponivel());
                livroRepository.save(livro);
            });

            return livroOptimal;
        }
    // remover livro
    public boolean deletarLivro(Long id){
       Optional<Livro> livroOptional = livroRepository.findById(id);
       if (livroOptional.isPresent()){
           livroRepository.delete(livroOptional.get());
           return true;
       }else {
           return false;
       }
    }
}
