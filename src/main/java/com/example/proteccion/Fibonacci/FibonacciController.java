package com.example.proteccion.Fibonacci;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fibonacci")

public class FibonacciController {

    @Autowired
    private FibonacciService fibonacciService;


    //se tiene  un figonacci por la hora actual
    @GetMapping
    public FibonacciResponse getFibonacciSeries() {
        return fibonacciService.generateFibonacciSeriesFromCurrentTime();
    }
    //se tiene  un figonacci por la hora enviada
    @PostMapping
    public FibonacciResponse getFibonacciSeries(@RequestBody FibonacciRequest request) {
        return fibonacciService.generateFibonacciSeries(request.getHora());
    }
    //se tiene  un lista de figonacci almacendos
    @GetMapping("/history")
    public List<FibonacciResponse> getFibonacciHistory() {
        return fibonacciService.getFibonacciHistory();
    }
}
