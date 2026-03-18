package br.com.fiap.beans;

/**
 * Funcionário gerente: bônus de 15% sobre o salário base.
 */
public class FuncionarioGerente extends Funcionario {

    private static final double PERCENTUAL_BONUS = 0.15;

    public FuncionarioGerente() {
    }

    public FuncionarioGerente(String nome, double horasTrabalhadas, double valorPorHora) {
        super(nome, horasTrabalhadas, valorPorHora);
    }

    @Override
    public double calcularSalario() {
        double salarioBase = super.calcularSalario();
        return salarioBase + (salarioBase * PERCENTUAL_BONUS);
    }

    @Override
    public void imprimirInformacao() {
        System.out.println("--- Funcionário Gerente ---");
        System.out.println("Nome: " + getNome());
        System.out.println("Horas trabalhadas: " + getHorasTrabalhadas());
        System.out.println("Valor por hora: R$ " + getValorPorHora());
        System.out.println("Bônus gestão (15%): " + (PERCENTUAL_BONUS * 100) + "%");
        System.out.println("Salário final: R$ " + calcularSalario());
    }
}
