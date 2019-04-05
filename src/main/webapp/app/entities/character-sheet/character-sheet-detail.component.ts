import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICharacterSheet } from 'app/shared/model/character-sheet.model';

@Component({
    selector: 'jhi-character-sheet-detail',
    templateUrl: './character-sheet-detail.component.html'
})
export class CharacterSheetDetailComponent implements OnInit {
    characterSheet: ICharacterSheet;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ characterSheet }) => {
            this.characterSheet = characterSheet;
        });
    }

    previousState() {
        window.history.back();
    }
}
