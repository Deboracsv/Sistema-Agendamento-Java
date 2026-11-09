import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SistemaAgendamento {
    // Lista que armazenará os agendamentos

    private List<Agendamento> agendamentos = new ArrayList<>();

    public boolean marcarConsulta(Paciente paciente, Profissional profissional, LocalDateTime dataHora) {
        LocalDateTime agora = LocalDateTime.now();

        if (dataHora.isBefore(agora)) {
            System.out.println("Não é possivel agendar para uma data passada.");
            return false;
        }

        Agendamento novoAgendamento = new Agendamento(paciente, profissional, dataHora);

        for (Agendamento agendamento : agendamentos) {


            if (agendamento.getProfissional() == profissional && agendamento.getDataHora().equals(dataHora)) {
                System.out.println("Já existe uma consulta marcada para esta data.");
                return false;
            }
        }

        agendamentos.add(novoAgendamento);
        return true;
    }

        public void listarAgendamentos(){

        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm");
        System.out.println("========== Consultas Marcadas ===========");
        for (Agendamento agendamento : agendamentos) {

            System.out.println("Paciente: " + agendamento.getPaciente().getNome());

            System.out.println("Idade: " + agendamento.getPaciente().getIdade());

            System.out.println("Sexo: " + agendamento.getPaciente().getSexo());

            System.out.println("Profissional: " + agendamento.getProfissional().getNome());

            System.out.println("Especialidade: " + agendamento.getProfissional().getEspecialidade());

            System.out.println("Data: " + agendamento.getDataHora().format(formatoData));

            System.out.println("Horario: " + agendamento.getDataHora().format(formatoHora));
          }
        }

    public void cancelarConsulta(Paciente paciente) {

        Iterator<Agendamento> iterator = agendamentos.iterator();

        while (iterator.hasNext()) {
            Agendamento agendamento = iterator.next();

            if (agendamento.getPaciente() == paciente) {
                iterator.remove();
                System.out.println("Consulta cancelada com sucesso.");
                return;

            }
        }
        System.out.println("Nenhuma consulta encontrada para esse paciente.");
    }

    }

