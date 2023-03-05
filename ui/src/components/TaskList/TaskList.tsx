import { useState } from "react";
import React from "react";
import TaskRow from "src/components/TaskList/TaskRow/TaskRow";
import { Task } from "src/models/Task";
import classes from "src/components/TaskList/TaskList.module.scss";

function TaskList({ name, tasks }: { name: string, tasks: Task[] }) {

    const [taskFolding, setTaskFolding] = useState<{ [key: string]: boolean }>(
        tasks.reduce<{[key: string]: boolean}>(
            (folding, task) => ({ ...folding, [task.id]: true }),
            {}
        )
    );

    const toggleFolding = (taskId: string) => {

        setTaskFolding({ ...taskFolding, [taskId]: !taskFolding[taskId]});
    };

    return (
        <div className={classes.TaskList}>
            <h1 className={classes.Title}>{name}</h1>
            <div className={classes.ListOutline}>
                {
                    tasks.map(task =>
                        <TaskRow
                            task={task}
                            folded={taskFolding[task.id]}
                            toggleFolding={toggleFolding}
                            key={task.id}
                        />
                    )
                }
            </div>
        </div>
    );
}

export default TaskList;
