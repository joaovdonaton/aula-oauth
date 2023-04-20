# Trabalho para Matéria de OAuth

<b>Aluno:</b> João Vitor Donaton <br>

### **Instruções Para Compilar:**
- Clonar repositório
- Criar arquivo ```auth.properties``` em ```src/main/resources```. com as propriedades:
  - ```auth.client-id``` Client ID para OAuth 2.0 da API do Twitter
  - ```auth.client-secret``` Client Secret para OAuth 2.0 da API do Twitter
- Utilize o comando ```./mvnw spring-boot:run``` ou use o Intellij para compilar e rodar

### **Detalhes:**

- Utilizei a API do Twitter para testar
- É necessário ir no developer portal do Twitter, criar um project e um app (já vem com um por padrão), 
- Acessar App->Settings e habilitar e configurar o "User Authentication Settings"
- Acessar App->Keys and Tokens para gerar o Client ID e Secret
- Endpoints da API estão dentro de ```/api/oauth/```
  - ```/callback``` recebe o Authorization Code e o challenge (Code Verifier PKCE usado na hora de criar gerar o Authorization Code)
- Persistência dos access tokens é feita usando o banco em memória h2, então não é necessário configurar o banco de dados
- Credenciais para o banco de dados estão no ```src/main/resources/application.properties```, e o endpoint do banco é ```/api/oauth/h2-console```
