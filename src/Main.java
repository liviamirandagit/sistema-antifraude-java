import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

//record é uma estrutura mais leve para tipagem estática, para representar dados

    record Transacao
        (String id,
         String cliente,
         double valor,
         int horario,
         int tentativasRecentes,
         boolean reconhecerDispositivo,
         boolean localizacaoSuspeita
     ){}

    record ResultadoAnalise
            (String idTransacao,
             String cliente,
             int scoreRisco,
             double valor,
             String status,
             String recomendacao,
             List<String> alertas
    ){}

    void main() {
        //dados ficticios
        Transacao transacaoSuspeita = new Transacao(
                "TX-98412",
                "Lívia",
                3500.00,
                2,
                4,
                false,
                true);

        //executando a análise e gerando ps resultados
        ResultadoAnalise resultado = analisarTransacao(transacaoSuspeita);

        System.out.println("|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|");
        System.out.println(" - RELATÓRIO DE ANÁLISE ANTIFRAUDE - ");
        System.out.println("ID da Transação : " + resultado.idTransacao());
        System.out.println("Cliente         : " + resultado.cliente());
        System.out.println("Score de Risco  : " + resultado.scoreRisco() + " pontos");
        System.out.println("Status Final    : " + resultado.status());
        System.out.println("Recomendação    : " + resultado.recomendacao());
        System.out.println("Alertas Disparados:");

        for (String alerta : resultado.alertas()) {
            System.out.println(" ->" + alerta);
        }
        System.out.println("|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|");

        salvarNoBanco(transacaoSuspeita, resultado);
    }

    ResultadoAnalise analisarTransacao(Transacao transacao) {
        int score = 0;
        List<String> motivosDeAlerta = new ArrayList<>();

        if (transacao.valor() > 2000.00) {
            score += 50;
            motivosDeAlerta.add("valor acima do limite de segurança");
        }
        if (transacao.horario() >= 23 || transacao.horario() <= 5) {
            score += 40;
            motivosDeAlerta.add(" Transação realizada em horário suspeito");
        }
        if (transacao.tentativasRecentes() >= 3) {
            score += 30;
            motivosDeAlerta.add(" Quantidade de tentativas de transação suspeitas ");
        }
        if (!transacao.reconhecerDispositivo()) {
            score += 25;
            motivosDeAlerta.add(" Novo dispositivo não reconhecido foi vinculado");
        }
        if (transacao.localizacaoSuspeita()) {
            score += 35;
            motivosDeAlerta.add(" Transação realizada em local suspeito");
        }

        String status;
        String recomendacao;
        if (score >= 70) {
            status = " TRANSAÇÃO BLOQUEADA";
            recomendacao = " Entre em contato com o banco para verificar a transação";
        } else if (score >= 40) {
            status = " TRANSAÇÃO EM ANÁLISE";
            recomendacao = " O pagamento sendo processado aguarde...";
        } else {
            status = " TRANSAÇÃO APROVADA COM SUCESSO";
            recomendacao = " O comprovante será emitido em breve";
        }
        //Retorno dos resultados com análise pornta
        return new ResultadoAnalise(transacao.id(), transacao.cliente(), score, transacao.valor(), status, recomendacao, motivosDeAlerta);

    }

    void salvarNoBanco(Transacao t, ResultadoAnalise r) {
        Properties props = new Properties();
        try (FileInputStream file = new FileInputStream ("config.properties")){
            props.load(file);
        }
        catch (IOException e) {
            System.err.println("\n[Erro] Não foi possível ler o arquivo config.properties:" + e.getMessage());
            return;
        }
        String url = props.getProperty("db.url");
        String usuario = props.getProperty("db.user");
        String senha = props.getProperty("db.password");

        String sql = """
                    INSERT INTO transacoes (
                        id, cliente, valor, horario, tentativasRecentes,
                        dispositivo_reconhecido, localizacao_suspeita,
                        score_risco, status, recomendacao
                    ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;
        try (Connection conn = DriverManager.getConnection(url, usuario, senha);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, t.id());
            pstmt.setString(2, t.cliente());
            pstmt.setDouble(3, t.valor());
            pstmt.setInt(4, t.horario());
            pstmt.setInt(5, t.tentativasRecentes());
            pstmt.setBoolean(6, t.reconhecerDispositivo());
            pstmt.setBoolean(7, t.localizacaoSuspeita());
            pstmt.setInt(8, r.scoreRisco());
            pstmt.setString(9, r.status().trim());
            pstmt.setString(10, r.recomendacao().trim());
            pstmt.executeUpdate();

            System.out.println("\n[SQL] Transação registrada no banco com sucesso!");

        } catch (SQLException e) {
            System.out.println("\"\\n[SQL Erro] Falha ao conectar ou salvar no banco:" + e.getMessage());

        }
    }