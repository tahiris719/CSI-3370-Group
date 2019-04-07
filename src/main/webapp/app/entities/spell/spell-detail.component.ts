import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISpell } from 'app/shared/model/spell.model';

@Component({
    selector: 'jhi-spell-detail',
    templateUrl: './spell-detail.component.html'
})
export class SpellDetailComponent implements OnInit {
    spell: ISpell;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ spell }) => {
            this.spell = spell;
        });
    }

    previousState() {
        window.history.back();
    }
}
