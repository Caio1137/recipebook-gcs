# Procedimentos manuais no GitHub

Este arquivo lista o que ainda precisa ser feito diretamente na sua conta GitHub.
O projeto local ja contem backend, frontend, branches, tags, CI e CHANGELOG.

## 1. Criar repositorio publico

No GitHub, crie um repositorio publico:

- Repository name: `recipebook-gcs`
- Description: `Sistema RecipeBook com GCS aplicada - SENAI`
- Visibility: `Public`
- License: `None`

Depois, na pasta do projeto, conecte o remoto e envie:

```powershell
git remote add origin https://github.com/SEU_USUARIO/recipebook-gcs.git
git push -u origin main
git push -u origin develop
git push origin feature/listar-receitas
git push origin feature/cadastrar-receita
git push origin v0.1.0
git push origin v1.0.0
git push origin v1.0.1
```

## 2. Configurar Branch Protection

No GitHub: `Settings` -> `Branches` -> `Add branch ruleset`.

Configure para o branch `main`:

- Require a pull request before merging
- Require approvals
- Require status checks to pass before merging
- Require branches to be up to date before merging
- Do not allow bypassing the above settings

Quando o CI aparecer na lista de checks, selecione:

- `CI - RecipeBook / Build Backend (Spring Boot + Java 17)`
- `CI - RecipeBook / Build Frontend (Angular + Node 20)`

## 3. Criar Issue #1

Titulo:

```text
[FEATURE] RF01 - Listar Receitas
```

Descricao:

```text
## Descricao
Implementar a listagem de receitas culinarias.

## Relacionado a Especificacao
Requisito Funcional: RF01 - Listar Receitas

## Criterios de Aceite
- CA01.1: Exibir nome, categoria e tempo de preparo em cada card
- CA01.2: Ordenar por data de cadastro (mais recentes primeiro)
- CA01.3: Mensagem 'Nenhuma receita cadastrada' quando lista vazia
- CA01.4: Cada card com link para visualizacao de detalhes

## Impacto Tecnico
- Backend: RecipeController.java, RecipeRepository.java, Recipe.java
- Frontend: recipe-list/, recipe.service.ts, recipe.model.ts

## Estimativa
Backend: 1h | Frontend: 2h | Testes: 30min
```

Labels: `enhancement`, `approved`.

## 4. Criar Issue #2

Titulo:

```text
[FEATURE] RF03 - Cadastrar Receita
```

Descricao:

```text
## Descricao
Implementar o formulario de cadastro de receitas com validacao
e o endpoint de criacao no backend.

## Relacionado a Especificacao
Requisito Funcional: RF03 - Cadastrar Receita

## Criterios de Aceite
- CA03.1: Formulario com todos os campos obrigatorios
- CA03.2: Validar campos antes de enviar (Reactive Forms + @Valid)
- CA03.3: Mensagens de erro especificas por campo
- CA03.4: Botao 'Salvar' desabilitado se formulario invalido
- CA03.5: Apos salvar, redirecionar para listagem
- CA03.6: Mensagem de sucesso apos cadastro

## Regras de Negocio
- RN01: Nome unico
- RN05: tempoPreparo >= 1
- RN06: porcoes >= 1

## Impacto Tecnico
- Backend: RecipeController (POST /api/receitas), @Valid na entidade
- Frontend: recipe-form/, ReactiveFormsModule, Validators
```

Labels: `enhancement`, `approved`.

## 5. Criar Issue #3

Titulo:

```text
[BUG] Formulario aceita porcoes = 0, violando RN06
```

Descricao:

```text
## Descricao do Bug
O formulario de cadastro nao bloqueia o envio quando o campo
'Porcoes' recebe o valor 0 (zero).

## Versao afetada
v1.0.0

## Passos para Reproduzir
1. Acessar a tela de cadastro de receita
2. Preencher todos os campos corretamente
3. No campo 'Porcoes', digitar 0
4. Clicar em 'Salvar'

## Resultado atual
A receita e salva com porcoes = 0

## Resultado esperado
- Botao 'Salvar' deve estar desabilitado
- Exibir: 'Minimo 1 porcao'

## Regras violadas
- RN06: Porcoes minimo 1
- CA03.4: Botao Salvar desabilitado se formulario invalido

## Impacto
Critico - dados invalidos no banco de dados
```

Labels: `bug`, `hotfix`, `production`.

## 6. Criar Pull Requests

PR #1:

- Base: `develop`
- Compare: `feature/listar-receitas`
- Title: `feat: implementar RF01 - Listagem de Receitas`
- Description: incluir `Closes #1`

PR #2:

- Base: `develop`
- Compare: `feature/cadastrar-receita`
- Title: `feat: implementar RF03 - Cadastrar Receita`
- Description: incluir `Closes #2`

## 7. Criar GitHub Release

No GitHub: `Releases` -> `Create a new release`.

- Tag: `v1.0.0`
- Target: `main`
- Release title: `RecipeBook v1.0.0 - Primeira Versao Estavel`
- Description: copie a secao `[1.0.0]` do `CHANGELOG.md`

## 8. Screenshot bonus

No GitHub: `Insights` -> `Network`.

Tire um screenshot do grafico e envie junto com o link do repositorio, se o professor aceitar o bonus.
