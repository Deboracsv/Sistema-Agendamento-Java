

import java.time.LocalDateTime;


public class Agendamento {
    private Paciente paciente;
    private Profissional profissional;
    private LocalDateTime dataHora;

    public Agendamento(Paciente paciente, Profissional profissional, LocalDateTime dataHora) {
        this.paciente = paciente;
        this.profissional = profissional;
        this.dataHora = dataHora;
    }


    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Profissional getProfissional() {
        return profissional;
    }

    public void setProfissional(Profissional profissional) {
        this.profissional = profissional;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }


}
