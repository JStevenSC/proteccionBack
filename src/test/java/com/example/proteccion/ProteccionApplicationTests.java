package com.example.proteccion;

import com.example.proteccion.Fibonacci.FibonacciResponse;
import com.example.proteccion.Fibonacci.FibonacciService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalTime;
import java.util.List;

@SpringBootTest
class ProteccionApplicationTests {

	@Autowired
	private FibonacciService fibonacciService;

	@Test
	public void testGenerateFibonacciSeries() {
		LocalTime now = LocalTime.now();
		int x = now.getMinute() % 10;
		int y = now.getSecond() % 10;
		int n = now.getSecond();

		FibonacciResponse response = fibonacciService.generateFibonacciSeries("12:23:01");
		List<Integer> series = response.getSeries();

		//assertNotNull(series);
		//assertEquals(n, series.size());
	}

}
