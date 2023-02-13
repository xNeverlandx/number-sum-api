package com.example.demo.web;

import com.example.demo.dto.NumberSumDto;
import com.example.demo.service.NumberSumService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class NumberSumApiEndpoint {
    private final NumberSumService numberSumService;

    @GetMapping("/number/{firstNumber}/{secondNumber}/number-sum")
    public ResponseEntity<NumberSumDto> getNumbersSum(@PathVariable Integer firstNumber, @PathVariable Integer secondNumber) {
        return ResponseEntity.ok(numberSumService.calculateSumNumbers(firstNumber, secondNumber));
    }

    @GetMapping("/number/{number}/{isDesc}/search")
    public ResponseEntity<List<NumberSumDto>> searchSum(@PathVariable Double number, @PathVariable boolean isDesc) {
        return ResponseEntity.ok(numberSumService.searchSuitableNumberSumDtos(number, isDesc));
    }
}
