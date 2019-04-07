import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { ISpellBook } from 'app/shared/model/spell-book.model';
import { SpellBookService } from './spell-book.service';
import { ICharacterSheet } from 'app/shared/model/character-sheet.model';
import { CharacterSheetService } from 'app/entities/character-sheet';

@Component({
    selector: 'jhi-spell-book-update',
    templateUrl: './spell-book-update.component.html'
})
export class SpellBookUpdateComponent implements OnInit {
    spellBook: ISpellBook;
    isSaving: boolean;

    charactersheets: ICharacterSheet[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected spellBookService: SpellBookService,
        protected characterSheetService: CharacterSheetService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ spellBook }) => {
            this.spellBook = spellBook;
        });
        this.characterSheetService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<ICharacterSheet[]>) => mayBeOk.ok),
                map((response: HttpResponse<ICharacterSheet[]>) => response.body)
            )
            .subscribe((res: ICharacterSheet[]) => (this.charactersheets = res), (res: HttpErrorResponse) => this.onError(res.message));
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

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackCharacterSheetById(index: number, item: ICharacterSheet) {
        return item.id;
    }
}
