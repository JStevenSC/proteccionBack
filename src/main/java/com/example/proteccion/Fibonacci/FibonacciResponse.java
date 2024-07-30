package com.example.proteccion.Fibonacci;

import java.util.List;

public class FibonacciResponse {


    private List<Integer> series;
    private String hora;

    public FibonacciResponse(List<Integer> series, String hora) {
        this.series = series;
        this.hora = hora;
    }

    public List<Integer> getSeries() {
        return series;
    }

    public void setSeries(List<Integer> series) {
        this.series = series;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }


}
