package com.example.neurodiagnosis.verification.mop.monitor3;

import com.example.neurodiagnosis.application.service.database.DatabaseContextLive;
import com.example.neurodiagnosis.domain.entities.TestResult;
import com.example.neurodiagnosis.infrastructure.repositories.MmseTestResultsRepository;

import java.util.Iterator;
import java.util.UUID;

public class TestIteratorRule {

    public static void main(String[] args) {


        var testResults = new MmseTestResultsRepository(new DatabaseContextLive())
                .getTestResults(UUID.fromString("c3b023a2-f5d1-4457-9413-30145b5f4c49"));


        TestResult currentObject = null;

        //aratam toate rezultatele la teste mmse

        Iterator i = testResults.listIterator();
//        i.next();

        for (; i.hasNext() ; currentObject = (TestResult) i.next() ) {
            System.out.printf("Test result %d at %s%n", currentObject.getTestResult(),
                    currentObject.getTestDate());
        }

    }
}
