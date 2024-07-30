package com.example.proteccion.Fibonacci;

import java.time.LocalDateTime;

public class FibonacciRecord {
    private int[] series;
    private LocalDateTime timestamp;

    public FibonacciRecord(int[] series, LocalDateTime timestamp) {
        this.series = series;
        this.timestamp = timestamp;
    }

    public int[] getSeries() {
        return series;
    }

    public void setSeries(int[] series) {
        this.series = series;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
