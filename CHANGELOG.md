# Changelog
Todas as mudancas notaveis neste projeto serao documentadas aqui.
Formato: https://keepachangelog.com

## [Unreleased]

## [1.0.0] - 2026-05-18

### Added
- RF01: Listagem de receitas com cards contendo nome, categoria e tempo de preparo (#1)
- RF01: Ordenacao por data de cadastro, mais recentes primeiro (#1)
- RF01: Mensagem informativa quando nao ha receitas cadastradas (#1)
- RF02: Busca de receitas por nome em tempo real
- RF03: Formulario de cadastro com Reactive Forms e validacao (#2)
- RF03: Mensagens de erro especificas por campo (#2)
- RF03: Redirecionamento para listagem apos cadastro com sucesso (#2)
- RF04: Tela de detalhes com ingredientes e modo de preparo formatados
- RF05: Exclusao com confirmacao e retorno para listagem
- CI: Pipeline GitHub Actions com build backend e frontend
- DataLoader com 3 receitas iniciais para demonstracao

### Technical
- Entidade Recipe com validacoes Bean Validation
- API REST para listar, buscar por ID, criar e excluir receitas
- Monorepo com pastas /backend e /frontend
- Conventional Commits adotado como padrao do projeto

## [0.1.0] - 2026-05-18

### Added
- Configuracao inicial do repositorio
- Estrutura /backend e /frontend
- README.md e CHANGELOG.md iniciais
- Branch develop configurado
