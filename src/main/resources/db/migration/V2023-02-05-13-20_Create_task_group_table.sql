CREATE TABLE "task_group"(
    "id" UUID PRIMARY KEY,
    "created_date" TIMESTAMP NOT NULL DEFAULT NOW(),
    "modified_date" TIMESTAMP NOT NULL DEFAULT NOW(),
    "version" BIGINT DEFAULT 1 NOT NULL,

    "name" TEXT NOT NULL
);

ALTER TABLE "task" ADD COLUMN "group_id" UUID REFERENCES "task_group"("id");

CREATE INDEX "idx_task__group_id" ON "task"("group_id");
