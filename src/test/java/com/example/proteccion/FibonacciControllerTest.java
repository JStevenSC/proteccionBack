package com.example.proteccion;

import com.example.proteccion.Fibonacci.FibonacciController;
import com.example.proteccion.Fibonacci.FibonacciRequest;
import com.example.proteccion.Fibonacci.FibonacciResponse;
import com.example.proteccion.Fibonacci.FibonacciService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

public class FibonacciControllerTest {

    private MockMvc mockMvc;

    @Mock
    private FibonacciService fibonacciService;

    @InjectMocks
    private FibonacciController fibonacciController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(fibonacciController).build();
    }

    @Test
    public void testGetFibonacciSeries() throws Exception {
        // Define los valores de prueba
        String hora = "12:34:56";
        List<Integer> series = Arrays.asList(1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144);
        FibonacciResponse response = new FibonacciResponse(series, hora);

        when(fibonacciService.generateFibonacciSeriesFromCurrentTime()).thenReturn(response);

        // Realiza la solicitud y verifica la respuesta
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(response)));
    }

    @Test
    public void testPostFibonacciSeries() throws Exception {
        String hora = "12:34:56";
        List<Integer> series = Arrays.asList(1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144);
        FibonacciResponse response = new FibonacciResponse(series, hora);
        FibonacciRequest request = new FibonacciRequest(hora);

        // Configura el comportamiento del mock
        when(fibonacciService.generateFibonacciSeries(hora)).thenReturn(response);

        // Realiza la solicitud y verifica la respuesta
        mockMvc.perform(post("/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(response)));
    }

    @Test
    public void testGetFibonacciHistory() throws Exception {

        String hora1 = "12:34:56";
        String hora2 = "23:45:12";
        List<Integer> series1 = Arrays.asList(1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144);
        List<Integer> series2 = Arrays.asList(2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377);
        FibonacciResponse response1 = new FibonacciResponse(series1, hora1);
        FibonacciResponse response2 = new FibonacciResponse(series2, hora2);
        List<FibonacciResponse> history = Arrays.asList(response1, response2);

        // Configura el comportamiento del mock
        when(fibonacciService.getFibonacciHistory()).thenReturn(history);

        // Realiza la solicitud y verifica la respuesta
        mockMvc.perform(get("/history"))
                .andExpect(status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(history)));
    }
}