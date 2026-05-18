# Changelog
Registro das versoes entregues no projeto RecipeBook.

## [Unreleased]

## [1.0.1] - 2026-05-18

### Fixed
- Corrigida a validacao do campo Porcoes para nao aceitar valor 0 (#3)
- Botao Salvar fica desabilitado quando a quantidade de porcoes e invalida

## [1.0.0] - 2026-05-18

### Added
- RF01: Listagem de receitas com nome, categoria e tempo de preparo (#1)
- RF01: Receitas ordenadas por data de cadastro, com as mais recentes primeiro (#1)
- RF01: Mensagem para quando ainda nao existem receitas cadastradas (#1)
- RF02: Busca de receitas por nome em tempo real
- RF03: Formulario para cadastrar novas receitas com validacao (#2)
- RF03: Mensagens de erro nos campos obrigatorios (#2)
- RF03: Retorno para a listagem depois do cadastro (#2)
- RF04: Tela de detalhes com ingredientes e modo de preparo
- RF05: Exclusao de receita com confirmacao
- GitHub Actions configurado para validar backend e frontend
- Carga inicial com 3 receitas para facilitar os testes

### Tecnico
- Entidade Recipe com validacoes no backend
- API REST para listar, buscar por ID, criar e excluir receitas
- Projeto organizado em backend e frontend
- Historico de commits seguindo o padrao combinado na atividade

## [0.1.0] - 2026-05-18

### Added
- Configuracao inicial do repositorio
- Estrutura inicial do backend e do frontend
- README.md e CHANGELOG.md criados
- Branch develop configurado
