package com.cleosilva.library.dto;

import jakarta.validation.constraints.NotNull;

public class EmprestimoDTO {
    @NotNull(message = "O ID do livro é obrigatório")
    private Long livroId;
    @NotNull(message = "O ID do usuário é obrigatório")
    private Long usuarioId;

    public EmprestimoDTO() {
    }

    public EmprestimoDTO(Long livroId, Long usuarioId) {
        this.livroId = livroId;
        this.usuarioId = usuarioId;
    }


    public Long getLivroId() {
        return livroId;
    }

    public void setLivroId(Long livroId) {
        this.livroId = livroId;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    @Override
    public String toString() {
        return "EmprestimoDTO{" +
                "livroId=" + livroId +
                ", usuarioId=" + usuarioId +
                '}';
    }
}
