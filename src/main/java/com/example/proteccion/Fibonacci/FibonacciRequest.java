package com.example.proteccion.Fibonacci;

public class FibonacciRequest {
    private String hora;

    public FibonacciRequest() {}

    public FibonacciRequest(String hora) {
        this.hora = hora;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
}
