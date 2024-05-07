package com.rafael.crudspring.exception;

public class RecordNotFoundException extends Exception {
    public RecordNotFoundException(long id){
        super("Registro não encontrado com id: " + id);
    }
}
