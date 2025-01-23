package dominio;

import java.time.LocalDate;

public class Matricula {
    private int codigo;
    private int matriculaAluno;
    private int codigoPlano;
    private LocalDate dataInicio;
    private LocalDate dataFim;

    // Construtor padrão
    public Matricula() {
        this.codigo = 0;
        this.matriculaAluno = 0;
        this.codigoPlano = 0;
        this.dataInicio = LocalDate.now();
        this.dataFim = LocalDate.now();
    }

    // Construtor com parâmetros
    public Matricula(int codigo, int matriculaAluno, int codigoPlano, LocalDate dataInicio, LocalDate dataFim) {
        this.codigo = codigo;
        this.matriculaAluno = matriculaAluno;
        this.codigoPlano = codigoPlano;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    // Getters e Setters
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getMatriculaAluno() {
        return matriculaAluno;
    }

    public void setMatriculaAluno(int matriculaAluno) {
        this.matriculaAluno = matriculaAluno;
    }

    public int getCodigoPlano() {
        return codigoPlano;
    }

    public void setCodigoPlano(int codigoPlano) {
        this.codigoPlano = codigoPlano;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    // Sobrescrevendo toString
    @Override
    public String toString() {
        return "Matricula{" +
                "codigo=" + codigo +
                ", matriculaAluno=" + matriculaAluno +
                ", codigoPlano=" + codigoPlano +
                ", dataInicio=" + dataInicio +
                ", dataFim=" + dataFim +
                '}';
    }

    // Sobrescrevendo equals para comparação lógica
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Matricula matricula = (Matricula) obj;
        return codigo == matricula.codigo &&
                matriculaAluno == matricula.matriculaAluno &&
                codigoPlano == matricula.codigoPlano &&
                dataInicio.equals(matricula.dataInicio) &&
                dataFim.equals(matricula.dataFim);
    }

    // Sobrescrevendo hashCode
    @Override
    public int hashCode() {
        return java.util.Objects.hash(codigo, matriculaAluno, codigoPlano, dataInicio, dataFim);
    }
}

