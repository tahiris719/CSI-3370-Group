import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISpell } from 'app/shared/model/spell.model';
import { SpellService } from './spell.service';

@Component({
    selector: 'jhi-spell-delete-dialog',
    templateUrl: './spell-delete-dialog.component.html'
})
export class SpellDeleteDialogComponent {
    spell: ISpell;

    constructor(protected spellService: SpellService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.spellService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'spellListModification',
                content: 'Deleted an spell'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-spell-delete-popup',
    template: ''
})
export class SpellDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ spell }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(SpellDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.spell = spell;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/spell', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/spell', { outlets: { popup: null } }]);
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
