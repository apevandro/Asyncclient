#language:pt
@PersonFaultInjection
Funcionalidade: Person - Fault Injection

  Cenário: Person - Cpf Nulo
    Dado o cenário Person - Cpf Nulo
    Quando eu executo o cenário Person - Cpf Nulo
    Então o cenário Person - Cpf Nulo executa com sucesso

  Cenário: Person - Data de nascimento inválida
    Dado o cenário Person - Data de nascimento inválida
    Quando eu executo o cenário Person - Data de nascimento inválida
    Então o cenário Person - Data de nascimento inválida executa com sucesso