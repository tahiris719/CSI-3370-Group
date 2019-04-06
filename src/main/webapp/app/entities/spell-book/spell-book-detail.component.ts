import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISpellBook } from 'app/shared/model/spell-book.model';

@Component({
    selector: 'jhi-spell-book-detail',
    templateUrl: './spell-book-detail.component.html'
})
export class SpellBookDetailComponent implements OnInit {
    spellBook: ISpellBook;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ spellBook }) => {
            this.spellBook = spellBook;
        });
    }

    previousState() {
        window.history.back();
    }
}
