CREATE TABLE "task_time"(
    "id" UUID PRIMARY KEY,
    
    "user_task_id" UUID NOT NULL
        CONSTRAINT "fk_task_time__user_task_id__user_task__id"
        REFERENCES "user_task" ("id")
        ON DELETE RESTRICT ON UPDATE RESTRICT,
    "start_time" TIMESTAMP NOT NULL,
    "end_time" TIMESTAMP NOT NULL,

    "created_date" TIMESTAMP NOT NULL DEFAULT NOW(),
    "modified_date" TIMESTAMP NOT NULL DEFAULT NOW(),
    "version" BIGINT DEFAULT 1 NOT NULL,
);

CREATE INDEX "idx_task_time__user_task_id" ON "task_time"("user_task_id");
