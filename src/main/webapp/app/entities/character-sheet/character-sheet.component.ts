import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ICharacterSheet } from 'app/shared/model/character-sheet.model';
import { AccountService } from 'app/core';
import { CharacterSheetService } from './character-sheet.service';

@Component({
    selector: 'jhi-character-sheet',
    templateUrl: './character-sheet.component.html'
})
export class CharacterSheetComponent implements OnInit, OnDestroy {
    characterSheets: ICharacterSheet[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected characterSheetService: CharacterSheetService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.characterSheetService
            .query()
            .pipe(
                filter((res: HttpResponse<ICharacterSheet[]>) => res.ok),
                map((res: HttpResponse<ICharacterSheet[]>) => res.body)
            )
            .subscribe(
                (res: ICharacterSheet[]) => {
                    this.characterSheets = res;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInCharacterSheets();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ICharacterSheet) {
        return item.id;
    }

    registerChangeInCharacterSheets() {
        this.eventSubscriber = this.eventManager.subscribe('characterSheetListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
