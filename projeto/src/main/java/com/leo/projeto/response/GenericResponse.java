package com.leo.projeto.response;


public class GenericResponse<T> extends ResponseHandler<T> {
    private long totalElements;

    public GenericResponse(T data, long totalElements) {
        super(data);
        this.totalElements = totalElements;
    }

    public GenericResponse(String mensagemErro) {
        super(mensagemErro);
        this.totalElements = 0;
    }

    public GenericResponse(String mensagemErro, long totalElements) {
        super(mensagemErro);
        this.totalElements = totalElements;
    }

    public GenericResponse(long totalElements) {
        this.totalElements = totalElements;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

}