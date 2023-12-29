package NuralNetworParcetro;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        final double Alpha = 1;

        int[][] x = {
            {1, 1, 1},
            {1, 1, -1},
            {1, -1, 1},
            {1, -1, -1}
        };

        int[] t = {1, -1, -1, -1};
        double[] w = {0, 0, 0}; //w0,w1,w2
        System.out.println("bobot Awal");
        for (int i = 0; i < w.length; i++) {
            System.out.print(w[i] + ";");

        }
        System.out.println();
        System.out.println("-----------------------------");
        boolean training = true;
        int epoch = 1;

        while (training) {
            System.out.println("-----------------------------------------");
            System.out.println("EPOCH = " + (epoch++));
            System.out.println("X0;X1;X2;t;net;y=f;(x;delta_w0;delta_w1;delta_w2;w0,w1,w2");
            int sama = 0;
            for (int j = 0; j < x.length; j++) {
                double net = 0;
                for (int i = 0; i < x[j].length; i++) {
                    int xi = x[j][i];
                    System.out.print(xi + ";");
                    double wi = w[i];
                    net = net + (xi * wi);
                }
                int y = -2;
                if (net > 0) {
                    y = 1;

                } else if (net == 0) {
                    y = 0;
                } else if (net < 0) {
                    y = -1;

                }
                double target = t[j];
                System.out.print((int) target + ";");
                System.out.print((int) net + ";");
                System.out.println(y + ";");
                if (target != y) {

                    for (int i = 0; i < x[j].length; i++) {
                        double xi = x[j][i];
                        double delta = Alpha * target * xi;
                        //melakukan update bobot
                        w[i] = w[i] + delta;
                        System.out.print(delta + ";");

                    }

                } else {
                    for (int i = 0; i < x[j].length; i++) {
                        System.out.print("0;");
                    }
                    sama++;
                }
                //print bobot baru
                for (int i = 0; i < w.length; i++) {
                    System.out.print((int)w[i] + ";");

                }
                System.out.println();

            }
            if (sama == x.length) {
                training = false;
                break;

            }
        }
        System.out.println("---------------------------------");
        System.out.println("TESTING");
        while(true){
            System.out.println("PILIHAN : [0] = EXIT, [1] = TESTING");
            int pilihan = new Scanner(System.in).nextInt();
            if (pilihan == 0) {
                break;
                
            }else if (pilihan == 1) {
                System.out.println("------------------------");
                System.out.println("Testing");
                int []xTraining = new int[3];
                xTraining[0]= -1;
                for (int i = 1; i < xTraining.length; i++) {
                    System.out.print("x"+i+": ");
                    xTraining[i]= new Scanner(System.in).nextInt();
                    
                }
                double net = 0;
                for (int i = 0; i < xTraining.length; i++) {
                    int xi = xTraining[i];
                    double wi = w[i];
                    net = net + (xi * wi);
                }
                //ACTIVATION
                int y = -2;
                if (net > 0) {
                    y = 1;

                } else if (net == 0) {
                    y = 0;
                } else if (net < 0) {
                    y = -1;

                }
                //result
                System.out.println("result = "+ y);
                System.out.println("-----------------------------");
                
            }
        }
    }

}
