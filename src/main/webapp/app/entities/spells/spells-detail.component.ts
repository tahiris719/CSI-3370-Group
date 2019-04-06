import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISpells } from 'app/shared/model/spells.model';

@Component({
    selector: 'jhi-spells-detail',
    templateUrl: './spells-detail.component.html'
})
export class SpellsDetailComponent implements OnInit {
    spells: ISpells;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ spells }) => {
            this.spells = spells;
        });
    }

    previousState() {
        window.history.back();
    }
}
