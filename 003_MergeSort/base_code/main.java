import pkg.*;
import java.util.*;
import java.io.*;
import java.time.*;
import java.lang.*;

class main {
    static final long createdNano = System.nanoTime();

    public static void main(String args[]) {
        long start, finish;
        System.out.println("Created: " + createdNano);
        System.out.println("-------------------Test-------------------\n");
        int[] times = {10, 100, 1000, 10000, 100000, 1000000};
        int[] nums = new int[100];
        for (int i : times) {
            System.out.println("Interval: " + i);
            nums = new int[i];

            Random rand = new Random();
            for (int j = 0; j < nums.length; j++) {
                nums[j] = rand.nextInt(100000);
            }
            start = System.nanoTime();
            mergeSort(nums, 0, nums.length - 1);
            finish = System.nanoTime();
            System.out.println("Started: " + start);
            System.out.println("Finished: " + finish);
            System.out.println("Duration: " + (finish - start) + " ns\n");
        }


        System.out.println("-------- Sorting Birth Records --------");
        List<BirthRecord> maleRecords = new ArrayList<>();
        List<BirthRecord> femaleRecords = new ArrayList<>();

        DayBirth(maleRecords, femaleRecords);

        System.out.println("Sorting male records...");
        start = System.nanoTime();
        mergeSortBirths(maleRecords);
        finish = System.nanoTime();
        System.out.println("Male duration: " + (finish - start) + " ns");

        System.out.println("Sorting female records...");
        start = System.nanoTime();
        mergeSortBirths(femaleRecords);
        finish = System.nanoTime();
        System.out.println("Female duration: " + (finish - start) + " ns");

        exportToCSV(maleRecords, "sorted_males.csv");
        exportToCSV(femaleRecords, "sorted_females.csv");
        System.out.println("Export complete!");
    }

    static void merge(int arr[], int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;

        int[] L = new int[n1];
        int[] R = new int[n2];

        for (int i = 0; i < n1; ++i)
            L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];

        int i = 0, j = 0;
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k++] = L[i++];
            } else {
                arr[k++] = R[j++];
            }
        }

        while (i < n1) {
            arr[k++] = L[i++];
        }

        while (j < n2) {
            arr[k++] = R[j++];
        }
    }

    static void mergeSort(int arr[], int l, int r) {
        if (l < r) {
            int m = l + (r - l) / 2;
            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);
            merge(arr, l, m, r);
        }
    }


    public static void mergeSortBirths(List<BirthRecord> records) {
        if (records.size() <= 1) return;

        int mid = records.size() / 2;
        List<BirthRecord> left = new ArrayList<>(records.subList(0, mid));
        List<BirthRecord> right = new ArrayList<>(records.subList(mid, records.size()));

        mergeSortBirths(left);
        mergeSortBirths(right);

        mergeBirths(records, left, right);
    }

    private static void mergeBirths(List<BirthRecord> result, List<BirthRecord> left, List<BirthRecord> right) {
        int i = 0, j = 0, k = 0;
        while (i < left.size() && j < right.size()) {
            if (left.get(i).births <= right.get(j).births) {
                result.set(k++, left.get(i++));
            } else {
                result.set(k++, right.get(j++));
            }
        }
        while (i < left.size()) result.set(k++, left.get(i++));
        while (j < right.size()) result.set(k++, right.get(j++));
    }


   public static void DayBirth(List<BirthRecord> maleRecords, List<BirthRecord> femaleRecords) {
    String csvFile = "births.csv";
    String line;
    String cvsSplitBy = ",";
    int lineNum = 0;  
    int badLines = 0; 
    try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
        br.readLine(); 
        lineNum++;
        while ((line = br.readLine()) != null) {
            lineNum++;
            String[] fields = line.split(cvsSplitBy);
            if (fields.length < 5) {
                System.out.println("Skipping incomplete line " + lineNum + ": " + line);
                badLines++;
                continue;
            }
            try {
                for (int i = 0; i < fields.length; i++) {
                    fields[i] = fields[i].trim();
                }

                if (fields[0].equalsIgnoreCase("null") || fields[1].equalsIgnoreCase("null") ||
                    fields[2].equalsIgnoreCase("null") || fields[3].equalsIgnoreCase("null") ||
                    fields[4].equalsIgnoreCase("null") || fields[4].isEmpty()) {
                    System.out.println("Skipping bad line " + lineNum + " (contains 'null'): " + line);
                    badLines++;
                    continue;
                }
                int year = Integer.parseInt(fields[0]);
                int month = Integer.parseInt(fields[1]);
                int day = Integer.parseInt(fields[2]);
                String gender = fields[3];
                int births = Integer.parseInt(fields[4]);
                BirthRecord record = new BirthRecord(year, month, day, gender, births);
                if (gender.equalsIgnoreCase("M")) {
                    maleRecords.add(record);
                } else if (gender.equalsIgnoreCase("F")) {
                    femaleRecords.add(record);
                }
            } catch (NumberFormatException e) {
                System.out.println("Skipping invalid numeric data on line " + lineNum + ": " + line);
                badLines++;
            }
        }

        System.out.println("CSV data loaded successfully!");
        System.out.println("   Males: " + maleRecords.size());
        System.out.println("   Females: " + femaleRecords.size());
        System.out.println("   Skipped lines: " + badLines);

    } catch (IOException e) {
        System.out.println("Error reading CSV file: " + e.getMessage());
    }
}
    public static void exportToCSV(List<BirthRecord> records, String filename) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filename))) {
            pw.println("Year,Month,Day,Gender,Births");
            for (BirthRecord r : records) {
                pw.println(r.year + "," + r.month + "," + r.day + "," + r.gender + "," + r.births);
            }
        } catch (IOException e) {
            System.out.println("Error writing CSV: " + e.getMessage());
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
