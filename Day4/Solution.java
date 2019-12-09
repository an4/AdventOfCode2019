package Day4;

/**
 * --- Day 4: Secure Container ---
 * You arrive at the Venus fuel depot only to discover it's protected by a password. The Elves had
 * written the password on a sticky note, but someone threw it out.
 *
 * However, they do remember a few key facts about the password:
 *
 * It is a six-digit number.
 * The value is within the range given in your puzzle input.
 * Two adjacent digits are the same (like 22 in 122345).
 * Going from left to right, the digits never decrease; they only ever increase or stay the same
 * (like 111123 or 135679).
 * Other than the range rule, the following are true:
 *
 * 111111 meets these criteria (double 11, never decreases).
 * 223450 does not meet these criteria (decreasing pair of digits 50).
 * 123789 does not meet these criteria (no double).
 * How many different passwords within the range given in your puzzle input meet these criteria?
 *
 * --- Part Two ---
 * An Elf just remembered one more important detail: the two adjacent matching digits are not part
 * of a larger group of matching digits.
 *
 * Given this additional criterion, but still ignoring the range rule, the following are now true:
 *
 * 112233 meets these criteria because the digits never decrease and all repeated digits are exactly
 * two digits long.
 * 123444 no longer meets the criteria (the repeated 44 is part of a larger group of 444).
 * 111122 meets the criteria (even though 1 is repeated more than twice, it still contains a double
 * 22).
 * How many different passwords within the range given in your puzzle input meet all of the
 * criteria?
 */
public class Solution {
    private static final int RANGE_MIN = 240298;
    private static final int RANGE_MAX = 784956;

    private static boolean areDigitsInIncreasingOrder(int value) {
        while (value > 9) {
            if (value % 10 < value / 10 % 10) {
                return false;
            }
            value = value / 10;
        }
        return true;
    }

    private static boolean hasTwoAdjacentIdenticalDigits(int value) {
        while (value > 9) {
            if (value % 10 == value / 10 % 10) {
                return true;
            }
            value = value / 10;
        }
        return false;
    }

    private static boolean hasAtLeastOnePairOfAdjacentIdenticalDigits(int value) {
        int counter = 0;
        if (value % 10 == value / 10 % 10 && value / 10 % 10 != value / 100 % 10) {
            counter++;
        }
        if (value / 100000 % 10 == value / 10000 % 10 && value / 10000 % 10 != value / 1000 % 10) {
            counter++;
        }
        while (value > 999) {
            if (value % 10 != value / 10 % 10 && value / 10 % 10 == value / 100 % 10 &&
                    value / 100 % 10 != value / 1000 % 10) {
                counter++;
            }
            value = value / 10;
        }
        return counter > 0;
    }

    private static int getPossiblePasswordsInRangeCounter(int min, int max) {
        int counter = 0;
        for (int i = min; i <= max; i++) {
            if (areDigitsInIncreasingOrder(i) && hasTwoAdjacentIdenticalDigits(i)) {
                counter++;
            }
        }
        return counter;
    }

    private static int getPossiblePasswordsInRangeCounterWithExtraCondition(int min, int max) {
        int counter = 0;
        for (int i = min; i <= max; i++) {
            if (areDigitsInIncreasingOrder(i) && hasAtLeastOnePairOfAdjacentIdenticalDigits(i)) {
                counter++;
            }
        }
        return counter;
    }

    public static void main(String[] args) {
        System.out.println(getPossiblePasswordsInRangeCounter(RANGE_MIN, RANGE_MAX));
        System.out.println(
                getPossiblePasswordsInRangeCounterWithExtraCondition(RANGE_MIN, RANGE_MAX));
    }
}
