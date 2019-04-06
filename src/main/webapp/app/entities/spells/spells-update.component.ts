import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ISpells } from 'app/shared/model/spells.model';
import { SpellsService } from './spells.service';

@Component({
    selector: 'jhi-spells-update',
    templateUrl: './spells-update.component.html'
})
export class SpellsUpdateComponent implements OnInit {
    spells: ISpells;
    isSaving: boolean;

    constructor(protected spellsService: SpellsService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ spells }) => {
            this.spells = spells;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.spells.id !== undefined) {
            this.subscribeToSaveResponse(this.spellsService.update(this.spells));
        } else {
            this.subscribeToSaveResponse(this.spellsService.create(this.spells));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ISpells>>) {
        result.subscribe((res: HttpResponse<ISpells>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
