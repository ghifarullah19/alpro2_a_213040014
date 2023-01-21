package Matriks;

public class ProseduralMatriks {

    static void isiMatriks(int[][] a, int m, int n) {
        java.util.Scanner sc = new java.util.Scanner(System.in);
        for (int b = 0; b < m; b++) {
            for (int k = 0; k < n; k++) {
                System.out.format("Masukan %d %d ", b, k);
                a[b][k] = sc.nextInt();
            }
        }
        sc.close();
    }

    static void printMatriks(int[][] matriks, int m, int n) {
        for (int b = 0; b < m; b++) {
            for (int k = 0; k < n; k++) {
                System.out.print(matriks[b][k] + "\t");
            }
            System.out.println();
        }
    }

    static void sum(int[][] sum, int[][] matriksPertama, int[][] matriksKedua, int m, int n) {
        for (int b = 0; b < m; b++) {
            for (int k = 0; k < n; k++) {
                sum[b][k] = matriksPertama[b][k] + matriksKedua[b][k];
            }
        }
        System.out.println("\nPenjumlahan Matriks Matriks");
    }

    static void subst(int[][] subst, int[][] matriksPertama, int[][] matriksKedua, int m, int n) {
        for (int b = 0; b < m; b++) {
            for (int k = 0; k < n; k++) {
                subst[b][k] = matriksPertama[b][k] - matriksKedua[b][k];
            }
        }
        System.out.println("\nPengurangan Matriks");
    }

    static void multi(int[][] c, int[][] matriksPertama, int[][] matriksKedua, int baris1, int kolom1, int kolom2) {
        for (int i = 0; i < baris1; i++) {
            for (int j = 0; j < kolom2; j++) {
                c[i][j] = 0;
                for (int k = 0; k < kolom1; k++) {
                    c[i][j] = c[i][j] + matriksPertama[i][k] * matriksKedua[k][j];
                }
            }
        }
        System.out.println("\nPerkalian Matriks");
    }

    public static void main(String[] args) {

        java.util.Scanner sc = new java.util.Scanner(System.in);

        int[][] matriksPertama = new int[10][10];
        int[][] matriksKedua = new int[10][10];
        int[][] c = new int[10][10];

        System.out.println("Isi Matriks Pertama");
        System.out.print("Masukan Jumlah Baris: ");
        int baris1 = sc.nextInt();
        System.out.print("Masukan Jumlah Kolom: ");
        int kolom1 = sc.nextInt();

        isiMatriks(matriksPertama, baris1, kolom1);
        printMatriks(matriksPertama, baris1, kolom1);

        System.out.println("Isi Matriks Kedua");
        System.out.print("Masukan Jumlah Baris: ");
        int baris2 = sc.nextInt();
        System.out.print("Masukan Jumlah Kolom: ");
        int kolom2 = sc.nextInt();

        isiMatriks(matriksKedua, baris2, kolom2);
        printMatriks(matriksKedua, baris2, kolom2);

        sum(c, matriksPertama, matriksKedua, baris2, kolom2);
        printMatriks(c, baris2, kolom2);

        subst(c, matriksPertama, matriksKedua, baris2, kolom2);
        printMatriks(c, baris2, kolom2);

        multi(c, matriksPertama, matriksKedua, baris1, kolom1, kolom2);
        printMatriks(c, baris2, kolom2);

        sc.close();
    }
}
