# 🛡️ Sistema Antifraude para Transações Financeiras (Versão Java)
### ☕ Engenharia de Software, Validação em Tempo Real e Persistência de Dados

<p align="center">
  <img src="https://img.shields.io/badge/Java-JDK_21%2B-ED8B00?style=for-the-badge&logo=java&logoColor=white" />
  <img src="https://img.shields.io/badge/MySQL-8.0%2B-4479A1?style=for-the-badge&logo=mysql&logoColor=white" />
  <img src="https://img.shields.io/badge/JDBC-Connector-007396?style=for-the-badge" />
  <img src="https://img.shields.io/badge/IDE-IntelliJ_IDEA-000000?style=for-the-badge&logo=intellijidea&logoColor=white" />
</p>

---

## || Sobre o Projeto & Abordagem Comparativa ||
Este projeto faz parte de um estudo prático comparativo entre **Java** e **Python** no desenvolvimento de um **Motor de Risco e Análise Antifraude para Transações Financeiras**. 

O objetivo principal foi construir uma aplicação funcional capaz de interceptar transações, processar regras de validação em tempo real e armazenar o histórico completo (com status de aprovação, suspeita ou rejeição) no banco de dados **MySQL**.

>  **Esta é a versão implementada em Java.** Para conferir a versão idêntica construída em Python, acesse o repositório paralelo: **[sistema-antifraude-python](https://github.com/liviamirandagit/sistema-antifraude-python)**.

---

## 📸 

<img width="1392" height="1600" alt="WhatsApp Image 2026-08-06 at 17 29 19" src="https://github.com/user-attachments/assets/56ff80ca-a604-4987-b4ab-77aaf6b22012" />
<img width="1600" height="923" alt="WhatsApp Image 2026-08-06 at 17 35 22" src="https://github.com/user-attachments/assets/d2173fa2-0a8b-4b60-9811-0ce9327389e7" />
<img width="1801" height="630" alt="Captura de tela de 2026-08-06 17-32-09" src="https://github.com/user-attachments/assets/8ec6b43e-8730-4c8c-86bd-f6dc58b95d54" />


<p align="center">
  <img src="COLE_O_LINK_DA_SUA_IMAGEM_AQUI" alt="Execução do Sistema Antifraude em Java" width="100%">
</p>

---

## >> Por que Java nesta Arquitetura?
Na análise transacional e no setor bancário, a robustez, a tipagem forte e o controle rigoroso de exceções do **Java** oferecem uma estrutura ideal para o motor de risco central. 

* **IDE e Produtividade:** Desenvolvido no **IntelliJ IDEA**, garantindo refatoração segura, controle estrito de tipos e gerenciamento de dependências.
* **Modelo Relacional & Persistência:** Integração via JDBC com MySQL, aplicando conceitos avançados de modelagem e consultas baseados nas melhores práticas de bancagem de dados.
* **Tratamento de Exceções:** Arquitetura focada em isolamento de falhas e tratamento de concorrência transacional.

---

##  Fluxo de Validação de Risco

```text
┌─────────────────────────┐      ┌─────────────────────────┐      ┌─────────────────────────┐
│  1. ENTRADA DA TRANSAÇÃO│ ───► │ 2. MOTOR DE REGRAS JAVA │ ───► │  3. CÁLCULO DE SCORE    │
│   (Payload / Payload)   │      │ (Validações em Tempo Real)│      │  (Análise de Padrão)    │
└─────────────────────────┘      └─────────────────────────┘      └─────────────────────────┘
                                                                               │
                                                                               ▼
┌─────────────────────────┐                                       ┌─────────────────────────┐
│ 5. HISTÓRICO NO MYSQL   │ ◄──────────────────────────────────── │ 4. DECISÃO TRANSAIONAL  │
│ (Persistência com Status)│                                       │ (Aprovação ou Bloqueio) │
└─────────────────────────┘                                       └─────────────────────────┘<img width="1392" height="1600" alt="WhatsApp Image 2026-08-06 at 17 29 19" src="https://github.com/user-attachments/assets/c5dfe998-db1e-46f9-8e58-7b8a2377b9ae" />
