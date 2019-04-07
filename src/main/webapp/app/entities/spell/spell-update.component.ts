import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { ISpell } from 'app/shared/model/spell.model';
import { SpellService } from './spell.service';
import { ISpellBook } from 'app/shared/model/spell-book.model';
import { SpellBookService } from 'app/entities/spell-book';

@Component({
    selector: 'jhi-spell-update',
    templateUrl: './spell-update.component.html'
})
export class SpellUpdateComponent implements OnInit {
    spell: ISpell;
    isSaving: boolean;

    spellbooks: ISpellBook[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected spellService: SpellService,
        protected spellBookService: SpellBookService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ spell }) => {
            this.spell = spell;
        });
        this.spellBookService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<ISpellBook[]>) => mayBeOk.ok),
                map((response: HttpResponse<ISpellBook[]>) => response.body)
            )
            .subscribe((res: ISpellBook[]) => (this.spellbooks = res), (res: HttpErrorResponse) => this.onError(res.message));
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.spell.id !== undefined) {
            this.subscribeToSaveResponse(this.spellService.update(this.spell));
        } else {
            this.subscribeToSaveResponse(this.spellService.create(this.spell));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ISpell>>) {
        result.subscribe((res: HttpResponse<ISpell>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
