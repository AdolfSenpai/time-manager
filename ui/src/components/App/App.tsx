import { useState } from "react";
import React from 'react';
import "src/assets/styles/main.css";
import styles from "src/components/App/App.module.css";
import getTasksData from "src/assets/tasks.data";
import AuthForm from "src/components/AuthForm/AuthForm";
import NavBar from "src/components/NavBar/NavBar";
import TaskList from "src/components/TaskList/TaskList";
import { TaskGroup } from "src/models/TaskGroup";
import { User } from "src/models/User";

function App() {

    const [taskGroups, setTaskGroups] = useState<TaskGroup[]>(getTasksData());
    const [user, setUser] = useState<User | null>(null);

    return (
        <div className={styles.App}>
            <NavBar user={user}/>
            {
                user
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

export default App;
