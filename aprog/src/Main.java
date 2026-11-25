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
        uso(nomes, redesSociais, utilizacao, numPessoas, numRedesSociais);
        usoDiario(utilizacao, nomes, numPessoas, numRedesSociais);
        atividade(nomes, redesSociais, utilizacao, numPessoas, numRedesSociais);
        acimaMediaTodasRedes(utilizacao, nomes, numPessoas, numRedesSociais);
        rede(nomes, redesSociais, utilizacao, numPessoas, numRedesSociais);
        lerPercentagensDeUso(nomes, redesSociais, utilizacao, numPessoas, numRedesSociais);
        perfis(nomes, utilizacao, numPessoas, numRedesSociais);


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
    public static void usoDiario(int[][] utilizacao, String[] nomes, int numPessoas, int numRedesSociais) {
        System.out.println("c) Daily usage");
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
    public static void acimaMediaTodasRedes(int[][] utilizacao, String[] nomes, int numPessoas, int numRedesSociais) {
        System.out.println("e) Users consistently above average");
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
    // f)
    public static void rede(String[] nomes, String[] redesSociais, int[][] utilizacao, int  numPessoas, int numRedesSociais) {
        System.out.println("f) Most used social network");

        for (int j = 0; j < numPessoas; j++) {
            int maior = maior1(utilizacao, j, numRedesSociais);
            System.out.printf("%-12s : ", nomes[j]);

            for (int i = 0; i < numRedesSociais; i++) {
                if (utilizacao[j][i] == maior) {
                    System.out.printf("[%s] ", redesSociais[i]);
                }
            }
            System.out.println();
        }
        System.out.println();

        //g
        public static void lerPercentagensDeUso (String[]nomes, String[]redesSociais,int[][] utilizacao, int numPessoas,
        int numRedesSociais)
        {
            System.out.println("g) Percentage of use");
            System.out.printf("%-12s", "");
            for (int j = 0; j < numRedesSociais; j++) {
                System.out.printf("%12s", redesSociais[j]);
            }
            System.out.println();
            for (int i = 0; i < numPessoas; i++) {
                int soma = 0;
                for (int j = 0; j < numRedesSociais; j++) {
                    soma += utilizacao[i][j];
                }
                System.out.printf("%-12s", nomes[i]);
                for (int j = 0; j < numRedesSociais; j++) {
                    double percentagem = (double) (utilizacao[i][j] * 100) / soma;
                    System.out.printf("%12.2f%%", percentagem);
                }
                System.out.println("");
            }
        }
    }
    //h
    public static void perfis (String[] nomes, int [][]utilizacao, int numPessoas, int numRedesSociais) {
        System.out.println("h) Users Profile");

        System.out.printf("%-12s%-12s%-12s%s%n", "Moderate", "Regular", "Intensive", "Very Intensive");
        System.out.println("-----------|-----------|-----------|-----------");
        for (int j = 0; j < numPessoas; j++) {
            int soma = 0;
            for (int i = 0; i < numRedesSociais; i++) {
                soma += utilizacao[j][i];
            }
            if (soma < 90) {
                System.out.printf("%-12s", nomes[j]);
            } else if (soma >= 90 && soma <= 180) {
                System.out.printf("%-12s%-12s", "", nomes[j]);
            } else if (soma > 180 && soma <= 300) {
                System.out.printf("%-12s%-12s%-12s", "", "", nomes[j]);
            } else if (soma > 300) {
                System.out.printf("%-12s%-12s%-12s%-12s", "", "", "", nomes[j]);
            }
            System.out.println("");
        }
    }
    //i
    public static void histrograma(int [][]utilizacao, int numPessoas, int numRedesSociais){
        System.out.println("i) Use of social networks");
        int totalMinutos = 0;
        int contadorRedesSociais = 0;
        for (int i = 0; i < numPessoas; i++){
            for (int j = 0; j < numRedesSociais; j++){
                totalMinutos += utilizacao[i][j];
            }
        }
        while (contadorRedesSociais < numRedesSociais){
            int totalMinutosRedeSocial = 0;
            for (int j = 0; j < numPessoas; j++){
                for (int i = 0; i < numRedesSociais; j++){
                    totalMinutosRedeSocial += utilizacao[i][j];
                }
            }
            double percentagem

        }

    }
}
