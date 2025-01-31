package gr.aueb.cf.Projects;

public class ProjectTwo {

    public static int maxSubArray(int[] arr) {
        // Αρχικοποίηση μεταβλητών
        int currentMax = arr[0];
        int globalMax = arr[0];

        // Διατρέχουμε τον πίνακα από τη δεύτερη θέση
        for (int i = 1; i < arr.length; i++) {
            // Υπολογισμός τοπικού μέγιστου
            currentMax = Math.max(currentMax + arr[i], arr[i]);

            // Ενημέρωση globalMax αν χρειάζεται
            globalMax = Math.max(globalMax, currentMax);
        }

        return globalMax;
    }

    public static void main(String[] args) {
        int[] arr = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println("Μέγιστο άθροισμα: " + maxSubArray(arr)); // Αναμενόμενο: 6
    }
}
