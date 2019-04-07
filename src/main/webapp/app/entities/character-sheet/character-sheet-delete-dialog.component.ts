import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICharacterSheet } from 'app/shared/model/character-sheet.model';
import { CharacterSheetService } from './character-sheet.service';

@Component({
    selector: 'jhi-character-sheet-delete-dialog',
    templateUrl: './character-sheet-delete-dialog.component.html'
})
export class CharacterSheetDeleteDialogComponent {
    characterSheet: ICharacterSheet;

    constructor(
        protected characterSheetService: CharacterSheetService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.characterSheetService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'characterSheetListModification',
                content: 'Deleted an characterSheet'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-character-sheet-delete-popup',
    template: ''
})
export class CharacterSheetDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ characterSheet }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(CharacterSheetDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.characterSheet = characterSheet;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/character-sheet', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/character-sheet', { outlets: { popup: null } }]);
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
