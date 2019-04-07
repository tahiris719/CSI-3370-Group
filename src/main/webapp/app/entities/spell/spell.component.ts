import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ISpell } from 'app/shared/model/spell.model';
import { AccountService } from 'app/core';
import { SpellService } from './spell.service';

@Component({
    selector: 'jhi-spell',
    templateUrl: './spell.component.html'
})
export class SpellComponent implements OnInit, OnDestroy {
    spells: ISpell[];
    currentAccount: any;
    eventSubscriber: Subscription;
    currentSearch: string;

    constructor(
        protected spellService: SpellService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected activatedRoute: ActivatedRoute,
        protected accountService: AccountService
    ) {
        this.currentSearch =
            this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['search']
                ? this.activatedRoute.snapshot.params['search']
                : '';
    }

    loadAll() {
        if (this.currentSearch) {
            this.spellService
                .search({
                    query: this.currentSearch
                })
                .pipe(
                    filter((res: HttpResponse<ISpell[]>) => res.ok),
                    map((res: HttpResponse<ISpell[]>) => res.body)
                )
                .subscribe((res: ISpell[]) => (this.spells = res), (res: HttpErrorResponse) => this.onError(res.message));
            return;
        }
        this.spellService
            .query()
            .pipe(
                filter((res: HttpResponse<ISpell[]>) => res.ok),
                map((res: HttpResponse<ISpell[]>) => res.body)
            )
            .subscribe(
                (res: ISpell[]) => {
                    this.spells = res;
                    this.currentSearch = '';
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    search(query) {
        if (!query) {
            return this.clear();
        }
        this.currentSearch = query;
        this.loadAll();
    }

    clear() {
        this.currentSearch = '';
        this.loadAll();
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

    trackId(index: number, item: ISpell) {
        return item.id;
    }

    registerChangeInSpells() {
        this.eventSubscriber = this.eventManager.subscribe('spellListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
