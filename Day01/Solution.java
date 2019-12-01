package Day01;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    private static List<Integer> readInput(String fileName) {
        List<Integer> inputList = new ArrayList<>();
        try {
            File file = new File(fileName);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                inputList.add(Integer.valueOf(line));
            }
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputList;
    }

    private static long computeFuelNeededForMass(long mass) {
        return mass / 3 - 2;
    }

    private static long computeTotalFuelNeededForModule(long moduleMass) {
        long fuel = computeFuelNeededForMass(moduleMass);
        long totalFuel = 0;
        while (fuel > 0) {
            totalFuel += fuel;
            fuel = computeFuelNeededForMass(fuel);
        }
        return totalFuel;
    }

    private static long computeFuelNeededForAllModules(List<Integer> moduleMassesList) {
        long totalFuel = 0;
        for (Integer mass : moduleMassesList) {
            totalFuel += computeFuelNeededForMass(mass);
        }
        return totalFuel;
    }

    private static long computeTotalFuelNeededForAllModules(List<Integer> moduleMassesList) {
        long totalFuel = 0;
        for (Integer mass : moduleMassesList) {
            totalFuel += computeTotalFuelNeededForModule(mass);
        }
        return totalFuel;
    }

    private static long part1(List<Integer> moduleMassesList) {
        return computeFuelNeededForAllModules(moduleMassesList);
    }

    private static long part2(List<Integer> moduleMassesList) {
        return computeTotalFuelNeededForAllModules(moduleMassesList);
    }

    public static void main(String[] args) {
        List<Integer> input = readInput("src/Day01/input.txt");
        System.out.println(part2(input));
    }
}
