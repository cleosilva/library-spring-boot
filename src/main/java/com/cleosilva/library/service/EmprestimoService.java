package com.cleosilva.library.service;

import com.cleosilva.library.exception.LivroDevolvidoException;
import com.cleosilva.library.exception.LivroIndisponivelException;
import com.cleosilva.library.exception.UsuarioNaoEncontradoException;
import com.cleosilva.library.model.Emprestimo;
import com.cleosilva.library.model.Livro;
import com.cleosilva.library.model.Usuario;
import com.cleosilva.library.repository.EmprestimoRepository;
import com.cleosilva.library.repository.LivroRepository;
import com.cleosilva.library.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
public class EmprestimoService {
    private final EmprestimoRepository emprestimoRepository;
    private final LivroRepository livroRepository;
    private final UsuarioRepository usuarioRepository;

    public EmprestimoService(EmprestimoRepository emprestimoRepository,
                             LivroRepository livroRepository,
                             UsuarioRepository usuarioRepository) {
        this.emprestimoRepository = emprestimoRepository;
        this.livroRepository = livroRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public Emprestimo realizarEmprestimo(Long livroId, Long usuarioId){
        Livro livro = livroRepository.findById(livroId)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Usuário não encontrado"));

        if (!livro.isDisponivel()){
            throw new LivroIndisponivelException(("Livro não está disponível para empréstimo!"));
        } else {
            livro.setDisponivel(false);
            livroRepository.save(livro);
        }
        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setLivro(livro);
        emprestimo.setUsuario(usuario);
        emprestimo.setDataEmprestimo(LocalDate.now());

        return emprestimoRepository.save(emprestimo);
    }

    public List<Emprestimo> listarEmprestimos(){
        return emprestimoRepository.findAll();
    }

    public Emprestimo devolverLivro(Long emprestimoId){
        Emprestimo emprestimo = emprestimoRepository.findById(emprestimoId)
                .orElseThrow(() -> new RuntimeException("Emprestimo não encontrado!"));

        if(emprestimo.getDataDevolucao() != null){
            throw new LivroDevolvidoException("Este livro já foi devolvido!");
        }

        emprestimo.setDataDevolucao(LocalDate.now());
        Livro livro = emprestimo.getLivro();
        livro.setDisponivel(true);
        livroRepository.save(livro);

        return emprestimoRepository.save(emprestimo);

    }
}
