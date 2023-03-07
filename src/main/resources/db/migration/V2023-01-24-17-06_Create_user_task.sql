CREATE TABLE "user_task"(
    "id" UUID PRIMARY KEY,
    
    "user_id" UUID NOT NULL
        CONSTRAINT "fk_user_task__user_id__user__id"
        REFERENCES "user" ("id")
        ON DELETE RESTRICT ON UPDATE RESTRICT,
    "task_id" UUID NOT NULL
        CONSTRAINT "fk_user_task__task_id__task__id"
        REFERENCES "task" ("id")
        ON DELETE RESTRICT ON UPDATE RESTRICT,

    "created_date" TIMESTAMP NOT NULL DEFAULT NOW(),
    "modified_date" TIMESTAMP NOT NULL DEFAULT NOW(),
    "version" BIGINT DEFAULT 1 NOT NULL
);

CREATE INDEX "idx_user_task__user_id" ON "user_task"("user_id");
CREATE INDEX "idx_user_task__task_id" ON "user_task"("task_id");
