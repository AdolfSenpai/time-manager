CREATE TABLE "user"(
    "id" UUID PRIMARY KEY,
    "email" TEXT NOT NULL,
    "name" TEXT NOT NULL,
    "password" TEXT NOT NULL,
    "created_date" TIMESTAMP NOT NULL DEFAULT NOW(),
    "modified_date" TIMESTAMP NOT NULL DEFAULT NOW(),
    "version" BIGINT DEFAULT 1 NOT NULL
);