package br.com.fiap.beans;

/**
 * Funcionário estagiário: valor por hora reduzido (50%) e carga máxima de 30h.
 */
public class FuncionarioEstagiario extends Funcionario {

    private static final double FATOR_REDUCAO = 0.5;
    private static final double HORAS_MAXIMAS = 30.0;

    public FuncionarioEstagiario() {
    }

    public FuncionarioEstagiario(String nome, double horasTrabalhadas, double valorPorHora) {
        super(nome, Math.min(horasTrabalhadas, HORAS_MAXIMAS), valorPorHora * FATOR_REDUCAO);
    }

    @Override
    public void imprimirInformacao() {
        System.out.println("--- Funcionário Estagiário ---");
        System.out.println("Nome: " + getNome());
        System.out.println("Horas trabalhadas (máx " + HORAS_MAXIMAS + "h): " + getHorasTrabalhadas());
        System.out.println("Valor por hora (50%): R$ " + getValorPorHora());
        System.out.println("Salário final: R$ " + calcularSalario());
    }
}
