# Minecraft Plugin: Encantamentos Mil

Encantamentos Mil é um plugin Minecraft para Paper que fornece livros encantados com todos os encantamentos registrados no jogo no nível 1000 e entrega quatro itens personalizados com encantamentos de alto nível por meio de comandos.

## Install

1. Use Java 21.
2. Execute o plugin em um servidor Paper compatível com Minecraft 1.21.
3. Compile o projeto com Maven usando:

   mvn package

4. Copie o arquivo JAR gerado em `target/plugin.jar` para a pasta `plugins` do servidor.
5. Reinicie o servidor.
6. O plugin será ativado automaticamente e a permissão `enchantments1000.use` será concedida por padrão a operadores.

## Use

Use um dos comandos abaixo dentro do jogo:

- `/encantamentos livros` ou `/encantos livros`: recebe um livro encantado para cada encantamento registrado no servidor, todos no nível 1000.
- `/encantamentos itens` ou `/encantos itens`: recebe os quatro itens personalizados.
- `/encantamentos todos` ou `/encantos todos`: recebe todos os livros e os quatro itens.

Os itens personalizados são:

- Um graveto com Repulsão 1000.
- Uma pá de madeira com Fortuna 1000.
- Uma espada de netherite com Afiação 1000 e Aspecto Flamejante 1000.
- Um machado de netherite com Fortuna 101.

A permissão necessária é `enchantments1000.use`. Por padrão, ela está disponível para operadores. O comando só pode ser executado por jogadores. Se o inventário estiver cheio, os itens que não couberem serão deixados no chão.

---

More Minecraft plugin projects by EnderDevelopment: https://github.com/EnderDevelopment

Project page: https://dash.enderdevelopment.com/community/df6beafc-0f84-4bae-90fd-dbcaa8ee1009?utm_source=github&utm_medium=readme&utm_campaign=auto_publish&utm_content=df6beafc-0f84-4bae-90fd-dbcaa8ee1009
