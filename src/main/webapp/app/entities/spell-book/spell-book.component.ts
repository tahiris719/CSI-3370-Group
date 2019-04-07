import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ISpellBook } from 'app/shared/model/spell-book.model';
import { AccountService } from 'app/core';
import { SpellBookService } from './spell-book.service';

@Component({
    selector: 'jhi-spell-book',
    templateUrl: './spell-book.component.html'
})
export class SpellBookComponent implements OnInit, OnDestroy {
    spellBooks: ISpellBook[];
    currentAccount: any;
    eventSubscriber: Subscription;
    currentSearch: string;

    constructor(
        protected spellBookService: SpellBookService,
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
            this.spellBookService
                .search({
                    query: this.currentSearch
                })
                .pipe(
                    filter((res: HttpResponse<ISpellBook[]>) => res.ok),
                    map((res: HttpResponse<ISpellBook[]>) => res.body)
                )
                .subscribe((res: ISpellBook[]) => (this.spellBooks = res), (res: HttpErrorResponse) => this.onError(res.message));
            return;
        }
        this.spellBookService
            .query()
            .pipe(
                filter((res: HttpResponse<ISpellBook[]>) => res.ok),
                map((res: HttpResponse<ISpellBook[]>) => res.body)
            )
            .subscribe(
                (res: ISpellBook[]) => {
                    this.spellBooks = res;
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
        this.registerChangeInSpellBooks();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ISpellBook) {
        return item.id;
    }

    registerChangeInSpellBooks() {
        this.eventSubscriber = this.eventManager.subscribe('spellBookListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
