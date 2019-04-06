import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISpellBook } from 'app/shared/model/spell-book.model';
import { SpellBookService } from './spell-book.service';

@Component({
    selector: 'jhi-spell-book-delete-dialog',
    templateUrl: './spell-book-delete-dialog.component.html'
})
export class SpellBookDeleteDialogComponent {
    spellBook: ISpellBook;

    constructor(
        protected spellBookService: SpellBookService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.spellBookService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'spellBookListModification',
                content: 'Deleted an spellBook'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-spell-book-delete-popup',
    template: ''
})
export class SpellBookDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ spellBook }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(SpellBookDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.spellBook = spellBook;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/spell-book', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/spell-book', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
