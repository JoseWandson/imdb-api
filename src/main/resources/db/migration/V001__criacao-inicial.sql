CREATE TABLE usuario (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
    senha VARCHAR(150) NOT NULL,
    ativo BOOLEAN NOT NULL
)  ENGINE=INNODB DEFAULT CHARSET=UTF8MB4;

CREATE TABLE permissao (
    id BIGINT PRIMARY KEY,
    descricao VARCHAR(50) NOT NULL
)  ENGINE=INNODB DEFAULT CHARSET=UTF8MB4;

CREATE TABLE usuario_permissao (
    id_usuario BIGINT NOT NULL,
    id_permissao BIGINT NOT NULL,
    PRIMARY KEY (id_usuario , id_permissao),
    FOREIGN KEY (id_usuario)
        REFERENCES usuario (id),
    FOREIGN KEY (id_permissao)
        REFERENCES permissao (id)
)  ENGINE=INNODB DEFAULT CHARSET=UTF8MB4;

CREATE TABLE filme (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    diretor VARCHAR(50) NOT NULL,
    genero VARCHAR(50) NOT NULL
)  ENGINE=INNODB DEFAULT CHARSET=UTF8MB4;

CREATE TABLE filme_ator (
    ator VARCHAR(50) NOT NULL,
    id_filme BIGINT NOT NULL,
    FOREIGN KEY (id_filme)
        REFERENCES filme (id)
)  ENGINE=INNODB DEFAULT CHARSET=UTF8MB4;

CREATE TABLE voto (
    id_usuario BIGINT NOT NULL,
    id_filme BIGINT NOT NULL,
    nota INT NOT NULL,
    PRIMARY KEY (id_usuario , id_filme),
    FOREIGN KEY (id_usuario)
        REFERENCES usuario (id),
    FOREIGN KEY (id_filme)
        REFERENCES filme (id)
)  ENGINE=INNODB DEFAULT CHARSET=UTF8MB4;