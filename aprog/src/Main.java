import java.util.Scanner;
public class Main {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        String[] nomes = new String[100];
        String[] redesSociais = new String[100];
        int[][] utilizacao = new int[100][100];

        int numPessoas = lerNomes(nomes);
        int numRedesSociais = lerNomes(redesSociais);

        lerMatriz(utilizacao, numPessoas, numRedesSociais);
        System.out.println("c) Daily usage");
        usoDiario(utilizacao, nomes, numPessoas, numRedesSociais);
        System.out.println("e) Users consistently above average");
        acimaMediaTodasRedes(utilizacao, nomes, numPessoas, numRedesSociais);
    }

    private static int lerNomes(String[] nomes) {
        String linha = sc.nextLine();
        String[] nomesLinha = linha.split(",");

        for (int i = 0; i < nomesLinha.length; i++) {
            nomes[i] = nomesLinha[i].trim();
        }
        return nomesLinha.length;
    }

    private static void lerMatriz(int[][] utilizacao, int numPessoas, int numRedesSociais) {
        for (int i = 0; i < numPessoas; i++) {
            for (int j = 0; j < numRedesSociais; j++) {
                utilizacao[i][j] = sc.nextInt();
            }
        }
    }
    // b)
    public static void uso(String[] nomes, String[] redesSociais, int[][] utilizacao, int numPessoas, int numRedesSociais) {
        System.out.println("b) Social Media Usage (minutes/day)");
        System.out.printf("%-12s", "");

        for (int j = 0; j < numRedesSociais; j++) {
            System.out.printf("%12s", redesSociais[j]);
        }
        System.out.println();

        for (int i = 0; i < numPessoas; i++) {
            System.out.printf("%-12s", nomes[i]);
            for (int j = 0; j < numRedesSociais; j++) {
                System.out.printf("%12d", utilizacao[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    // c)
    private static void usoDiario(int[][] utilizacao, String[] nomes, int numPessoas, int numRedesSociais) {
        for (int i = 0; i < numPessoas; i++) {
            int soma = 0;
            for (int j = 0; j < numRedesSociais; j++) {
                soma += utilizacao[i][j];
            }
            double media = (double) soma / numRedesSociais;
            System.out.printf("%-12s : %4d min (%.1f min average/network)%n", nomes[i], soma, media);
        }
    }
    // d)
    public static void atividade(String[] nomes, String[] redesSociais, int[][] utilizacao, int numPessoas, int numRedesSociais) {
        System.out.println("d) Most active Users");

        for (int j = 0; j < numRedesSociais; j++) {
            int maior = maiorTempoRede(utilizacao, j, numPessoas);
            System.out.printf("%-12s : ", redesSociais[j]);

            for (int i = 0; i < numPessoas; i++) {
                if (utilizacao[i][j] == maior) {
                    System.out.printf("\"%s\" ", nomes[i]);
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public static int maiorTempoRede(int[][] utilizacao, int colunaRede, int numPessoas) {
        int max = utilizacao[0][colunaRede];
        for (int i = 1; i < numPessoas; i++) {
            if (utilizacao[i][colunaRede] > max) {
                max = utilizacao[i][colunaRede];
            }
        }
        return max;
    }

    // e)
    private static void acimaMediaTodasRedes(int[][] utilizacao, String[] nomes, int numPessoas, int numRedesSociais) {
        double[] medias = new double[numRedesSociais];
        int contador = 0;
        for (int j = 0; j < numRedesSociais; j++) {
            int soma = 0;
            for (int i = 0; i < numPessoas; i++) {
                soma += utilizacao[i][j];
            }
            double media = (double) soma / numPessoas;
            medias[j] = media;
        }
        for (int i = 0; i < numPessoas; i++) {
            boolean acimaTodas = true;
            for (int j = 0; j < numRedesSociais; j++) {
                if (utilizacao[i][j] <= medias[j]) {
                    acimaTodas = false;
                }
            }
            if (acimaTodas) {
                System.out.println(nomes[i]);
                contador++;
            }
        }
        if (contador == 0) {
            System.out.println("none");
        }
    }
}
