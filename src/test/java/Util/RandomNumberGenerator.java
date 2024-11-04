package Util;

import java.util.Random;

public class RandomNumberGenerator {

    public static String generateNumber(int length) {
        Random random = new Random();
        int lowerBound = (int) Math.pow(10, length - 1);
        int upperBound = (int) Math.pow(10, length) - 1;

        int randomNumber = lowerBound + random.nextInt(upperBound - lowerBound + 1);
        return String.valueOf(randomNumber);
    }

//    public static int generateNumber2() {
//        Random random = new Random();
//        int gender = random.nextInt(2);
//        return gender;
//    }

//    public static void main(String[] args) {
//        System.out.println(generateNumber2());
//    }
}
