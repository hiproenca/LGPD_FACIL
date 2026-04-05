# 🛡️ LGPD Fácil - Aplicativo de Compliance e Maturidade

Um aplicativo Android nativo desenvolvido para democratizar e simplificar o acompanhamento da conformidade corporativa e pessoal com a **Lei Geral de Proteção de Dados Pessoais (LGPD)**. Este projeto funciona como um MVP focado em avaliação, estruturação de planos de ação e monitoramento contínuo de atualizações legislativas.

## 🎯 Objetivo e Impacto (ESG)
O projeto nasceu da necessidade de aplicar soluções tecnológicas ao pilar de **Governança (G)** do ecossistema ESG. O aplicativo auxilia na criação de processos internos claros para mitigação de riscos jurídicos, além de promover o pilar **Social (S)** ao educar e estimular práticas de proteção ao direito fundamental da privacidade de dados.

## ✨ Funcionalidades Principais
* **📊 Dashboard Reativo:** Painel central que exibe o índice de maturidade atualizado em tempo real.
* **📝 Diagnóstico Interativo:** Questionário que avalia as práticas atuais de proteção de dados da entidade/usuário e calcula a pontuação correspondente.
* **✅ Plano de Ação:** Gerenciador de tarefas integrado para acompanhar as etapas de adequação e mitigar riscos.
* **📡 Radar Legislativo:** Integração via API governamental para exibir, em tempo real, Projetos de Lei (PLs) recentes relacionados à "LGPD" e "Proteção de Dados".
* **📚 Hub Educativo:** Base de conhecimento com conceitos essenciais sobre a legislação.

## 🛠️ Tecnologias e Arquitetura
Este projeto foi construído seguindo as diretrizes e ferramentas mais modernas recomendadas para o ecossistema Android:

* **Linguagem:** [Kotlin](https://kotlinlang.org/)
* **Interface Gráfica:** [Jetpack Compose](https://developer.android.com/jetpack/compose) (UI Declarativa e Material Design)
* **Arquitetura:** MVVM (Model-View-ViewModel) para clara separação de responsabilidades.
* **Persistência de Dados Local:** [Room Database](https://developer.android.com/training/data-storage/room) gerenciando estados com Coroutines.
* **Consumo de API REST:** [Retrofit](https://square.github.io/retrofit/) e OkHttp para comunicação assíncrona com tratamento de queries.
* **Navegação:** Jetpack Navigation Compose.

## 🌐 Integração de Dados
O aplicativo consome a **API Oficial de Dados Abertos da Câmara dos Deputados** para o módulo de Radar Legislativo, utilizando filtros dinâmicos por palavras-chave (`keywords=LGPD, proteção de dados`) para entregar apenas resultados relevantes ao escopo jurídico de privacidade.

## 🚀 Como executar o projeto

1. Faça o clone deste repositório:
   ```bash
   git clone [https://github.com/SEU_USUARIO/lgpd-facil.git](https://github.com/SEU_USUARIO/lgpd-facil.git)
   Abra o projeto no Android Studio.

2. Aguarde o Gradle sincronizar todas as dependências.

3. Execute o aplicativo em um emulador ou dispositivo físico clicando no botão Run (Shift + F10).

4. Nota: O módulo "Radar Legislativo" exige conexão com a internet para consumir a API externa.

👨‍💻 Desenvolvedor
Desenvolvido como projeto autoral para o curso de Análise e Desenvolvimento de Sistemas (FIAP) com foco em arquitetura de soluções e processos corporativos.

Higor Proença

[LinkedIn](http://www.linkedin.com/in/higor-proenca)
