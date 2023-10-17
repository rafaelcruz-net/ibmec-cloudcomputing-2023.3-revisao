package br.edu.ibmec.cloudcomputing.revisao.errorHandler;

public class BusinessError {

    private String typeError = "BusinessException";

    public String message;

    public BusinessError(String typeError, String message) {
        this.typeError = typeError;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getTypeError() {
        return typeError;
    }

}
