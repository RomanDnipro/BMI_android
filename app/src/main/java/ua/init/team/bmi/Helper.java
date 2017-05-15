package ua.init.team.bmi;

import java.util.Scanner;

/**
 * Created by Roman on 16.05.2017.
 */

public class Helper {
    public static double absMy(double i){
        if (i < 0){
            return i*-1;
        }else {
            return i;
        }
    }

    public static double powMy(double base, int exponent) {
        if (exponent == 0){
            return 1;
        }
        if (exponent == 1){
            return base;
        }
        if (base == 0){
            return 0;
        }
        if (base == 1){
            return 1;
        }

        double res = 1;
        for (int i = 0; i < absMy(exponent); i++) {
            res *= base;
        }
        if (exponent < 0) {
            return (1 / res);
        } else {
            return res;
        }
    }

    public static double powMy(String base, String exponent) {
//        if (Helper.isNumericMy(base) && Helper.isNumericMy(exponent)) {
//        double res = 0;
//        try {
        return powMy(Double.parseDouble(base), Integer.parseInt(exponent));
//        }catch (NumberFormatException e){
//            System.out.println(Helper.message1);
//        }
//        return res;
    }

    public static double powMy(Scanner sc) {
        return powMy(sc.nextLine(), sc.nextLine());
    }

    public static double roundMy(double number, int accuracy) {
        String stringNumber = Double.toString(number);

        int i = 1;
        while (stringNumber.charAt(i) != '.') {
            i++;
        }

        double res = absMy(number)/*absMy(Double.parseDouble(stringNumber))*/;
        if ((i + accuracy + 1) < stringNumber.length()) {

            res = absMy(Double.parseDouble(stringNumber.substring(0, i + accuracy + 1)));

            if (/*Integer.parseInt(stringNumber, i + accuracy + 1 )*/ Character.getNumericValue(stringNumber.charAt(i + accuracy + 1)) > 4) {
                res += 1 / powMy(10, accuracy);
            }
        }
        if (number < 0) {
            return 0 - res;
        } else {
            return res;
        }
    }

    public static double root(double number, int power, int accuracy) {
//        int i = 0;
        double minLimit = 0;
        double maxLimit = number;
        double res = number / power;

        while (roundMy(powMy(res, power), accuracy) != roundMy(number, accuracy)) {
            if (roundMy(powMy(res, power), accuracy) > number) {
                maxLimit = res;
            } else {
                minLimit = res;
            }
            res = (minLimit + maxLimit) / 2;
//            i++;
        }
//        System.out.println(i);
        return roundMy(res, accuracy);
    }

    public static double root(double number, int power){
        return root(number, power, 4);
    }

    public static double sqrtMy(double number){
        return root(number, 2);
    }

    public static double sqrt(String s){
        return sqrtMy(Double.parseDouble(s));
    }

    public static double sqrt(Scanner sc){
        return sqrt(sc.nextLine());
    }
}
