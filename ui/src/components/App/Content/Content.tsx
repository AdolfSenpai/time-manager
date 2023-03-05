import { useState } from "react";
import React from "react";
import getTasksData from "src/assets/tasks.data";
import styles from "src/components/App/Content/Content.module.scss";
import AuthForm from "src/components/AuthForm/AuthForm";
import TaskList from "src/components/TaskList/TaskList";
import { TaskGroup } from "src/models/TaskGroup";
import { User } from "src/models/User";

export default function Content(props: {user: User | null}) {

    const [taskGroups, setTaskGroups] = useState<TaskGroup[]>(getTasksData());

    return (
        <div className={styles.Content}>
            {
                props.user
                &&
                <div className={styles.TaskGroups}>
                    {
                        taskGroups?.map(
                            group => <TaskList
                                tasks={group.taskList}
                                name={group.name}
                                key={group.id}
                            />
                        )
                    }
                </div>
                ||
                <AuthForm/>
            }
        </div>
    );
}