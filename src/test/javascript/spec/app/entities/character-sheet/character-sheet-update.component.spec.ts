/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { DungeonsandDatabasesTestModule } from '../../../test.module';
import { CharacterSheetUpdateComponent } from 'app/entities/character-sheet/character-sheet-update.component';
import { CharacterSheetService } from 'app/entities/character-sheet/character-sheet.service';
import { CharacterSheet } from 'app/shared/model/character-sheet.model';

describe('Component Tests', () => {
    describe('CharacterSheet Management Update Component', () => {
        let comp: CharacterSheetUpdateComponent;
        let fixture: ComponentFixture<CharacterSheetUpdateComponent>;
        let service: CharacterSheetService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DungeonsandDatabasesTestModule],
                declarations: [CharacterSheetUpdateComponent]
            })
                .overrideTemplate(CharacterSheetUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(CharacterSheetUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CharacterSheetService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new CharacterSheet(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.characterSheet = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new CharacterSheet();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.characterSheet = entity;
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
