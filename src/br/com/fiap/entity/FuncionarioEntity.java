package br.com.fiap.entity;

import br.com.fiap.annotation.Descricao;
import jakarta.persistence.*;

@Entity
@Table(name = "TB_FUNCIONARIO")
@Descricao(descricao = "Tabela de funcionários - nome, horas trabalhadas, valor por hora e tipo")
public class FuncionarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_funcionario")
    @SequenceGenerator(name = "seq_funcionario", sequenceName = "SEQ_FUNCIONARIO", allocationSize = 1)
    @Column(name = "ID_FUNCIONARIO")
    private Long id;

    @Column(name = "NM_FUNCIONARIO", nullable = false, length = 200)
    private String nome;

    @Column(name = "QTD_HORAS_TRABALHADAS", nullable = false)
    private Double horasTrabalhadas;

    @Column(name = "VL_POR_HORA", nullable = false)
    private Double valorPorHora;

    @Column(name = "TP_FUNCIONARIO", length = 50)
    private String tipoFuncionario;

    public FuncionarioEntity() {
    }

    public FuncionarioEntity(String nome, Double horasTrabalhadas, Double valorPorHora, String tipoFuncionario) {
        this.nome = nome;
        this.horasTrabalhadas = horasTrabalhadas;
        this.valorPorHora = valorPorHora;
        this.tipoFuncionario = tipoFuncionario;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public Double getHorasTrabalhadas() { return horasTrabalhadas; }
    public void setHorasTrabalhadas(Double horasTrabalhadas) { this.horasTrabalhadas = horasTrabalhadas; }
    public Double getValorPorHora() { return valorPorHora; }
    public void setValorPorHora(Double valorPorHora) { this.valorPorHora = valorPorHora; }
    public String getTipoFuncionario() { return tipoFuncionario; }
    public void setTipoFuncionario(String tipoFuncionario) { this.tipoFuncionario = tipoFuncionario; }
}
