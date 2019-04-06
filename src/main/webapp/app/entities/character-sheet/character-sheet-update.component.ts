import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { ICharacterSheet } from 'app/shared/model/character-sheet.model';
import { CharacterSheetService } from './character-sheet.service';
import { ISpellBook } from 'app/shared/model/spell-book.model';
import { SpellBookService } from 'app/entities/spell-book';

@Component({
    selector: 'jhi-character-sheet-update',
    templateUrl: './character-sheet-update.component.html'
})
export class CharacterSheetUpdateComponent implements OnInit {
    characterSheet: ICharacterSheet;
    isSaving: boolean;

    characters: ISpellBook[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected characterSheetService: CharacterSheetService,
        protected spellBookService: SpellBookService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ characterSheet }) => {
            this.characterSheet = characterSheet;
        });
        this.spellBookService
            .query({ filter: 'charactersheet-is-null' })
            .pipe(
                filter((mayBeOk: HttpResponse<ISpellBook[]>) => mayBeOk.ok),
                map((response: HttpResponse<ISpellBook[]>) => response.body)
            )
            .subscribe(
                (res: ISpellBook[]) => {
                    if (!this.characterSheet.character || !this.characterSheet.character.id) {
                        this.characters = res;
                    } else {
                        this.spellBookService
                            .find(this.characterSheet.character.id)
                            .pipe(
                                filter((subResMayBeOk: HttpResponse<ISpellBook>) => subResMayBeOk.ok),
                                map((subResponse: HttpResponse<ISpellBook>) => subResponse.body)
                            )
                            .subscribe(
                                (subRes: ISpellBook) => (this.characters = [subRes].concat(res)),
                                (subRes: HttpErrorResponse) => this.onError(subRes.message)
                            );
                    }
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.characterSheet.id !== undefined) {
            this.subscribeToSaveResponse(this.characterSheetService.update(this.characterSheet));
        } else {
            this.subscribeToSaveResponse(this.characterSheetService.create(this.characterSheet));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ICharacterSheet>>) {
        result.subscribe((res: HttpResponse<ICharacterSheet>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackSpellBookById(index: number, item: ISpellBook) {
        return item.id;
    }
}
