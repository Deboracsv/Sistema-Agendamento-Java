import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SistemaAgendamento sistema = new SistemaAgendamento();

        ArrayList<Paciente> pacientes = new ArrayList<>();
        Paciente paciente1 = new Paciente("Daniela da Costa Viana", "Feminino", 20);
        Paciente paciente2 = new Paciente("Deinara da Costa Viana", "Feminino", 25);
        Paciente paciente3 = new Paciente("Débora da Costa Viana", "Feminino", 23);

        pacientes.add(paciente1);
        pacientes.add(paciente2);
        pacientes.add(paciente3);


        ArrayList<Profissional> profissionais = new ArrayList<>();
        Profissional profissional1 = new Profissional("Ana", "Dermatologia");
        Profissional profissional2 = new Profissional("Maria", "Fisioterapia");
        Profissional profissional3 = new Profissional("Carlos", "Psiquiatria");

        profissionais.add(profissional1);
        profissionais.add(profissional2);
        profissionais.add(profissional3);

        Paciente pacienteEscolhido = null;
        boolean validar = false;

        while (!validar) {
            System.out.println("#########----------- Escolha o paciente -----------######### \n -Daniela da Costa Viana - 1. \n -Deinara da Costa Viana - 2. \n -Débora da Costa Viana - 3.");
            int paciente;

            try {
                paciente = scanner.nextInt();

            } catch (InputMismatchException e) {
                System.out.println("❗ ATENÇÃO: Digite somente números ❗");
                scanner.nextLine();
                continue;
            }
            switch (paciente) {
                case 1:
                    pacienteEscolhido = pacientes.get(0);
                    validar = true;
                    break;

                case 2:
                    pacienteEscolhido = pacientes.get(1);
                    validar = true;
                    break;

                case 3:
                    pacienteEscolhido = pacientes.get(2);
                    validar = true;
                    break;

                default:
                    System.out.println("-- Tente novamente, digite o numero correspondente. --");
                    validar = false;
                    break;
            }

        }

        Profissional profissionalEscolhido = null;

        boolean opcaoValida = false;
        while (!opcaoValida) {
            System.out.println("========= Olá, me informe qual o especialista?: =========" +
                    "\n Dermatologia digite 1. \n Fisioterapia digite 2. \n Psiquiatria digite 3.\n" +
                    " ----------------------------------------------"
            );

            int profissional;

            try {
                profissional = scanner.nextInt();

            } catch (InputMismatchException e) {
                System.out.println("❗ Digite somente números ❗");
                scanner.nextLine();
                continue;
            }
            switch (profissional) {
                case 1:
                    profissionalEscolhido = profissionais.get(0);
                    opcaoValida = true;
                    break;
                case 2:
                    profissionalEscolhido = profissionais.get(1);
                    opcaoValida = true;
                    break;
                case 3:
                    profissionalEscolhido = profissionais.get(2);
                    opcaoValida = true;
                    break;
                default:
                    System.out.println("❗ Valor não correspondente, tente novamente");
                    opcaoValida = false;
                    break;
            }
        }

        scanner.nextLine();
        System.out.println("----------------------------------------------");

        LocalDate dataConsulta = null;
        LocalTime horaConsulta = null;

        DateTimeFormatter dataFormatada = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        while (dataConsulta == null) {
            System.out.println("\uD83D\uDCC5\u200B Digite a data da consulta (DD/MM/YYYY):");
            String data = scanner.nextLine();


            try {
                dataConsulta = LocalDate.parse(data, dataFormatada);

                if (dataConsulta.isBefore(LocalDate.now())) {
                    System.out.println("❌ Não é possível agendar para uma data passada.");
                    dataConsulta = null;

                }
            }catch (DateTimeParseException e) {
                System.out.println("❌ Erro: data invalida, verifique a data novamente.");
            }

        }
        System.out.println("----------------------------------------------");

        while (horaConsulta == null) {
            System.out.println("⌚\u200B Digite o horário da consulta (HH:MM):");
            String horario = scanner.nextLine();

            try {
                horaConsulta = LocalTime.parse(horario);
            } catch (DateTimeParseException e) {
                System.out.println("❌ Erro: horario invalido, verifique a hora novamente.");
            }
        }

        System.out.println("----------------------------------------------");

        LocalDateTime dataHora = LocalDateTime.of(dataConsulta, horaConsulta);

        boolean consultaMarcada = sistema.marcarConsulta(pacienteEscolhido, profissionalEscolhido, dataHora);


        if(consultaMarcada) {
            int cancelamento = 0;

            sistema.listarAgendamentos();
            System.out.println("----------------------------------------------");
            boolean opcaoCancelamento = false;

            System.out.println("Deseja cancelar a consulta da: " + pacienteEscolhido.getNome());
            System.out.println("Sim digite 1: \nNão digite 2:");


            while (!opcaoCancelamento) {
                try {
                    cancelamento = scanner.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("digte somente numero.");
                    scanner.nextLine();
                    continue;
                }
                switch (cancelamento) {
                    case 1:
                        sistema.cancelarConsulta(pacienteEscolhido);
                        opcaoCancelamento = true;
                        break;
                    case 2:
                        System.out.println("Consulta mantida.");
                        opcaoCancelamento = true;
                        break;

                    default:
                        System.out.println("Opção Invalida");
                        opcaoCancelamento = false;
                        break;
                }

            }
            System.out.println("----------------------------------------------");
            sistema.listarAgendamentos();
        }

    }
}


