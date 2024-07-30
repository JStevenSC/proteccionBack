package com.example.proteccion;


import com.example.proteccion.Fibonacci.FibonacciResponse;
import com.example.proteccion.Fibonacci.FibonacciService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FibonacciServiceTest {

    @InjectMocks
    private FibonacciService fibonacciService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGenerateFibonacciSeries() {
        // Define los valores de prueba
        String hora = "12:34:56";
        LocalTime time = LocalTime.parse(hora, DateTimeFormatter.ofPattern("HH:mm:ss"));

        // Llama al método
        FibonacciResponse response = fibonacciService.generateFibonacciSeries(hora);

        // Calcula la serie esperada
        int x = (time.getMinute() / 10) % 10;
        int y = (time.getMinute() % 10);
        int n = time.getSecond();
        List<Integer> expectedSeries = new ArrayList<>();
        expectedSeries.add(x);
        expectedSeries.add(y);

        for (int i = 2; i < n + 2; i++) {
            expectedSeries.add(expectedSeries.get(i - 1) + expectedSeries.get(i - 2));
        }

        List<Integer> resultSeries = new ArrayList<>(expectedSeries.subList(0, n + 2));
        Collections.reverse(resultSeries);

        // Verifica que la serie generada sea correcta
        assertEquals(resultSeries, response.getSeries());
        assertEquals(hora, response.getHora());
    }

    @Test
    public void testGenerateFibonacciSeriesFromCurrentTime() {
        // Obtiene la hora actual
        LocalTime time = LocalTime.now();
        String hora = time.format(DateTimeFormatter.ofPattern("HH:mm:ss"));

        // Llama al método
        FibonacciResponse response = fibonacciService.generateFibonacciSeriesFromCurrentTime();

        // Calcula la serie esperada
        int x = (time.getMinute() / 10) % 10;
        int y = (time.getMinute() % 10);
        int n = time.getSecond();
        List<Integer> expectedSeries = new ArrayList<>();
        expectedSeries.add(x);
        expectedSeries.add(y);

        for (int i = 2; i < n + 2; i++) {
            expectedSeries.add(expectedSeries.get(i - 1) + expectedSeries.get(i - 2));
        }

        List<Integer> resultSeries = new ArrayList<>(expectedSeries.subList(0, n + 2));
        Collections.reverse(resultSeries);

        // Verifica que la serie generada sea correcta
        assertEquals(resultSeries, response.getSeries());
        assertEquals(hora, response.getHora());
    }

    @Test
    public void testGetFibonacciHistory() {
        // Define los valores de prueba
        String hora1 = "12:34:56";
        String hora2 = "23:45:12";
        FibonacciResponse response1 = fibonacciService.generateFibonacciSeries(hora1);
        FibonacciResponse response2 = fibonacciService.generateFibonacciSeries(hora2);

        // Obtén el historial
        List<FibonacciResponse> history = fibonacciService.getFibonacciHistory();

        // Verifica que el historial contenga las respuestas esperadas
        assertEquals(2, history.size());
        assertEquals(response1, history.get(0));
        assertEquals(response2, history.get(1));
    }
}