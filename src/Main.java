
import java.util.*;

public class Main {

    static String[] secretCode = new String[10];
    static String secretCodeStr = "";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input the length of the secret code:");
        int len = 0;
        int charLen = 0;
        String star = "";
        int check1 = 0;
        int check2 = 0;
        while (true) {
            String lenStr = scanner.nextLine();
            if (!lenStr.matches("\\d*")) {
                System.out.println("Error: " + lenStr + " isn't a valid number.");
                break;
            } else if (Integer.valueOf(lenStr) > 36) {
                System.out.println("Error: can't generate a secret number with a length of 11 " +
                        "because there aren't enough unique digits.");
            } else {
                len = Integer.valueOf(lenStr);
                check1 = 1;

            }
            star = "*".repeat(len);
            System.out.println("Input the number of possible symbols in the code:");
            String charLenStr = scanner.nextLine();
            if (!charLenStr.matches("\\d*")) {
                System.out.println("Error: " + charLenStr + " isn't a valid number.");
                break;
            } else if (Integer.valueOf(charLenStr) > 36) {
                System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
            } else {
                charLen = Integer.valueOf(charLenStr);
                check2 = 1;
            }
            if (len - charLen == 1) {
                check1 = 0;
                check2 = 0;
                System.out.println("Error: it's not possible to generate a code with a length of " + len + " with" + charLen + " unique symbols.");
                break;
            }
            if (len == 0 || charLen == 0) {
                check1 = 0;
                check2 = 0;
                System.out.println("error");
                break;
            }
            break;
        }


        if (check1 == 1 && check2 == 1) {

            System.out.println("The secret is prepared: " + star + " " + "(0-9, a-f).");

            System.out.println("Okay, let's start a game!");
            secretCodeStr = generateSecretCode(len, charLen);

            secretCode = secretCodeStr.split("");


            String[] input = new String[len];

            int step = 1;
            int cycle = 1;
            String convertInput = "";

            while (step != 0) {
                System.out.println("Turn " + cycle + ":"); // + " " + "Answer");
                cycle++;
                String[] inputStr = scanner.next().split("");
                for (int i = 0; i < len; i++) {

                    convertInput = convertInput + String.valueOf(inputStr[i]);
                }

                if (checkCowBull(inputStr).contains("Congrats")) {
                    System.out.println(checkCowBull(inputStr));
                    step = 0;
                } else {
                    System.out.println(checkCowBull(inputStr));
                }
            }
        }
    }

    private static String generateSecretCode(int len, int charLen) {
        Random random = new Random();
        String temp = "0";
        for (int i = 0; i < len - 2; i++) {
            temp = temp + "0";
        }
        if (charLen > 10 && charLen < 37) {
            charLen = charLen - 10;
        }
        char c = (char) (charLen + 96);

        ArrayList<String> arrayList = new ArrayList<>();
        if (len == 1) {
            len = 2;
        }
        while (arrayList.size() < len - 1) { // how many numbers u need - it will 6
            String a = String.valueOf(random.nextInt(10)); // this will give numbers between 1 and 50.

            if (!arrayList.contains(a)) {
                arrayList.add(a);
            }
        }
        String nums = String.join("", arrayList);

        String pseudoRandomNumber = nums + String.valueOf(c);

        return pseudoRandomNumber;
    }

    private static String checkCowBull(String[] number) {
        int cow = 0;
        int bull = 0;
        String cw = "cow";
        String bul = "bull";
        String ct = "cows";
        String bt = "bulls";
        List<Integer> dub = new ArrayList<>();

        for (int i = 0; i < number.length; i++) {
            if (number[i].equals(secretCode[i])) {
                dub.add(i);
                bull++;
            }
        }
        for (int j = 0; j < number.length; j++) {
            for (int k = 0; k < secretCode.length; k++) {
                if (number[j].equals(secretCode[k]) && j != k && !dub.contains(j) && !dub.contains(k)) {
                    cow++;
                }
            }
        }

        if (bull == secretCode.length) {
            return "Grade: " + bull + " " + "bulls" + "\n" + "Congrats! The secret code is " + secretCodeStr;
        } else if (cow != 0 && bull != 0) {
            if (cow == 1) {
                ct = cw;
            }
            if (bull == 1) {
                bt = bul;
            }
            return "Grade: " + bull + " " + bt + " " + "and" + " " + cow + " " + ct;
        } else if (cow == 0 && bull == 0) {
            return "None";
        } else if (cow == 0) {
            if (bull == 1) {
                bt = bul;
            }
            return "Grade: " + bull + " " + bt;
        } else if (bull == 0) {
            if (cow == 1) {
                ct = cw;
            }
            return "Grade: " + cow + " " + ct;
        }
        return "Default result";
    }
}

