package com.example.neurodiagnosis.verification.mop;

import java.util.Optional;

public class Test {
    public static void main(String[] args) {
        Optional<Integer> opt = Optional.of(10);
        Integer number = opt.get();
        System.out.println(number);
    }
}
