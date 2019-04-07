import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ISpellBook } from 'app/shared/model/spell-book.model';
import { SpellBookService } from './spell-book.service';

@Component({
    selector: 'jhi-spell-book-update',
    templateUrl: './spell-book-update.component.html'
})
export class SpellBookUpdateComponent implements OnInit {
    spellBook: ISpellBook;
    isSaving: boolean;

    constructor(protected spellBookService: SpellBookService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ spellBook }) => {
            this.spellBook = spellBook;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.spellBook.id !== undefined) {
            this.subscribeToSaveResponse(this.spellBookService.update(this.spellBook));
        } else {
            this.subscribeToSaveResponse(this.spellBookService.create(this.spellBook));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ISpellBook>>) {
        result.subscribe((res: HttpResponse<ISpellBook>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
