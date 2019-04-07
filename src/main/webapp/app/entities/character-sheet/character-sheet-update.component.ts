import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ICharacterSheet } from 'app/shared/model/character-sheet.model';
import { CharacterSheetService } from './character-sheet.service';

@Component({
    selector: 'jhi-character-sheet-update',
    templateUrl: './character-sheet-update.component.html'
})
export class CharacterSheetUpdateComponent implements OnInit {
    characterSheet: ICharacterSheet;
    isSaving: boolean;

    constructor(protected characterSheetService: CharacterSheetService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ characterSheet }) => {
            this.characterSheet = characterSheet;
        });
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
}
