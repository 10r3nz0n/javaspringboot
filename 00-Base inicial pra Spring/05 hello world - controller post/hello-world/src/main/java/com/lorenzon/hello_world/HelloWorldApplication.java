package com.lorenzon.hello_world;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;

/*
	Pacotes e explicações:
	Pacotes como domain, controller e service existem para separar responsabilidades e reduzir acoplamento. 
	Essa separação organiza o código por camadas, facilita manutenção, testes e evolução do sistema, 
	e permite que mudanças em uma parte causem o mínimo de impacto nas demais.

    Em termos de arquitetura, isso é uma aplicação prática de:
	Separation of Concerns, separar preocupações
    Single Responsibility Principle, cada componente com um motivo principal para mudar
    Arquitetura em camadas, Web ou API, aplicação, domínio, infraestrutura

    Pacote controller: Camada de entrada do sistema. Em Spring, é onde ficam as rotas HTTP. É a parte que conversa com o mundo externo via HTTP.
                       Receber a requisição HTTP
					   Extrair dados do caminho, query ou body
					   Validar o básico de formato, se aplicável
					   Chamar a camada de serviço
					   Devolver a resposta HTTP
					   O que não deveria fazer: Regras de negócio, Lógica complexa, Acesso direto a banco, Transformações pesadas, 
					   Por que existe?
					   Porque HTTP é um detalhe de infraestrutura. Você quer isolar esse detalhe. 
					   Se amanhã a entrada não for HTTP (exemplo fila, CLI, batch), você não quer reescrever o domínio.

	Pacote service: Camada de aplicação. Coordena casos de uso. Em Spring, normalmente classes anotadas com @Service.
					Implementar casos de uso, exemplo cadastrar ER, gerar genealogia, rastrear para trás
					Orquestrar validações, chamadas a repositórios e regras
					Definir transações, quando aplicável
					Manter o controller simples
					O que não deveria fazer: Depender de detalhes de HTTP, Misturar lógica de persistência específica (SQL, Mongo) diretamente, isso fica em repository
					Por que existe?
					Porque os casos de uso representam o valor do sistema. Eles precisam de uma camada própria para:
						Ser testável sem web
						Ser reutilizável por diferentes entradas
						Encapsular complexidade e fluxo

	Pacote domain: Camada que representa o núcleo do seu problema. No seu caso, rastreabilidade industrial: ER, genealogia, eventos, lotes, relações.
				   Modelar conceitos do domínio, classes, records, value objects
				   Definir invariantes, exemplo ER precisa ter identificador, evento precisa ter timestamp, vínculo precisa ter origem e destino
				   Centralizar regras que não dependem de tecnologia
				   O que não deveria fazer: Depender de Spring, Depender de banco específico, Depender de HTTP,
				   Por que existe?
				   Porque o domínio muda por motivos de negócio, não por motivos técnicos. 
				   Ao isolar o domínio, você mantém a essência do seu sistema estável e protege sua propriedade intelectual e 
				   seus conceitos do “vai e volta” de frameworks.

	Pacote repositorio: O pacote repository existe para isolar o acesso a dados. Ele encapsula como você lê e grava informações, seja em memória, MySQL, 
						MongoDB, arquivo ou qualquer outra fonte. Isso mantém controller e service focados em entrada e casos de uso, e mantém o domínio livre de detalhes de persistência.
						Em arquitetura em camadas, o repository normalmente fica na camada de infraestrutura, mas é comum organizá-lo como um pacote próprio no projeto, porque é um ponto de variação frequente.
						Persistir e recuperar dados
						Traduzir chamadas do sistema para operações do mecanismo de armazenamento
						Prover uma “interface” de acesso ao dado para o service, sem expor detalhes
						O que ele não deveria fazer: Tomar decisões de negócio, Conhecer HTTP, Montar resposta de API, Fazer regras
						Por que ele existe 
						Porque persistência é um detalhe que muda. 
						Hoje você usa uma lista em memória, amanhã MySQL com JPA, depois MongoDB para eventos. 
						Se controller ou service dependerem diretamente de JPA ou Mongo, a mudança custa muito mais.	
						Com Repository, o Service dependerá dele na forma de injeção do repositório no serviço.

	Mais pacotes poderão ser úteis para organização da arquitetura do software: 
		config
		controller
		application
		domain
		repository (interfaces)
		infrastructure (implementacoes MySQL e Mongo)
		dto
		mapper
		exception
		validation
		security
		integration
		shared

*/

@SpringBootApplication
@Profile("dev")
public class HelloWorldApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloWorldApplication.class, args); // executando a aplicação por sprint
	}

}
