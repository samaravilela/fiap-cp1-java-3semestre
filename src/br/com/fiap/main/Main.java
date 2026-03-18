package br.com.fiap.main;

import br.com.fiap.beans.*;
import br.com.fiap.dao.FuncionarioDao;
import br.com.fiap.dao.SqlGenerator;
import br.com.fiap.entity.FuncionarioEntity;
import br.com.fiap.util.JpaUtil;

import java.util.List;

/**
 * Classe principal de execução - CheckPoint 1: Java, JPA e Annotations.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("=== CheckPoint 1 - Java JPA e Annotations ===\n");

        System.out.println("--- Informações dos funcionários (beans) ---");
        new Funcionario("Maria", 40, 50.0).imprimirInformacao();
        new FuncionarioSenior("João", 30, 80.0).imprimirInformacao();
        new FuncionarioEstagiario("Ana", 20, 30.0).imprimirInformacao();
        new FuncionarioGerente("Carlos", 44, 100.0).imprimirInformacao();

        System.out.println("\n--- SQL gerado automaticamente (Reflection) ---");
        System.out.println(SqlGenerator.gerarSelectTodos(FuncionarioEntity.class));
        System.out.println(SqlGenerator.gerarSelectComColunas(FuncionarioEntity.class));

        FuncionarioDao dao = new FuncionarioDao();
        try {
            FuncionarioEntity ent = new FuncionarioEntity("Maria Silva", 40.0, 50.0, "PADRAO");
            ent = dao.create(ent);
            System.out.println("Funcionário criado com ID: " + ent.getId());

            List<FuncionarioEntity> todos = dao.readAll();
            System.out.println("Total no BD: " + todos.size());
            for (FuncionarioEntity e : todos) {
                System.out.println("  ID: " + e.getId() + " | " + e.getNome() + " | " + e.getHorasTrabalhadas() + "h | R$ " + e.getValorPorHora());
            }

            if (!todos.isEmpty()) {
                FuncionarioEntity paraAtualizar = todos.get(0);
                paraAtualizar.setHorasTrabalhadas(44.0);
                dao.update(paraAtualizar);
                System.out.println("Funcionário atualizado.");
            }
        } catch (Exception e) {
            System.err.println("Erro ao acessar BD: " + e.getMessage());
            e.printStackTrace();
        } finally {
            JpaUtil.close();
        }

        System.out.println("\n--- Fim ---");
    }
}
