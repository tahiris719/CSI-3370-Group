import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ISpells } from 'app/shared/model/spells.model';
import { AccountService } from 'app/core';
import { SpellsService } from './spells.service';

@Component({
    selector: 'jhi-spells',
    templateUrl: './spells.component.html'
})
export class SpellsComponent implements OnInit, OnDestroy {
    spells: ISpells[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected spellsService: SpellsService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.spellsService
            .query()
            .pipe(
                filter((res: HttpResponse<ISpells[]>) => res.ok),
                map((res: HttpResponse<ISpells[]>) => res.body)
            )
            .subscribe(
                (res: ISpells[]) => {
                    this.spells = res;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInSpells();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ISpells) {
        return item.id;
    }

    registerChangeInSpells() {
        this.eventSubscriber = this.eventManager.subscribe('spellsListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
