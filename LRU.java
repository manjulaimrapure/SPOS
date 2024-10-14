import java.util.*;
class LruAlgo {
int p[], n, fr[], m, fs[], index, k, l, flag1 = 0, flag2 = 0, pf = 0, frsize = 3, i, j;
Scanner src = new Scanner(System.in);
// Method to read the page table and frame size
void read() {
System.out.println("Enter page table size:");
n = src.nextInt();
p = new int[n];
System.out.println("Enter elements in page table:");
for (int i = 0; i < n; i++)
p[i] = src.nextInt();
System.out.println("Enter page frame size:");
m = src.nextInt();
fr = new int[m];
fs = new int[m];
}
// Method to display the current frame
void display() {
System.out.println();
for (i = 0; i < m; i++) {
if (fr[i] == -1)
System.out.print("[ ] ");
else
System.out.print("[" + fr[i] + "] ");
}
System.out.println();
}
// Method to implement the LRU page replacement algorithm
void lru() {
// Initialize the frame array with -1
for (i = 0; i < m; i++) {
fr[i] = -1;
}
// Start processing the page table
for (j = 0; j < n; j++) {
flag1 = 0;
flag2 = 0;
// Check if the page is already in the frame
for (i = 0; i < m; i++) {
if (fr[i] == p[j]) {
flag1 = 1;
flag2 = 1;
break;
}
}
// Check for an empty frame
if (flag1 == 0) {
for (i = 0; i < m; i++) {
if (fr[i] == -1) {
fr[i] = p[j];
flag2 = 1;
break;
}
}
}
// If no empty frame, replace the least recently used page
if (flag2 == 0) {
for (i = 0; i < 3; i++)
fs[i] = 0;
for (k = j - 1, l = 1; l <= frsize - 1; l++, k--) {
for (i = 0; i < 3; i++) {
if (fr[i] == p[k])
fs[i] = 1;
}
}
for (i = 0; i < 3; i++) {
if (fs[i] == 0)
index = i;
}
fr[index] = p[j];
pf++;
}
// Display the current page
System.out.print("Page: " + p[j]);
display();
}
System.out.println("\nNumber of page faults: " + pf);
}
// Main method to execute the program
public static void main(String args[]) {
LruAlgo a = new LruAlgo();
a.read();
a.lru();
a.display();
}
}
