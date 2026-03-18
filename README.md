# CheckPoint 1 - Java, JPA e Annotations (FIAP)

Projeto no **padrão IntelliJ** (sem Maven). CheckPoint 1 da disciplina de Java Advanced.

## Estrutura do projeto (IntelliJ)

```
cp1_java_3sem/
├── cp1_java_3sem.iml      # Módulo IntelliJ
├── src/
│   ├── META-INF/
│   │   └── persistence.xml    # Persistence Unit e conexão Oracle
│   └── br/com/fiap/
│       ├── annotation/
│       │   └── Descricao.java
│       ├── beans/
│       │   ├── Funcionario.java
│       │   ├── FuncionarioSenior.java
│       │   ├── FuncionarioEstagiario.java
│       │   └── FuncionarioGerente.java
│       ├── dao/
│       │   ├── SqlGenerator.java
│       │   └── FuncionarioDao.java
│       ├── entity/
│       │   └── FuncionarioEntity.java
│       ├── main/
│       │   └── Main.java      # Classe principal – botão Run aqui
│       └── util/
│           └── JpaUtil.java
└── README.md
```

## Configurar no IntelliJ

1. **Abrir o projeto:** File → Open → selecione a pasta `cp1_java_3sem` (ou abra o `.iml`).
2. **Garantir que `src` é Source Root:** botão direito em `src` → Mark Directory as → Sources Root (se ainda não estiver).
3. **Adicionar as bibliotecas (JPA, Hibernate, Oracle):**
   - File → Project Structure (Ctrl+Alt+S) → Libraries → **+** → **From Maven**.
   - Adicione (uma por vez ou busque pelo nome):
     - `jakarta.persistence:jakarta.persistence-api:3.1.0`
     - `org.hibernate.orm:hibernate-core:6.4.4.Final`
     - `com.oracle.database.jdbc:ojdbc11:23.4.0.24.05`
     - `com.zaxxer:HikariCP:5.1.0`
   - Ou baixe os JARs e use **+** → **Java** e selecione as pastas/JARs.
4. **Executar:** abra `br.com.fiap.main.Main`, clique no ícone ▶ ao lado de `public static void main` e escolha **Run 'Main.main()'**.

## Conexão Oracle

| Parâmetro | Valor |
|-----------|--------|
| **URL**   | oracle.fiap.com.br |
| **Porta** | 1521 |
| **SID**   | ORCL |
| **User**  | RM566133 |
| **Senha** | 280201 |

Configurada em `src/META-INF/persistence.xml` (Persistence Unit: `cp1-oracle`).

## Requisitos atendidos (CheckPoint)

- Classe funcionário com nome, horas trabalhadas, valor por hora; `calcularSalario()` e `imprimirInformacao()`.
- Subclasse funcionário sênior (bônus a cada 15h); sobrescrita de `calcularSalario` e `imprimirInformacao`.
- Outras subclasses: Estagiário e Gerente.
- Anotação `@Descricao`, entidade com colunas anotadas, geração de SQL via Reflection.
- Entity Manager, Persistence Unit e Persistence Context com Hibernate para Oracle.
- Retorno do código SQL em cada etapa do CRUD (Create, Read, Update, Delete).
