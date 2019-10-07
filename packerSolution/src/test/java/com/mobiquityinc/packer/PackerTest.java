package com.mobiquityinc.packer;

import com.mobiquityinc.exception.APIException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PackerTest {

    @Test
    void sucessCase() throws APIException {
        String filePath = "input.txt";
        String solution = Packer.pack(filePath);

        String expected =  "4,2\n" +
                "4\n" +
                "-\n" +
                "7,6\n" +
                "9,6\n";
        System.out.println(solution);
        Assertions.assertEquals(expected,solution);
    }

    @Test
    void throwExceptionWhenNumberOfItemsExceded() {
        String filePath = "inputNumberException.txt";
        Assertions.assertThrows(APIException.class, () -> {
            Packer.pack(filePath);
        });
    }

    @Test
    void throwExceptionWhenMaxWeighIsExceded() {
        String filePath = "maxWeightExceed.txt";
        Assertions.assertThrows(APIException.class, () -> {
            Packer.pack(filePath);
        });
    }

    @Test
    void throwExceptionWhenMaxCostItemIsExceeded() {
        String filePath = "maxCostItemExceed.txt";
        Assertions.assertThrows(APIException.class, () -> {
            Packer.pack(filePath);
        });
    }

    @Test
    void throwExceptionWhenMaxWightItemIsExceeded() {
        String filePath = "maxWeightItemExceed.txt";
        Assertions.assertThrows(APIException.class, () -> {
            Packer.pack(filePath);
        });
    }
}
