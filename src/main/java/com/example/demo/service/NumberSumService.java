package com.example.demo.service;

import com.example.demo.dto.NumberSumDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NumberSumService {
    private static final List<NumberSumDto> numberSumDtoList = new ArrayList<>();
    public NumberSumDto calculateSumNumbers(Integer firstNumber, Integer secondNumber) {
        NumberSumDto numberSumDto = new NumberSumDto();
        numberSumDto.setFirstNumber(numberValidation(firstNumber));
        numberSumDto.setSecondNumber(numberValidation(secondNumber));
        numberSumDto.setNumberSum(firstNumber + secondNumber);
        numberSumDtoList.add(numberSumDto);
        return numberSumDto;
    }

    public List<NumberSumDto> searchSuitableNumberSumDtos(Double number, boolean isDesc) {
        List<NumberSumDto> listOfMatchingValues = new ArrayList<>();
        try {
            Integer convertedNumber = number.intValue();
            numberValidation(convertedNumber);
            for (NumberSumDto numberSumDto : numberSumDtoList) {
                if (numberSumDto.getNumberSum().equals(convertedNumber)
                        || numberSumDto.getFirstNumber().equals(convertedNumber) || numberSumDto.getSecondNumber().equals(convertedNumber)) {
                    listOfMatchingValues.add(numberSumDto);
                }
            }
        } catch (NumberFormatException ex) {
            throw new NumberFormatException("No match for this number " + number);
        }
        return sortList(listOfMatchingValues, isDesc);
    }

    private List<NumberSumDto> sortList(List<NumberSumDto> listOfMatchingValues, boolean isDesc) {
        if (isDesc) {
            listOfMatchingValues.sort(Comparator.comparing(NumberSumDto::getNumberSum).reversed());
        } else {
            listOfMatchingValues.sort(Comparator.comparing(NumberSumDto::getNumberSum));
        }
        return listOfMatchingValues;
    }

    private Integer numberValidation(Integer number) {
        if (number <= 100) {
            return number;
        } else {
            throw new NumberFormatException("Numbers must not exceed 100.");
        }
    }
}
