/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { DungeonsAndDatabasesTestModule } from '../../../test.module';
import { SpellDeleteDialogComponent } from 'app/entities/spell/spell-delete-dialog.component';
import { SpellService } from 'app/entities/spell/spell.service';

describe('Component Tests', () => {
    describe('Spell Management Delete Component', () => {
        let comp: SpellDeleteDialogComponent;
        let fixture: ComponentFixture<SpellDeleteDialogComponent>;
        let service: SpellService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DungeonsAndDatabasesTestModule],
                declarations: [SpellDeleteDialogComponent]
            })
                .overrideTemplate(SpellDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SpellDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SpellService);
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
