CREATE DATABASE IF NOT EXISTS sistema_antifraude;
USE sistema_antifraude;

CREATE TABLE IF NOT EXISTS transacoes (
                                          id VARCHAR(50) PRIMARY KEY,
    cliente VARCHAR(100) NOT NULL,
    valor DECIMAL(10,2) NOT NULL,
    horario INT NOT NULL,
    tentativasRecentes INT NOT NULL DEFAULT 0,
    localizacao_suspeita BOOLEAN NOT NULL,
    dispositivo_reconhecido BOOLEAN NOT NULL,
    score_risco INT NOT NULL,
    status VARCHAR(30) NOT NULL,
    recomendacao VARCHAR(255) NOT NULL,
    criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );