package com.cleosilva.library.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(LivroIndisponivelException.class)
    public ResponseEntity<ApiError> handleLivroIndisponivel(LivroIndisponivelException ex, HttpServletRequest request) {
        ApiError error = new ApiError(
                HttpStatus.BAD_REQUEST.value(),
                "Livro Indisponível.",
                ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(LivroDevolvidoException.class)
    public ResponseEntity<ApiError> handleLivroDevolvido(LivroDevolvidoException ex, HttpServletRequest request){
        ApiError error = new ApiError(
                HttpStatus.BAD_REQUEST.value(),
                "Este livro já foi devolvido",
                ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(UsuarioNaoEncontradoException.class)
    public ResponseEntity<ApiError> handleUsuarioNaoEncontrado(UsuarioNaoEncontradoException ex, HttpServletRequest request){
        ApiError error = new ApiError(
                HttpStatus.NOT_FOUND.value(),
                "Usuário não encontrado.",
                ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    // Tratamento Genérico
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiError> handleGeneric(RuntimeException ex, HttpServletRequest request){
        ApiError error = new ApiError(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Erro interno",
                ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

}
