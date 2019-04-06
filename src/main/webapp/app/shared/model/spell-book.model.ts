export interface ISpellBook {
    id?: number;
    maxSpells?: number;
    spells?: string;
}

export class SpellBook implements ISpellBook {
    constructor(public id?: number, public maxSpells?: number, public spells?: string) {}
}
