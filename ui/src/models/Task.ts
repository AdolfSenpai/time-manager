export interface Task {

    readonly id: string;

    readonly numberPrefix?: string;

    readonly number?: string;

    readonly name: string;

    readonly description?: string;

    readonly link?: string;
}