/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { DungeonsAndDatabasesTestModule } from '../../../test.module';
import { SpellsUpdateComponent } from 'app/entities/spells/spells-update.component';
import { SpellsService } from 'app/entities/spells/spells.service';
import { Spells } from 'app/shared/model/spells.model';

describe('Component Tests', () => {
    describe('Spells Management Update Component', () => {
        let comp: SpellsUpdateComponent;
        let fixture: ComponentFixture<SpellsUpdateComponent>;
        let service: SpellsService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DungeonsAndDatabasesTestModule],
                declarations: [SpellsUpdateComponent]
            })
                .overrideTemplate(SpellsUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SpellsUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SpellsService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new Spells(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.spells = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new Spells();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.spells = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.create).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));
        });
    });
});
