import java.util.*;
import java.io.*;
public class robbin {
public static void main(String args[]) {
int n, sum = 0;
float total_tt = 0, total_waiting = 0;
Scanner s = new Scanner(System.in);
System.out.println("Enter Number Of Processes You Want To Execute:");
n = s.nextInt();
int arrival[] = new int[n];
int cpu[] = new int[n];
int ncpu[] = new int[n];
int finish[] = new int[100];
int turntt[] = new int[n];
int wait[] = new int[n];
int process[] = new int[n];
int seq[] = new int[100];
int t_quantum, difference, temp_sum = 0, k = 0;
// Input arrival times and CPU burst times for each process
for (int i = 0; i < n; i++) {
System.out.println("Enter arrival time of Process " + (i + 1) + ": ");
arrival[i] = s.nextInt();
System.out.println("Enter CPU time of Process " + (i + 1) + ": ");
ncpu[i] = cpu[i] = s.nextInt();
process[i] = i + 1;
}
// Input the time quantum
System.out.println("Enter time quantum: ");
t_quantum = s.nextInt();
// Calculate total CPU time needed for all processes
for (int i = 0; i < n; i++) {
temp_sum += cpu[i];
}
System.out.println("Process execution sequence: ");
// Round Robin scheduling logic
while (sum != temp_sum) {
for (int i = 0; i < n; i++) {
if (ncpu[i] > 0) {
if (ncpu[i] < t_quantum) {
difference = ncpu[i];
ncpu[i] = 0;
} else {
difference = ncpu[i] - t_quantum;
ncpu[i] = difference;
}
// Update the total execution time
if (ncpu[i] < t_quantum) {
sum += difference;
} else {
sum += t_quantum;
}
finish[k] = sum;
seq[k] = i;
System.out.print((seq[k] + 1) + " ");
k++;
}
}
}
System.out.println();
// Calculate turnaround time and waiting time for each process
for (int i = 0; i < n; i++) {
int carr = 0, tt = 0;
carr = arrival[i];
for (int j = 0; j < k; j++) {
if (seq[j] == i) {
tt += (finish[j] - carr);
carr = finish[j];
}
}
turntt[i] = tt;
System.out.println("Turn around time for Process " + (i + 1) + ": " + turntt[i]);
total_tt += turntt[i];
wait[i] = turntt[i] - cpu[i];
System.out.println("Waiting time for Process " + (i + 1) + ": " + wait[i]);
total_waiting += wait[i];
}
// Display process details
System.out.println("\n\nProcess\t\tAT\tCPU_T");
for (int i = 0; i < n; i++) {
System.out.println(process[i] + "\t\t" + arrival[i] + "\t" + cpu[i]);
}
System.out.println("\n\n");
System.out.println("Average Turnaround Time: " + (total_tt / n));
System.out.println("Average Waiting Time: " + (total_waiting / n));
}
}