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
        tabelaUtilizacao(nomes, redesSociais, utilizacao, numPessoas, numRedesSociais);
        usoDiario(utilizacao, nomes, numPessoas, numRedesSociais);
        utilizadoresMaisAtivos(nomes, redesSociais, utilizacao, numPessoas, numRedesSociais);
        acimaMediaTodasRedes(utilizacao, nomes, numPessoas, numRedesSociais);
        redesMaisUtilizadas(nomes, redesSociais, utilizacao, numPessoas, numRedesSociais);
        lerPercentagensDeUso(nomes, redesSociais, utilizacao, numPessoas, numRedesSociais);
        perfisUtilizadores(nomes, utilizacao, numPessoas, numRedesSociais);
        utilizadoresMaisEquilibrados(nomes, utilizacao, numPessoas, numRedesSociais);


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
    public static void tabelaUtilizacao(String[] nomes, String[] redesSociais, int[][] utilizacao, int numPessoas, int numRedesSociais) {
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
        System.out.println();
    }
    // d)
    public static void utilizadoresMaisAtivos(String[] nomes, String[] redesSociais, int[][] utilizacao, int numPessoas, int numRedesSociais) {
        if (numPessoas <= 0 || numRedesSociais <= 0) {
            return;
        }
        System.out.println("d) Most active Users");

        for (int j = 0; j < numRedesSociais; j++) {
            int maior = MaiorTempoRede(utilizacao, j, numPessoas);
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

    private static int MaiorTempoRede(int[][] utilizacao, int colunaRede, int numPessoas) {
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
        System.out.println();
    }
    // f)
    public static void redesMaisUtilizadas(String[] nomes, String[] redesSociais, int[][] utilizacao, int numPessoas, int numRedesSociais) {
        if (numPessoas <= 0 || numRedesSociais <= 0) {
            return;
        }
        System.out.println("f) Most used social network");

        for (int i = 0; i < numPessoas; i++) {
            int maior = MaiorTempoPessoa(utilizacao, i, numRedesSociais);
            System.out.printf("%-12s : ", nomes[i]);

            for (int j = 0; j < numRedesSociais; j++) {
                if (utilizacao[i][j] == maior) {
                    System.out.printf("[%s] ", redesSociais[j]);
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    private static int MaiorTempoPessoa(int[][] utilizacao, int linhaPessoa, int numRedesSociais) {
        int max = utilizacao[linhaPessoa][0];
        for (int j = 1; j < numRedesSociais; j++) {
            if (utilizacao[linhaPessoa][j] > max) {
                max = utilizacao[linhaPessoa][j];
            }
        }
        return max;
    }
    public static void lerPercentagensDeUso(String[] nomes, String[] redesSociais,int[][] utilizacao, int numPessoas, int numRedesSociais) {
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
    // h)
    public static void perfisUtilizadores(String[] nomes, int[][] utilizacao, int numPessoas, int numRedesSociais) {
        final int Limite_Moderate = 90;
        final int Limite_Regular = 180;
        final int Limite_Intensive = 300;

        System.out.println("h) Users profile");
        System.out.printf("%-12s%-12s%-12s%s%n", "Moderate", "Regular", "Intensive", "Very Intensive");
        System.out.println("-----------|-----------|-----------|-----------");

        for (int i = 0; i < numPessoas; i++) {
            int totalDiario = calcularTotalDiarioPessoa(utilizacao, i, numRedesSociais);
            int nivelPerfil = obterNivelPerfil(totalDiario, Limite_Moderate, Limite_Regular, Limite_Intensive);

            for (int k = 0; k < nivelPerfil; k++) {
                System.out.printf("%-12s", "");
            }
            System.out.println(nomes[i]);
        }
        System.out.println();
    }

    private static int calcularTotalDiarioPessoa(int[][] utilizacao, int linhaPessoa, int numRedesSociais) {
        int soma = 0;
        for (int j = 0; j < numRedesSociais; j++) {
            soma += utilizacao[linhaPessoa][j];
        }
        return soma;
    }

    private static int obterNivelPerfil(int totalDiario, int limiteModerate, int limiteRegular, int limiteIntensive) {
        if (totalDiario < limiteModerate) {
            return 0;
        } else if (totalDiario <= limiteRegular) {
            return 1;
        } else if (totalDiario <= limiteIntensive) {
            return 2;
        }
        return 3;
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
            double percentagem;

        }
    }
    // j)
    public static void utilizadoresMaisEquilibrados(String[] nomes, int[][] utilizacao, int numPessoas, int numRedesSociais) {
        if (numPessoas <= 0 || numRedesSociais <= 0) {
            return;
        }
        System.out.println("j) Most balanced users");

        int[] diferencas = new int[numPessoas];
        for (int i = 0; i < numPessoas; i++) {
            int maior = MaiorTempoPessoa(utilizacao, i, numRedesSociais);
            int menor = MenorTempo(utilizacao, i, numRedesSociais);
            diferencas[i] = maior - menor;
        }

        int menorDiferenca = MenorValor(diferencas);

        System.out.print("{");
        int cont = 0;
        for (int i = 0; i < numPessoas; i++) {
            if (diferencas[i] == menorDiferenca) {
                if (cont > 0) {
                    System.out.print(", ");
                }
                System.out.print("\"" + nomes[i] + "\"");
                cont++;
            }
        }
        System.out.println("}");
    }

    private static int MenorTempo(int[][] utilizacao, int linhaPessoa, int numRedesSociais) {
        int min = utilizacao[linhaPessoa][0];
        for (int j = 1; j < numRedesSociais; j++) {
            if (utilizacao[linhaPessoa][j] < min) {
                min = utilizacao[linhaPessoa][j];
            }
        }
        return min;
    }

    private static int MenorValor(int[] valores) {
        int menor = valores[0];
        for (int i = 1; i < valores.length; i++) {
            if (valores[i] < menor) {
                menor = valores[i];
            }
        }
        return menor;
    }
}

