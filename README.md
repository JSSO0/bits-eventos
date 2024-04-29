# bits-eventos
Bis eventos PI

# Rota api de cadastro de pessoa
<h2>Metodos para usar</h2>
<br>
<h3>Get</h3>
<h3>1 - /pessoa</h3>
<h3>2 - /pessoa/{id}</h3>
<br>
<h3>Post</h3>
<h3>/pessoa</h3>
<br>
<h3>Put</h3>
<h3>/pessoa/{id}</h3>
<br>
<h3>Delete</h3>
<h3>/pessoa/id</h3>
<br>
<h3>Corpo da Request</h3>
<h3>
{
    "id": "id",
    "nome": "nome",
    "telefone": "numero",
    "email": "email",
    "cpf": "cpf"
}

</h3>

Tabela de Eventos:
evento_id (UUID) - Identificador único do evento (Chave primária)
nome_evento (Texto) - Nome do evento
data_evento (Data/Hora) - Data e hora do evento
evento_pago (Booleano) - Indica se o evento é pago ou gratuito
evento_valor (Numérico) - Valor do evento
adm_id (UUID) - Referência para o ID do administrador (Chave estrangeira)

Tabela de Participantes:
participante_id (UUID) - Identificador único do participante (Chave primária)
evento_id (UUID) - Referência para o ID do evento (Chave estrangeira)
pessoa_id (UUID) - Referência para o ID da pessoa (Chave estrangeira)
administrador (Booleano) - Indica se o participante é um administrador do evento

Tabela de Pessoas (Usuários):
pessoa_id (UUID) - Identificador único da pessoa (Chave primária)
nome (Texto) - Nome da pessoa
telefone (Texto) - Número de telefone da pessoa
email (Texto) - Endereço de e-mail da pessoa
cpf (Texto) - CPF da pessoa
username (Texto) - Nome de usuário da pessoa
password (Texto) - Senha da pessoa
administrador (Booleano) - Indica se a pessoa é um administrador global do sistema

<h3>http://localhost:8080/ </h3>

CREATE TABLE Company (
    id UUID PRIMARY KEY,
    name VARCHAR(255),
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE Event (
    id UUID PRIMARY KEY,
    name VARCHAR(255),
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    starts_in TIMESTAMP,
    end_in TIMESTAMP,
    payed_event BOOLEAN,
    value_event DECIMAL CHECK (payed_event = false OR (payed_event = true AND value_event IS NOT NULL)),
    company_id UUID REFERENCES Company(id)
);

CREATE TABLE User (
    id UUID PRIMARY KEY,
    name VARCHAR(255),
    company_id UUID REFERENCES Company(id),
    phone VARCHAR(15),
    email VARCHAR(255),
    is_adm BOOLEAN,
    password VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE Participants (
    id UUID PRIMARY KEY,
    event_id UUID REFERENCES Event(id),
    user_id UUID REFERENCES User(id),
    adm_of_event BOOLEAN
);


import java.util.UUID;
import java.time.LocalDateTime;

public class Company {
    private UUID id;
    private String name;
    private String description;
    private LocalDateTime createdAt;
    // getters and setters
}

public class Event {
    private UUID id;
    private String name;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime startsIn;
    private LocalDateTime endIn;
    private boolean payedEvent;
    private double valueEvent;
    private UUID companyId;
    // getters and setters
}

public class User {
    private UUID id;
    private String name;
    private UUID companyId;
    private String phone;
    private String email;
    private boolean isAdm;
    private String password;
    private LocalDateTime createdAt;
    // getters and setters
}

public class Participants {
    private UUID id;
    private UUID eventId;
    private UUID userId;
    private boolean admOfEvent;
    // getters and setters
}
