package br.com.fiap.beans;

/**
 * Classe que representa um funcionário: nome, horas trabalhadas e valor por hora.
 */
public class Funcionario {

    private String nome;
    private double horasTrabalhadas;
    private double valorPorHora;

    public Funcionario() {
    }

    public Funcionario(String nome, double horasTrabalhadas, double valorPorHora) {
        this.nome = nome;
        this.horasTrabalhadas = horasTrabalhadas;
        this.valorPorHora = valorPorHora;
    }

    public double calcularSalario() {
        return horasTrabalhadas * valorPorHora;
    }

    public void imprimirInformacao() {
        System.out.println("--- Funcionário ---");
        System.out.println("Nome: " + nome);
        System.out.println("Horas trabalhadas: " + horasTrabalhadas);
        System.out.println("Valor por hora: R$ " + valorPorHora);
        System.out.println("Salário final: R$ " + calcularSalario());
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public double getHorasTrabalhadas() { return horasTrabalhadas; }
    public void setHorasTrabalhadas(double horasTrabalhadas) { this.horasTrabalhadas = horasTrabalhadas; }
    public double getValorPorHora() { return valorPorHora; }
    public void setValorPorHora(double valorPorHora) { this.valorPorHora = valorPorHora; }
}
