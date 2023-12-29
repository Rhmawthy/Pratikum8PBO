package BlackBox_N07;

import java.util.Scanner;
public class MyCode{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = 1;
        for (int i = 0; i < a; i++) {
            for (int j = 0; j < b; j++) {
                boolean cetak = false;
                System.out.print((c++)+"\t");
                //System.out.print(cetak);
            }
            System.out.println();
        }
    }
}
