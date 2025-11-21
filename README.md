# 📘 **Dog API – Automated Test Suite (Rest-Assured + JUnit 5 + Allure + GitHub Actions)**

Este repositório contém uma **suíte completa de testes automatizados de API** construída para validar os endpoints públicos da **Dog API** (`https://dog.ceo/api`), utilizando uma stack moderna e alinhada ao mercado:

* **Java 21 (LTS)**
* **JUnit 5**
* **Rest-Assured**
* **Allure Reports**
* **Maven**
* **GitHub Actions (CI)**

O objetivo é demonstrar boas práticas profissionais em automação de testes backend e CI/CD, garantindo **clareza, confiabilidade, rastreabilidade e escalabilidade**.

---

# 🚀 **1. Tecnologias Utilizadas**

| Tecnologia         | Função                                |
| ------------------ | ------------------------------------- |
| **Java 21**        | Linguagem base (LTS mais recente)     |
| **JUnit 5**        | Framework de testes                   |
| **Rest-Assured**   | Testes de API REST                    |
| **Allure Reports** | Relatórios ricos e navegáveis         |
| **Maven**          | Gerenciamento de dependências e build |
| **GitHub Actions** | Execução dos testes em CI             |

---

# 📂 **2. Estrutura do Projeto**

```
dog-api-qa-tests-agi/
 ├─ .github/workflows/api-tests.yml     # Pipeline CI
 ├─ src/
 │   └─ test/
 │       ├─ java/br/com/jhonattan/dogapi/
 │       │     ├─ BaseApiTest.java
 │       │     ├─ BreedsListTests.java
 │       │     ├─ BreedImagesTests.java
 │       │     └─ RandomImageTests.java
 │       └─ resources/
 │             └─ allure.properties
 ├─ .gitignore
 ├─ pom.xml
 └─ README.md
```

### ✔️ Destaques estruturais

* Testes **organizados por feature**
* Configurações centralizadas em **BaseApiTest**
* Logging global configurado para request/response
* Suporte total ao **Allure**
* Pipeline CI limpo e direto

---

# 🧪 **3. Escopo dos Testes**

A suíte cobre os principais endpoints da Dog API, incluindo cenários positivos e negativos.

## 🔸 **3.1. Listagem de raças**

Arquivo: `BreedsListTests.java`

Valida:

* Status code = 200
* Campo `"status" = "success"`
* Estrutura completa do JSON
* Verificação de que há raças retornadas

---

## 🔸 **3.2. Imagens por raça**

Arquivo: `BreedImagesTests.java`

### Cenários incluídos:

##### ✔️ Raças válidas (parametrizado)

* `hound`
* `pug`
* `bulldog`

Valida:

* Status code = 200
* `"status" = "success"`
* Lista não vazia
* URLs válidas

##### ✔️ Raças inválidas (parametrizado)

* `"invalidBreed"`
* `"doge"`
* `"1234"`

Valida:

* Status code = 404
* `"status" = "error"`
* Mensagem de erro correspondente

---

## 🔸 **3.3. Imagem aleatória**

Arquivo: `RandomImageTests.java`

* Teste repetido 3x
* Valida:

    * Status code = 200
    * `"status" = "success"`
    * URL termina com `.jpg`
    * URL inicia com HTTPS

---

# ⚙️ **4. Como Rodar Localmente**

### 4.1. Pré-requisitos

* JDK **21+**
* Maven **3.9+**

### 4.2. Executar testes

```bash
mvn clean test
```

---

# 📊 **5. Gerar Relatório Allure**

### 5.1. Via Maven (gera HTML na pasta target/site)

```bash
mvn allure:report
```

Após gerar, abra:

```
target/site/allure-maven-plugin/index.html
```

### 5.2. Via Allure CLI (abre servidor automático)

```bash
allure serve target/allure-results
```

---

# 🧱 **6. Arquitetura dos Testes**

## 🔸 BaseApiTest.java

Centraliza:

* BaseURI
* Logging global de Request/Response
* Integração com Allure
* Setup único via `@BeforeAll`

## 🔸 Testes por domínio

Cada classe está separada por regra de negócio:

| Classe             | Responsabilidade                     |
| ------------------ | ------------------------------------ |
| `BreedsListTests`  | Listagem de raças                    |
| `BreedImagesTests` | Imagens por raça (válido + inválido) |
| `RandomImageTests` | Imagem aleatória                     |

---

# 🔧 **7. Pipeline CI – GitHub Actions**

Arquivo: `.github/workflows/api-tests.yml`

### O pipeline executa:

1. Checkout
2. Setup JDK 21
3. `mvn clean test`
4. Publica artefatos:

    * Relatórios JUnit (`surefire-reports`)
    * Resultados Allure (`allure-results`)