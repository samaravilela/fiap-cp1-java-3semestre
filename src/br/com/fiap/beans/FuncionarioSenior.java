package br.com.fiap.beans;

/**
 * Funcionário sênior: recebe bônus a cada 15 horas trabalhadas.
 */
public class FuncionarioSenior extends Funcionario {

    private static final double HORAS_POR_BONUS = 15.0;
    private static final double VALOR_BONUS = 200.0;

    public FuncionarioSenior() {
    }

    public FuncionarioSenior(String nome, double horasTrabalhadas, double valorPorHora) {
        super(nome, horasTrabalhadas, valorPorHora);
    }

    @Override
    public double calcularSalario() {
        double salarioBase = super.calcularSalario();
        int quantidadeBonus = (int) (getHorasTrabalhadas() / HORAS_POR_BONUS);
        return salarioBase + (quantidadeBonus * VALOR_BONUS);
    }

    @Override
    public void imprimirInformacao() {
        System.out.println("--- Funcionário Sênior ---");
        System.out.println("Nome: " + getNome());
        System.out.println("Horas trabalhadas: " + getHorasTrabalhadas());
        System.out.println("Valor por hora: R$ " + getValorPorHora());
        System.out.println("Bônus (a cada 15h): R$ " + VALOR_BONUS);
        System.out.println("Salário final: R$ " + calcularSalario());
    }
}
