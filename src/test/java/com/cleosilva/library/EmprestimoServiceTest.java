package com.cleosilva.library;

import com.cleosilva.library.exception.LivroIndisponivelException;
import com.cleosilva.library.exception.UsuarioNaoEncontradoException;
import com.cleosilva.library.model.Emprestimo;
import com.cleosilva.library.model.Livro;
import com.cleosilva.library.model.Usuario;
import com.cleosilva.library.repository.EmprestimoRepository;
import com.cleosilva.library.repository.LivroRepository;
import com.cleosilva.library.repository.UsuarioRepository;
import com.cleosilva.library.service.EmprestimoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EmprestimoServiceTest {
    @InjectMocks
    private EmprestimoService emprestimoService;
    @Mock
    private EmprestimoRepository emprestimoRepository;
    @Mock
    private LivroRepository livroRepository;
    @Mock
    private UsuarioRepository usuarioRepository;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void realizarEmprestimo_DeveCriarEmprestimo_QuandoLivroDisponìvel(){
        Livro livro = new Livro("Titulo", "Autor", 2020, true);
        livro.setId(1L);
        Usuario usuario = new Usuario("Nome", "email@example");
        usuario.setId(1L);

        when(livroRepository.findById(1L)).thenReturn(Optional.of(livro));
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        when(emprestimoRepository.save(any(Emprestimo.class))).thenAnswer(invocation -> invocation.getArgument(0));
        when(livroRepository.save(any(Livro.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Emprestimo emprestimo = emprestimoService.realizarEmprestimo(1L, 1L);

        assertNotNull(emprestimo);
        assertEquals(livro, emprestimo.getLivro());
        assertEquals(usuario, emprestimo.getUsuario());
        assertEquals(LocalDate.now(), emprestimo.getDataEmprestimo());
        assertFalse(livro.isDisponivel());
        verify(livroRepository).save(livro);
        verify(emprestimoRepository).save(emprestimo);
    }
    @Test
    void realizarEmprestimo_DeveLancarExcecao_QuandoLivroIndisponivel() {
        Livro livro = new Livro("Titulo", "Autor", 2020, false);
        livro.setId(1L);
        Usuario usuario = new Usuario("Nome", "email@example.com");
        usuario.setId(1L);

        when(livroRepository.findById(1L)).thenReturn(Optional.of(livro));
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));

        assertThrows(LivroIndisponivelException.class, () -> {
            emprestimoService.realizarEmprestimo(1L, 1L);
        });
    }

    @Test
    void realizarEmprestimo_DeveLancarExcecao_QuandoUsuarioNaoEncontrado() {
        Livro livro = new Livro("Titulo", "Autor", 2020, true);
        livro.setId(1L);

        when(livroRepository.findById(1L)).thenReturn(Optional.of(livro));
        when(usuarioRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(UsuarioNaoEncontradoException.class, () -> {
            emprestimoService.realizarEmprestimo(1L, 1L);
        });

        verify(livroRepository, never()).save(any());
        verify(emprestimoRepository, never()).save(any());
    }
}
