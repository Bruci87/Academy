package dominio;

public class Aluno {
    private int matricula;
    private String nome;
    private String email;

    // Construtor padrão
    public Aluno() {
        this.matricula = 0;
        this.nome = "";
        this.email = "";
    }

    // Construtor com parâmetros
    public Aluno(int matricula, String nome, String email) {
        this.matricula = matricula;
        this.nome = nome;
        this.email = email;
    }

    // Getters e Setters
    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Sobrescrevendo toString
    @Override
    public String toString() {
        return "Aluno{" +
                "matricula=" + matricula +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    // Sobrescrevendo equals para comparação lógica
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Aluno aluno = (Aluno) obj;
        return matricula == aluno.matricula &&
                nome.equals(aluno.nome) &&
                email.equals(aluno.email);
    }

    // Sobrescrevendo hashCode
    @Override
    public int hashCode() {
        return java.util.Objects.hash(matricula, nome, email);
    }
}
