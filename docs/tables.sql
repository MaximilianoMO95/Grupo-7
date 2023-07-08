CREATE TABLE IF NOT EXISTS tipo_cuenta (
    id INT AUTO_INCREMENT PRIMARY KEY,

    nombre          VARCHAR(25) NOT NULL,
    descripcion     TEXT
);

CREATE TABLE IF NOT EXISTS cliente (
    id INT AUTO_INCREMENT PRIMARY KEY,

    run                 INT NOT NULL,
    dv                  CHAR(1) NOT NULL,
    nombre              VARCHAR(60) NOT NULL,
    ap_materno          VARCHAR(30) NOT NULL,
    ap_paterno          VARCHAR(30) NOT NULL,
    tel                 VARCHAR(16) NOT NULL,
    comuna              VARCHAR(30) NOT NULL,
    domicilio           VARCHAR(60) NOT NULL
);

CREATE TABLE IF NOT EXISTS cuenta (
    id INT AUTO_INCREMENT PRIMARY KEY,
    
    tipo_id INT NOT NULL,
    numero  INT NOT NULL,
    saldo   INT NOT NULL,

    CONSTRAINT fk_cuenta_tipo FOREIGN KEY (tipo_id) REFERENCES tipo_cuenta(id)
);

CREATE TABLE IF NOT EXISTS cuenta_cliente (
    id INT AUTO_INCREMENT PRIMARY KEY,
    
    cuenta_id   INT NOT NULL,
    cliente_id  INT NOT NULL,

    CONSTRAINT fk_cuenta_cliente_cuenta FOREIGN KEY (cuenta_id) REFERENCES cuenta(id),
    CONSTRAINT fk_cuenta_cliente_cliente FOREIGN KEY (cliente_id) REFERENCES cliente(id)
);
