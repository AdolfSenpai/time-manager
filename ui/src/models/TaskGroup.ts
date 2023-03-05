import { Task } from "src/models/Task";

export interface TaskGroup {

    readonly id: string;

    readonly name: string;

    readonly taskList: Task[];
}