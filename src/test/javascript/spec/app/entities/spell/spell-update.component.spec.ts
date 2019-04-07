/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { DungeonsAndDatabasesTestModule } from '../../../test.module';
import { SpellUpdateComponent } from 'app/entities/spell/spell-update.component';
import { SpellService } from 'app/entities/spell/spell.service';
import { Spell } from 'app/shared/model/spell.model';

describe('Component Tests', () => {
    describe('Spell Management Update Component', () => {
        let comp: SpellUpdateComponent;
        let fixture: ComponentFixture<SpellUpdateComponent>;
        let service: SpellService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DungeonsAndDatabasesTestModule],
                declarations: [SpellUpdateComponent]
            })
                .overrideTemplate(SpellUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SpellUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SpellService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new Spell(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.spell = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new Spell();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.spell = entity;
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
