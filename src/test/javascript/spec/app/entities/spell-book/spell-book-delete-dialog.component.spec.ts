/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { DungeonsandDatabasesTestModule } from '../../../test.module';
import { SpellBookDeleteDialogComponent } from 'app/entities/spell-book/spell-book-delete-dialog.component';
import { SpellBookService } from 'app/entities/spell-book/spell-book.service';

describe('Component Tests', () => {
    describe('SpellBook Management Delete Component', () => {
        let comp: SpellBookDeleteDialogComponent;
        let fixture: ComponentFixture<SpellBookDeleteDialogComponent>;
        let service: SpellBookService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DungeonsandDatabasesTestModule],
                declarations: [SpellBookDeleteDialogComponent]
            })
                .overrideTemplate(SpellBookDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SpellBookDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SpellBookService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete(123);
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith(123);
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});
