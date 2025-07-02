package com.cleosilva.library.controller;

import com.cleosilva.library.dto.EmprestimoDTO;
import com.cleosilva.library.model.Emprestimo;
import com.cleosilva.library.service.EmprestimoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loan")
public class EmprestimoController {
    @Autowired
    private EmprestimoService emprestimoService;

    public EmprestimoController(EmprestimoService emprestimoService) {
        this.emprestimoService = emprestimoService;
    }

    @PostMapping
    public ResponseEntity<Emprestimo> createEmprestimo(@RequestBody EmprestimoDTO request){
        Emprestimo emprestimo = emprestimoService.realizarEmprestimo(request.getLivroId(), request.getUsuarioId());
        return ResponseEntity.ok(emprestimo);
    }

    @GetMapping
    public ResponseEntity<List<Emprestimo>> listAll(){
        return ResponseEntity.ok(emprestimoService.listarEmprestimos());
    }

    @PutMapping("/{id}/return")
    public ResponseEntity<Emprestimo> returnLoan(@PathVariable Long id){
        return ResponseEntity.ok(emprestimoService.devolverLivro(id));
    }

}
