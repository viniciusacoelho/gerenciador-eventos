import java.util.*;

public class Main {
    public static final Scanner scanner = new Scanner(System.in);

    // Eventos ->
    // TODO: provavelmente criar uma variável public static int totalEventosCadastrados = 0;
    public static int eventoCadastrado = 0;
    public static List<String> nomeEventoCadastrado = new ArrayList<>();
    public static List<String> dataEventoCadastrado = new ArrayList<>();
    public static List<String> localEventoCadastrado = new ArrayList<>();
    public static List<Integer> capacidadeEventoCadastrado = new ArrayList<>();

    // Participantes ->
    public static int totalParticipantesCadastrados = 0;
    public static int[] participanteCadastrado = new int[100];
    public static List<String> nomeParticipanteCadastrado = new ArrayList<>();
    public static List<Integer> contatoParticipateCadastrado = new ArrayList<>();

    public static void main(String[] args) {
        // login();
        String[] menu = {
                "Cadastrar Evento", "Listar Eventos", "Inscrever Participante",
                "Exibir Participantes Inscritos", "Confirmar Presença de Participante", "Sair"
        };

        do {
            System.out.println("--------------------------------------------");
            System.out.println("           Gerenciador de Eventos");
            System.out.println("--------------------------------------------");

            for (int i = 0; i < menu.length; i++) {
                System.out.println(i + 1 + " - " + menu[i]);
            }
            System.out.println("--------------------------------------------");
            System.out.println("Digite uma opção:");

            try {
                int opcao = scanner.nextInt();
                scanner.nextLine();
                System.out.println("--------------------------------------------");

                switch (opcao) {
                    case 1 -> cadastrarEvento();
                    case 2 -> listarEventos();
                    case 3 -> inscreverParticipante();
                    case 4 -> exibirParticipantesInscritos();
                    case 5 -> confirmarPresencaParticipante();
                    case 6 -> {
                        System.out.println("Saindo...");
                        System.exit(0);
                    }
                    default -> System.out.println("Opção inválida!");
                }
            } catch (InputMismatchException e) {
                System.err.println("[ERRO]: Digite um número!");
            }
        } while (true);
    }

    public static void login() {
        System.out.println("--------------------------------------------");
        System.out.println("                   Login");
        System.out.println("--------------------------------------------");
        System.out.println("Digite seu e-mail:");
        String email = scanner.nextLine();
        System.out.println("Digite sua senha:");
        String senha = scanner.nextLine();
    }

    public static void cadastrarEvento() {
        System.out.println("Digite o nome do evento:");
        String nome = scanner.nextLine();
        System.out.println("Digite a data do evento:");
        String data = scanner.nextLine();
        System.out.println("Digite o local do evento:");
        String local = scanner.nextLine();
        System.out.println("Digite a capacidade do evento:");
        int capacidade = scanner.nextInt();

        nomeEventoCadastrado.add(nome.toLowerCase());
        dataEventoCadastrado.add(data);
        localEventoCadastrado.add(local);
        capacidadeEventoCadastrado.add(capacidade);

        eventoCadastrado++;
    }

    public static void listarEventos() {
        if (eventoCadastrado == 0) {
            System.out.println("Nenhum evento cadastrado anteriormente.");
            return;
        }

        for (int i = 0; i < eventoCadastrado; i++) {
            System.out.println("Evento " + i + 1 + ":");
            System.out.println("Nome: " + nomeEventoCadastrado.get(i));
            System.out.println("Data: " + dataEventoCadastrado.get(i));
            System.out.println("Local: " + localEventoCadastrado.get(i));
            System.out.println("Capacidade: " + capacidadeEventoCadastrado.get(i));
            System.out.println("Número de Participantes: " + participanteCadastrado[i]);
            System.out.println("--------------------------------------------");
        }
    }

    public static void inscreverParticipante() {
        System.out.println("Digite o nome do participante:");
        String nome = scanner.nextLine();
        nomeParticipanteCadastrado.add(nome.toLowerCase());

        do {
            try {
                System.out.println("Digite o contato do participante:");
                int contato = scanner.nextInt();
                contatoParticipateCadastrado.add(contato);
                scanner.nextLine();
                break;
            } catch (InputMismatchException e) {
                System.err.println("[ERRO]: Digite um número!");
            }
        } while (true);

        do {
            System.out.println("--------------------------------------------");
            listarEventos();
            System.out.println("Digite o nome do evento para inscrever:");
            String nomeEvento = scanner.nextLine();

            if (nomeEventoCadastrado.contains(nomeEvento)) {
                break;
            } else {
                System.out.println("Nome do evento inválido, digite novamente.");
            }
        } while (true);

        for (int i = 0; i < eventoCadastrado; i++) {
            if (participanteCadastrado[i] < capacidadeEventoCadastrado.get(i)) {
                participanteCadastrado[i]++;
                totalParticipantesCadastrados++;
                System.out.println("Participante " + nome + " inscrito com sucesso!");
            } else {
                System.out.println("Evento lotado, não foi possível inscrever o participante.");
                return;
            }
        }
    }

    public static void exibirParticipantesInscritos() {
        if (totalParticipantesCadastrados == 0) {
            System.out.println("Nenhum participante cadastrado anteriormente.");
            return;
        }

        for (int i = 0; i < totalParticipantesCadastrados; i++) {
            System.out.println("Nome: " + nomeParticipanteCadastrado.get(i));
            System.out.println("Contato: " + contatoParticipateCadastrado.get(i));
            // TODO: Evento relacionado ao participante
            // System.out.println("Evento: " + nomeEventoCadastrado.get(i));
        }
    }

    public static void confirmarPresencaParticipante() {
        if (totalParticipantesCadastrados == 0) {
            System.out.println("Nenhum participante cadastrado anteriormente.");
            return;
        }
        System.out.println("--------------------------------------------");
        exibirParticipantesInscritos();
        System.out.println("--------------------------------------------");
        System.out.println("Digite nome do participante:");
        String nome = scanner.nextLine();

        do {
            System.out.println("Você deseja confirmar presença do participante " + nome + "? (s/n)");
            String resposta = scanner.nextLine();

            if (resposta.equalsIgnoreCase("s") || resposta.equalsIgnoreCase("sim")) {
                System.out.println("Presença confirmada com sucesso!");
                break;
            } else if (resposta.equalsIgnoreCase("n") || resposta.equalsIgnoreCase("não") || resposta.equalsIgnoreCase("nao")) {
                System.out.println("Presença confirmada com sucesso!");
                break;
            } else {
                System.out.println("Respota inválida! Digite novamente.");
            }
         } while (true);
    }

}

/*
    Funções:
    - Login
    - Cadastrar Evento (Nome, Data, Local, Capacidade)
    - Listar Eventos
    - Inscrever Participante (Nome, Contato)
    - Exibir Participantes Inscritos
    - Confirmar Presença de Participante
*/