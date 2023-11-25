package com.leo.projeto.response;

import java.io.Serializable;

public class ResponseHandler<T> implements Serializable {

    private static final long serialVersionUID = 4312882832980856994L;

    private T response;

    private String mensagem;

    private Boolean error;

    public ResponseHandler(T data) {
        super();
        this.response = data;
        this.error = false;
    }

    public ResponseHandler(String mensagemErro) {
        super();
        this.mensagem = mensagemErro;
        this.error = true;
    }

    public ResponseHandler() {
    }

    public void setResponse(T data) {
        this.response = data;
    }

    public void setMensagem(String mensagemErro) {
        this.mensagem = mensagemErro;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public T getResponse() {
        return response;
    }

    public String getMensagem() {
        return mensagem;
    }

    public Boolean getError() {
        return error;
    }

}
