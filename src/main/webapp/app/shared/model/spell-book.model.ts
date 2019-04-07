export interface ISpellBook {
    id?: number;
    maxSlots?: number;
    currentSlots?: number;
}

export class SpellBook implements ISpellBook {
    constructor(public id?: number, public maxSlots?: number, public currentSlots?: number) {}
}
