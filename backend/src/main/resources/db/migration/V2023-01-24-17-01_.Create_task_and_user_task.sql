CREATE TABLE "task"(
    "id" UUID PRIMARY KEY,
    
    "number_prefix" VARCHAR(3) NOT NULL,
    "number" INT NOT NULL,
    "name" TEXT NOT NULL,
    "description" TEXT,
    "link" TEXT,

    "created_date" TIMESTAMP NOT NULL DEFAULT NOW(),
    "modified_date" TIMESTAMP NOT NULL DEFAULT NOW(),
    "version" BIGINT DEFAULT 1 NOT NULL,

    CONSTRAINT "uq_task__number_prefix__number" UNIQUE ("number_prefix", "number")
);
