# service-spring-consul
Example Spring Consul
- Segue abaixo para inicia o consul
- Descompacte o arquivo consul_1.19.0_darwin_amd64.zip
- Entre na pasta /consul_1.19.0_darwin_amd64 
- Digite o seguinte comando ./consul agent -server -bootstrap-expect=1 -data-dir=consul-data -ui -bind=127.0.0.1
- Acesse o link http://localhost:8500/ui/dc1/services
- No menu clique em "Key / Value"
- Clique em CREATE
- Crie uma pasta com o nome "config/"
- Entre na pasta "config"
- Crie uma pasta com o nome "service-spring-consul/"
- Entre na pasta "service-spring-consul"
- Crie duas chaves 
- Primeira chave com o nome "dataMessage", adicione um valor qualquer
- Segunda chave com o nome "message", adicione um valor qualquer
- Volte ao repo da aplicacao na raiz
- Digite o seguinte comando
- mvn install
