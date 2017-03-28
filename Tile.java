import java.util.Scanner;

public class Tile {

        public static void main(String[] args) {
                Scanner s = new Scanner(System.in);

                int a[][] = new int[4][4];

                for (int i=0; i<4; i++){
                        //String number = s.next();
                        for (int j=0; j<4; j++){
                                a[j][i] = s.nextInt();
                        }
                }
                int mode = s.nextInt();
                if (mode == 0) fliphorizontal(a);
                if (mode == 1) {
                        flipx(a);
                        fliphorizontal(a);
                }
                if (mode == 3) {
                        flipx(a);
                }
                shift(a);
                shift(a);
                shift(a);
                merge(a);
                shift(a);
                shift(a);
                if (mode == 0) fliphorizontal(a);
                if (mode == 1) {
                        fliphorizontal(a);
                        flipx(a);
                }
                if (mode == 3) {
                        flipx(a);
                }

                for (int i=0; i<4; i++){
                        for (int j=0; j<4; j++){
                                System.out.print(a[j][i] + " ");
                        }
                        System.out.println();
                }

        }



        public static void shift(int[][] a){
                int temp;
                for (int i=0; i<4; i++){                //row
                        for (int j=1; j<4; j++){        //column
                                if (a[j][i] == 0) {
                                        temp = a[j-1][i];
                                        a[j-1][i] = 0;
                                        a[j][i] = temp;
                                }
                        }
                }
        }

        public static void merge(int[][] a){
                int temp;
                for (int i=0; i<4; i++){                //row
                        for (int j=3; j>0; j--){        //column
                                if (a[j][i] == a[j-1][i] && a[j][i] != 0) {
                                        temp = a[j-1][i];
                                        a[j-1][i] = 0;
                                        a[j][i] = temp*2;
                                }
                        }
                }
        }

        public static void fliphorizontal(int[][] a){
                int temp;
                for (int i=0; i<4; i++){
                        for (int j=0; j<2; j++){
                                temp = a[j][i];
                                a[j][i] = a[3-j][i];
                                a[3-j][i] = temp;
                        }
                }
        }
        public static void flipx(int[][]a){
                int temp;
                for (int i=0; i<4; i++){
                        for (int j=0; j<i; j++){
                                temp = a[i][j];
                                a[i][j] = a[j][i];
                                a[j][i] = temp;
                        }
                }
        }
        public static void flipvertical(int[][] a){
                int temp;
                for (int i=0; i<4; i++){
                        for (int j=0; j<2; j++){
                                temp = a[i][j];
                                a[i][j] = a[i][3-j];
                                a[i][3-j] = temp;
                        }
                }
        }
        public static int[][] copy(int[][] a){
                int b[][] = new int[a.length][a[0].length];
                for (int i=0; i<a[0].length; i++){
                        for (int j=0; j<a.length; j++){
                                b[i][j] = a[i][j];
                        }
                }
                return b;
        }
}

