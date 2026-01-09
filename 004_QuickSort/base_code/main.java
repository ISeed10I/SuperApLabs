import java.io.*;
import java.util.*;


public class main {
    static final long createdNano = System.nanoTime();

    public static void main(String[] args) {
        System.out.println("Created: " + createdNano);

        runQuickSortTimingTest();
        List<DayBirth> allBirths = readCSV("births.csv");
        List<DayBirth> males = new ArrayList<>();
        List<DayBirth> females = new ArrayList<>();

        for (DayBirth d : allBirths) {
            if (d.getGender().equalsIgnoreCase("M"))
                males.add(d);
            else if (d.getGender().equalsIgnoreCase("F"))
                females.add(d);
        }


        long start, finish;

        start = System.nanoTime();
        quickSort(males, 0, males.size() - 1, "M");
        finish = System.nanoTime();
        System.out.println("Sorted males in " + (finish - start) + " ns");

        start = System.nanoTime();
        quickSort(females, 0, females.size() - 1, "F");
        finish = System.nanoTime();
        System.out.println("Sorted females in " + (finish - start) + " ns");

        writeCSV(males, "male_births_sorted.csv");
        writeCSV(females, "female_births_sorted.csv");

        System.out.println("export complete!");
    }

    public static void runQuickSortTimingTest() {
        int[] sizes = {10, 100, 1000, 10000, 100000};
        Random rand = new Random();
        System.out.println("\n------------------- QuickSort Performance -------------------");
        for (int size : sizes) {
            int[] arr = new int[size];
            for (int i = 0; i < size; i++) arr[i] = rand.nextInt(1000000);

            long start = System.nanoTime();
            quickSortArray(arr, 0, arr.length - 1);
            long finish = System.nanoTime();

            System.out.println("Size " + size + " → Duration: " + (finish - start) + " ns");
        }
        System.out.println("--------------------------------------------------------------\n");
    }

    public static void quickSortArray(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partitionArray(arr, low, high);
            quickSortArray(arr, low, pi - 1);
            quickSortArray(arr, pi + 1, high);
        }
    }

    private static int partitionArray(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }

    public static void quickSort(List<DayBirth> list, int low, int high, String gender) {
        if (low < high) {
            int pi = partition(list, low, high, gender);
            quickSort(list, low, pi - 1, gender);
            quickSort(list, pi + 1, high, gender);
        }
    }

    private static int partition(List<DayBirth> list, int low, int high, String gender) {
        int pivot = list.get(high).getBirths();
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (list.get(j).getBirths() <= pivot) {
                i++;
                Collections.swap(list, i, j);
            }
        }
        Collections.swap(list, i + 1, high);
        return i + 1;
    }

    public static List<DayBirth> readCSV(String filename) {
        List<DayBirth> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line = br.readLine(); // skip header
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length < 5) continue;
                int year = Integer.parseInt(parts[0]);
                int month = Integer.parseInt(parts[1]);
                int day = Integer.parseInt(parts[2]);
                String gender = parts[3];
                int births = Integer.parseInt(parts[4]);
                list.add(new DayBirth(year, month, day, gender, births));
            }
        } catch (Exception e) {
            System.out.println("Error reading CSV: " + e);
        }
        System.out.println("Loaded " + list.size() + " records from " + filename);
        return list;
    }
    
    public static void writeCSV(List<DayBirth> data, String filename) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filename))) {
            pw.println("year,month,day,gender,births");
            for (DayBirth d : data) {
                pw.println(d.toString());
            }
        } catch (IOException e) {
            System.out.println("Error writing CSV: " + e);
        }
    }
}

class BirthRecord {
    int year, month, day, births;
    String gender;

    BirthRecord(int year, int month, int day, String gender, int births) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.gender = gender;
        this.births = births;
    }
}