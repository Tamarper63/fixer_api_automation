package org.example;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        int[] nums = {0, 1, 2, 3, 4, 5};
        int[] myRendom = getRandom(nums);
        System.out.println(Arrays.toString(myRendom));


    }

    public static int[] getRandom(int[] myArray) {
        Random random = new Random();
            for (int i = myArray.length - 1; i > 0; i--) {
                int index = random.nextInt(i + 1);
                // Swap elements
                int temp = myArray[index];
                myArray[index] = myArray[i];
                myArray[i] = temp;
            }
            return myArray;
        }


    }
}