import { useState } from "react";
import React from 'react';
import "src/assets/styles/App.css";
import getTasksData from "src/assets/tasks.data";
import NavBar from "src/components/NavBar/NavBar";
import TaskList from "src/components/TaskList/TaskList";
import { TaskGroup } from "src/models/TaskGroup";

function App() {

    const [taskGroups, changeTaskGroups] = useState<TaskGroup[]>(getTasksData());

    return (
        <div className="App">
            <NavBar />
            {
                taskGroups?.map(
                 group => <TaskList tasks={group.taskList} name={group.name} key={group.id}/>
                )
            }
        </div>
    );
}

export default App;
