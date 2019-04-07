export interface ISpell {
    id?: number;
    book?: string;
    level?: number;
    school?: string;
    time?: string;
    range?: string;
    components?: string;
    duration?: string;
    classes?: string;
    description?: string;
}

export class Spell implements ISpell {
    constructor(
        public id?: number,
        public book?: string,
        public level?: number,
        public school?: string,
        public time?: string,
        public range?: string,
        public components?: string,
        public duration?: string,
        public classes?: string,
        public description?: string
    ) {}
}
