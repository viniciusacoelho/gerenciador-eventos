import java.util.*;

public class Main {
    public static final Scanner scanner = new Scanner(System.in);

    // Eventos ->
    // TODO: provavelmente criar uma variável public static int totalEventosCadastrados = 0;
    public static int totalEventosCadastrados = 0;
    public static int[] eventoCadastrado = new int[100];
    public static List<String> nomeEventoCadastrado = new ArrayList<>();
    public static List<String> dataEventoCadastrado = new ArrayList<>();
    public static List<String> localEventoCadastrado = new ArrayList<>();
    public static List<Integer> capacidadeEventoCadastrado = new ArrayList<>();

    // Participantes ->
    public static int totalParticipantesCadastrados = 0;
    public static int[] participanteCadastrado = new int[100];
    public static List<String> nomeParticipanteCadastrado = new ArrayList<>();
    public static List<Integer> contatoParticipateCadastrado = new ArrayList<>();
    public static List<Integer> idEventoCadastrado = new ArrayList<>();
    public static int idEvento;

    public static void main(String[] args) {
        login();
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

            try {
                System.out.println("--------------------------------------------");
                System.out.println("Digite uma opção:");
                int opcao = scanner.nextInt();
                scanner.nextLine();
                System.out.println("--------------------------------------------");

                switch (opcao) {
                    case 1 -> cadastrarEvento();
                    case 2 -> {
                        listarEventos();
                        System.out.println("Eventos listados com sucesso!");
                    }
                    case 3 -> inscreverParticipante();
                    case 4 -> {
                        System.out.println("Participantes inscritos exibidos com sucesso!");
                        exibirParticipantesInscritos();
                    }
                    case 5 -> confirmarPresencaParticipante();
                    case 6 -> {
                        System.out.println("Saindo...");
                        System.exit(0);
                    }
                    default -> System.out.println("Opção inválida! Tente novamente.");
                }
            } catch (InputMismatchException e) {
                System.err.println("[ERRO]: Digite um número!");
                scanner.nextLine();
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
        System.out.println("Seja bem-vindo " + email + "!");
    }

    public static void cadastrarEvento() {
        System.out.println("Digite o nome do evento:");
        String nome = scanner.nextLine();
        System.out.println("Digite a data do evento:");
        String data = scanner.nextLine();
        System.out.println("Digite o local do evento:");
        String local = scanner.nextLine();

        int capacidade = 0;
        do {
            try {
                System.out.println("Digite a capacidade do evento:");
                capacidade = scanner.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.err.println("[ERRO]: Digite um número!");
                scanner.nextLine();
            }
        } while (true);

        nomeEventoCadastrado.add(nome);
        dataEventoCadastrado.add(data);
        localEventoCadastrado.add(local);
        capacidadeEventoCadastrado.add(capacidade);

        totalEventosCadastrados++;
        idEventoCadastrado.add(totalEventosCadastrados);
        System.out.println("Evento " + nome + " cadastrado com sucesso!");
    }

    public static void listarEventos() {
        if (totalEventosCadastrados == 0) {
            System.out.println("Nenhum evento cadastrado anteriormente.");
            return;
        }

        for (int i = 0; i < totalEventosCadastrados; i++) {
            System.out.println("Evento " + (i + 1) + ":");
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

        int contato;
        do {
            try {
                System.out.println("Digite o contato do participante:");
                contato = scanner.nextInt();
                scanner.nextLine();
                break;
            } catch (InputMismatchException e) {
                System.err.println("[ERRO]: Digite um número!");
                scanner.nextLine();
            }
        } while (true);

        do {
            System.out.println("--------------------------------------------");
            listarEventos();
            System.out.println("Digite o ID do evento para inscrever o participante:");
            idEvento = scanner.nextInt();

            if (idEventoCadastrado.contains(idEvento)) {
                if (participanteCadastrado[idEvento - 1] < capacidadeEventoCadastrado.get(idEvento - 1)) {
                    nomeParticipanteCadastrado.add(nome);
                    contatoParticipateCadastrado.add(contato);

                    participanteCadastrado[idEvento - 1]++;
                    totalParticipantesCadastrados++;
                    System.out.println("Participante " + nome + " inscrito no evento " + nomeEventoCadastrado.get(participanteCadastrado[idEvento]) + " com sucesso!");
                } else {
                    System.out.println("Evento lotado! Não foi possível inscrever o participante.");
                }

                break;
            } else {
                System.out.println("ID do evento inválido! Tente novamente.");
            }
        } while (true);
    }

    public static void exibirParticipantesInscritos() {
        if (totalParticipantesCadastrados == 0) {
            System.out.println("Nenhum participante cadastrado anteriormente.");
            return;
        }

        for (int i = 0; i < totalParticipantesCadastrados; i++) {
            System.out.println("Participante " + (i + 1));
            System.out.println("Nome: " + nomeParticipanteCadastrado.get(i));
            System.out.println("Contato: " + contatoParticipateCadastrado.get(i));
            // TODO: Evento relacionado ao participante
            System.out.println("Evento: " + nomeEventoCadastrado.get(eventoCadastrado[idEvento]));
            System.out.println("--------------------------------------------");
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
            resposta = resposta.toLowerCase();

            if (resposta.equalsIgnoreCase("s") || resposta.equalsIgnoreCase("sim")) {
                System.out.println("Presença confirmada com sucesso!");
                break;
            } else if (resposta.equalsIgnoreCase("n") || resposta.equalsIgnoreCase("não") || resposta.equalsIgnoreCase("nao")) {
                System.out.println("Presença não foi confirmada.");
                break;
            } else {
                System.out.println("Resposta inválida! Tente novamente.");
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