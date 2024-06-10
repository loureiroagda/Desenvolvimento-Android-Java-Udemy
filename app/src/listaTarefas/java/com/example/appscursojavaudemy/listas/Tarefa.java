package com.example.appscursojavaudemy.listas;

import java.io.Serializable;

public class Tarefa implements Serializable {

    private Long id;
    private String txTarefa;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTxTarefa() {
        return txTarefa;
    }

    public void setTxTarefa(String txTarefa) {
        this.txTarefa = txTarefa;
    }
}
