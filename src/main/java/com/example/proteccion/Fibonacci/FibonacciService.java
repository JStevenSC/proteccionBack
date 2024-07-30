package com.example.proteccion.Fibonacci;

import com.example.proteccion.Email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service

public class FibonacciService {

    @Autowired
    private EmailService emailService;

    private List<FibonacciResponse> fibonacciHistory = new ArrayList<>();

    public FibonacciResponse generateFibonacciSeries(String hora) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime time = LocalTime.parse(hora, formatter);

        FibonacciResponse response = generateFibonacciSeriesFromTime(time, hora);

        // Almacena la serie generada en la lista de historial
        fibonacciHistory.add(response);

        // Envía un correo con la serie generada
        //emailService.sendFibonacciEmail(response);

        return response;
    }

    public FibonacciResponse generateFibonacciSeriesFromCurrentTime() {
        LocalTime time = LocalTime.now();
        String hora = time.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        FibonacciResponse response = generateFibonacciSeriesFromTime(time, hora);

        // Almacena la serie generada en la lista de historial
        fibonacciHistory.add(response);

        // Envía un correo con la serie generada
        //emailService.sendFibonacciEmail(response);

        return response;
    }

    private FibonacciResponse generateFibonacciSeriesFromTime(LocalTime time, String hora) {
        int x = (time.getMinute() / 10) % 10;
        int y = (time.getMinute() % 10);
        int n = time.getSecond();

        List<Integer> series = new ArrayList<>();
        series.add(x);
        series.add(y);

        for (int i = 2; i < n + 2; i++) {
            series.add(series.get(i - 1) + series.get(i - 2));
        }

        List<Integer> resultSeries = new ArrayList<>(series.subList(0, n + 2));
        Collections.reverse(resultSeries);

        return new FibonacciResponse(resultSeries, hora);
    }

    public List<FibonacciResponse> getFibonacciHistory() {
        return new ArrayList<>(fibonacciHistory);
    }

}
