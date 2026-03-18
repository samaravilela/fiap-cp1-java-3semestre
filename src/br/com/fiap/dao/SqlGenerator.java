package br.com.fiap.dao;

import br.com.fiap.annotation.Descricao;
import br.com.fiap.entity.FuncionarioEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.lang.reflect.Field;

/**
 * Gera SQL automaticamente via Reflection a partir da entidade com @Descricao e @Column.
 */
public class SqlGenerator {

    public static String gerarSelectTodos(Class<?> entityClass) {
        String tabela = obterNomeTabela(entityClass);
        if (tabela == null) return "-- Erro: classe sem @Table ou @Descricao";
        return "SELECT * FROM " + tabela;
    }

    public static String gerarSelectComColunas(Class<?> entityClass) {
        String tabela = obterNomeTabela(entityClass);
        if (tabela == null) return "-- Erro ao obter tabela";
        StringBuilder colunas = new StringBuilder();
        for (Field f : entityClass.getDeclaredFields()) {
            Column col = f.getAnnotation(Column.class);
            if (col != null && !col.name().isEmpty()) {
                if (colunas.length() > 0) colunas.append(", ");
                colunas.append(col.name());
            }
        }
        return colunas.length() > 0 ? "SELECT " + colunas + " FROM " + tabela : "SELECT * FROM " + tabela;
    }

    private static String obterNomeTabela(Class<?> entityClass) {
        Table t = entityClass.getAnnotation(Table.class);
        if (t != null && !t.name().isEmpty()) return t.name();
        Descricao d = entityClass.getAnnotation(Descricao.class);
        if (d != null) return "TB_FUNCIONARIO";
        return entityClass.getSimpleName();
    }

    public static String gerarInsert(Object entity) {
        Class<?> clazz = entity.getClass();
        String tabela = obterNomeTabela(clazz);
        if (tabela == null) return "-- Erro";
        StringBuilder colunas = new StringBuilder();
        StringBuilder valores = new StringBuilder();
        try {
            for (Field f : clazz.getDeclaredFields()) {
                if (f.getAnnotation(Id.class) != null) continue;
                Column col = f.getAnnotation(Column.class);
                if (col == null) continue;
                f.setAccessible(true);
                Object valor = f.get(entity);
                if (colunas.length() > 0) { colunas.append(", "); valores.append(", "); }
                colunas.append(col.name());
                valores.append(formatarValor(valor));
            }
        } catch (IllegalAccessException e) {
            return "-- Erro: " + e.getMessage();
        }
        return "INSERT INTO " + tabela + " (" + colunas + ") VALUES (" + valores + ")";
    }

    public static String gerarUpdate(Object entity) {
        Class<?> clazz = entity.getClass();
        String tabela = obterNomeTabela(clazz);
        if (tabela == null) return "-- Erro";
        String idCol = null;
        Object idVal = null;
        StringBuilder sets = new StringBuilder();
        try {
            for (Field f : clazz.getDeclaredFields()) {
                f.setAccessible(true);
                Column col = f.getAnnotation(Column.class);
                if (col == null) continue;
                Object valor = f.get(entity);
                if (f.getAnnotation(Id.class) != null) {
                    idCol = col.name();
                    idVal = valor;
                } else {
                    if (sets.length() > 0) sets.append(", ");
                    sets.append(col.name()).append(" = ").append(formatarValor(valor));
                }
            }
            if (idCol == null || idVal == null) return "-- Sem @Id";
            return "UPDATE " + tabela + " SET " + sets + " WHERE " + idCol + " = " + formatarValor(idVal);
        } catch (IllegalAccessException e) {
            return "-- Erro: " + e.getMessage();
        }
    }

    public static String gerarDelete(Class<?> entityClass, Object id) {
        String tabela = obterNomeTabela(entityClass);
        if (tabela == null) return "-- Erro";
        try {
            for (Field f : entityClass.getDeclaredFields()) {
                if (f.getAnnotation(Id.class) != null) {
                    Column col = f.getAnnotation(Column.class);
                    String colName = col != null ? col.name() : "ID_FUNCIONARIO";
                    return "DELETE FROM " + tabela + " WHERE " + colName + " = " + formatarValor(id);
                }
            }
        } catch (Exception ignored) {}
        return "DELETE FROM " + tabela + " WHERE ID_FUNCIONARIO = " + formatarValor(id);
    }

    private static String formatarValor(Object valor) {
        if (valor == null) return "NULL";
        if (valor instanceof Number || valor instanceof Boolean) return valor.toString();
        return "'" + valor.toString().replace("'", "''") + "'";
    }
}
