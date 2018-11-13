CREATE TABLE IF NOT EXISTS "tipo_financa"(
  "id" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
  "nome" TEXT,
  "descricao" TEXT,
  "is_renda" INTEGER
);
CREATE TABLE IF NOT EXISTS "status_boleto"(
  "id" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
  "nome" TEXT,
  "descricao" TEXT
);
CREATE TABLE IF NOT EXISTS "condominio"(
  "id" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
  "nome" TEXT,
  "cnpj" TEXT,
  "telefone" TEXT,
  "endereco" TEXT,
  "numero" TEXT,
  "cidade" TEXT,
  "estado" TEXT,
  "cep" INTEGER,
  "valor_aluguel" REAL
);
CREATE TABLE IF NOT EXISTS "morador"(
  "id" INTEGER NOT NULL,
  "condominio_id" INTEGER NOT NULL,
  "nome" TEXT,
  "telefone" TEXT,
  "email" TEXT,
  "cpf" TEXT,
  "bloco" INTEGER,
  "andar" INTEGER,
  "apartamento" INTEGER,
  PRIMARY KEY("id","condominio_id"),
  CONSTRAINT "fk_morador_condominio1"
    FOREIGN KEY("condominio_id")
    REFERENCES "condominio"("id")
    ON DELETE CASCADE
    ON UPDATE CASCADE
);
CREATE INDEX IF NOT EXISTS "morador.fk_morador_condominio1_idx" ON "morador" ("condominio_id");
CREATE TABLE IF NOT EXISTS "sindico"(
  "id" INTEGER NOT NULL,
  "nome" TEXT,
  "cpf" TEXT,
  "telefone" TEXT,
  "email" TEXT,
  "usuario" TEXT,
  "senha" TEXT,
  "condominio_id" INTEGER NOT NULL,
  PRIMARY KEY("id","condominio_id"),
  CONSTRAINT "fk_sindico_condominio1"
    FOREIGN KEY("condominio_id")
    REFERENCES "condominio"("id")
    ON DELETE CASCADE
    ON UPDATE CASCADE
);
CREATE INDEX IF NOT EXISTS "sindico.fk_sindico_condominio1_idx" ON "sindico" ("condominio_id");
CREATE TABLE IF NOT EXISTS "orcamento"(
  "id" INTEGER NOT NULL,
  "sindico_id" INTEGER NOT NULL,
  "mes" INTEGER,
  "ano" INTEGER,
  "custo" REAL,
  "renda" REAL,
  "saldo" REAL,
  PRIMARY KEY("id","sindico_id"),
  CONSTRAINT "fk_orcamento_sindico1"
    FOREIGN KEY("sindico_id")
    REFERENCES "sindico"("id")
    ON DELETE CASCADE
    ON UPDATE CASCADE
);
CREATE INDEX IF NOT EXISTS "orcamento.fk_orcamento_sindico1_idx" ON "orcamento" ("sindico_id");
CREATE TABLE IF NOT EXISTS "boleto"(
  "id" INTEGER NOT NULL,
  "morador_id" INTEGER NOT NULL,
  "morador_condominio_id" INTEGER NOT NULL,
  "status_boleto_id" INTEGER NOT NULL,
  "banco" TEXT,
  "codigo" TEXT,
  "valor" REAL,
  "juros" REAL,
  "desconto" REAL,
  "multa" REAL,
  "data_vencimento" DATE,
  "is_segunda_via" INTEGER,
  PRIMARY KEY("id","morador_id","morador_condominio_id","status_boleto_id"),
  CONSTRAINT "fk_boleto_status_boleto"
    FOREIGN KEY("status_boleto_id")
    REFERENCES "status_boleto"("id")
    ON UPDATE CASCADE,
  CONSTRAINT "fk_boleto_morador1"
    FOREIGN KEY("morador_id","morador_condominio_id")
    REFERENCES "morador"("id","condominio_id")
    ON UPDATE CASCADE
);
CREATE INDEX IF NOT EXISTS "boleto.fk_boleto_status_boleto_idx" ON "boleto" ("status_boleto_id");
CREATE INDEX IF NOT EXISTS "boleto.fk_boleto_morador1_idx" ON "boleto" ("morador_id","morador_condominio_id");
CREATE TABLE IF NOT EXISTS "item_financa"(
  "id" INTEGER NOT NULL,
  "orcamento_id" INTEGER NOT NULL,
  "tipo_financa_id" INTEGER NOT NULL,
  "boleto_id" INTEGER NOT NULL,
  "boleto_status_boleto_id" INTEGER NOT NULL,
  "boleto_morador_id" INTEGER NOT NULL,
  "boleto_morador_condominio_id" INTEGER NOT NULL,
  "data" DATETIME,
  "renda" REAL,
  PRIMARY KEY("id","orcamento_id","tipo_financa_id","boleto_id","boleto_status_boleto_id","boleto_morador_id","boleto_morador_condominio_id"),
  CONSTRAINT "fk_item_financa_tipo_financa1"
    FOREIGN KEY("tipo_financa_id")
    REFERENCES "tipo_financa"("id")
    ON UPDATE CASCADE,
  CONSTRAINT "fk_item_financa_orcamento1"
    FOREIGN KEY("orcamento_id")
    REFERENCES "orcamento"("id")
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT "fk_item_financa_boleto1"
    FOREIGN KEY("boleto_id","boleto_status_boleto_id","boleto_morador_id","boleto_morador_condominio_id")
    REFERENCES "boleto"("id","status_boleto_id","morador_id","morador_condominio_id")
    ON DELETE CASCADE
    ON UPDATE CASCADE
);
CREATE INDEX IF NOT EXISTS "item_financa.fk_item_financa_tipo_financa1_idx" ON "item_financa" ("tipo_financa_id");
CREATE INDEX IF NOT EXISTS "item_financa.fk_item_financa_orcamento1_idx" ON "item_financa" ("orcamento_id");
CREATE INDEX IF NOT EXISTS "item_financa.fk_item_financa_boleto1_idx" ON "item_financa" ("boleto_id","boleto_status_boleto_id","boleto_morador_id","boleto_morador_condominio_id");

INSERT INTO "sindico" ("id", "nome", "usuario", "senha", "condominio_id") VALUES (0, "Usuario", "admin", "admin", 0);
