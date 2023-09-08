import { Course } from './course';

export class User {
    id: string;
    name: string;
    email: string;
    courses?: Course[];
    courseInput: string;
}
