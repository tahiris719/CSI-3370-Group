import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISpells } from 'app/shared/model/spells.model';
import { SpellsService } from './spells.service';

@Component({
    selector: 'jhi-spells-delete-dialog',
    templateUrl: './spells-delete-dialog.component.html'
})
export class SpellsDeleteDialogComponent {
    spells: ISpells;

    constructor(protected spellsService: SpellsService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.spellsService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'spellsListModification',
                content: 'Deleted an spells'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-spells-delete-popup',
    template: ''
})
export class SpellsDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ spells }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(SpellsDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.spells = spells;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/spells', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/spells', { outlets: { popup: null } }]);
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
