package dominio;

public class Plano {
    private int codigo;
    private String nome;
    private String descricao;
    private double valor;
    private int duracaoMeses;

    // Construtor padrão
    public Plano() {
        this.codigo = 0;
        this.nome = "";
        this.descricao = "";
        this.valor = 0.0;
        this.duracaoMeses = 0;
    }

    // Construtor com parâmetros
    public Plano(int codigo, String nome, String descricao, double valor, int duracaoMeses) {
        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
        this.duracaoMeses = duracaoMeses;
    }

    // Getters e Setters
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getDuracaoMeses() {
        return duracaoMeses;
    }

    public void setDuracaoMeses(int duracaoMeses) {
        this.duracaoMeses = duracaoMeses;
    }

    // Sobrescrevendo toString
    @Override
    public String toString() {
        return "Plano{" +
                "codigo=" + codigo +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", valor=" + valor +
                ", duracaoMeses=" + duracaoMeses +
                '}';
    }

    // Sobrescrevendo equals para comparação lógica
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Plano plano = (Plano) obj;
        return codigo == plano.codigo &&
                Double.compare(plano.valor, valor) == 0 &&
                duracaoMeses == plano.duracaoMeses &&
                nome.equals(plano.nome) &&
                descricao.equals(plano.descricao);
    }

    // Sobrescrevendo hashCode
    @Override
    public int hashCode() {
        return java.util.Objects.hash(codigo, nome, descricao, valor, duracaoMeses);
    }
}
