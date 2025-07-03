package com.cleosilva.library.controller;

import com.cleosilva.library.dto.EmprestimoDTO;
import com.cleosilva.library.model.Emprestimo;
import com.cleosilva.library.service.EmprestimoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loans")
public class EmprestimoController {

    private final EmprestimoService emprestimoService;

    public EmprestimoController(EmprestimoService emprestimoService) {
        this.emprestimoService = emprestimoService;
    }

    @PostMapping
    public ResponseEntity<Emprestimo> createEmprestimo(@Valid @RequestBody EmprestimoDTO request){
        Emprestimo emprestimo = emprestimoService.realizarEmprestimo(request.getLivroId(), request.getUsuarioId());
        return ResponseEntity.ok(emprestimo);
    }

    @GetMapping
    public ResponseEntity<List<Emprestimo>> listLoans(){
        return ResponseEntity.ok(emprestimoService.listarEmprestimos());
    }

    @PutMapping("/{id}/return")
    public ResponseEntity<Emprestimo> returnLoan(@PathVariable Long id){
        return ResponseEntity.ok(emprestimoService.devolverLivro(id));
    }

}
