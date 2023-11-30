@startuml

Actor Client
activate Client

Client -> SessionController: {username, password}
activate SessionController

SessionController -> SessionController: mapToTokenRequest(username, password)

SessionController -> SessionService: getToken(tokenRequest)
activate SessionService

SessionService -> UserRepository: findByUsername(username)
activate UserRepository

UserRepository --> SessionService: user
deactivate UserRepository

SessionService -> SessionService: checkPassword(user, password)

SessionService -> SessionService: generateToken(user)

SessionService --> SessionController: token
deactivate SessionService

SessionController --> Client : {token}
deactivate SessionController

deactivate Client

@enduml