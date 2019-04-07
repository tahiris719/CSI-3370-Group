import { ISpell } from 'app/shared/model/spell.model';
import { ICharacterSheet } from 'app/shared/model/character-sheet.model';

export interface ISpellBook {
    id?: number;
    maxSlots?: number;
    currentSlots?: number;
    books?: ISpell[];
    book?: ICharacterSheet;
}

export class SpellBook implements ISpellBook {
    constructor(
        public id?: number,
        public maxSlots?: number,
        public currentSlots?: number,
        public books?: ISpell[],
        public book?: ICharacterSheet
    ) {}
}
