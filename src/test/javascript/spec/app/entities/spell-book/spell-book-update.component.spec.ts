/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { DungeonsandDatabasesTestModule } from '../../../test.module';
import { SpellBookUpdateComponent } from 'app/entities/spell-book/spell-book-update.component';
import { SpellBookService } from 'app/entities/spell-book/spell-book.service';
import { SpellBook } from 'app/shared/model/spell-book.model';

describe('Component Tests', () => {
    describe('SpellBook Management Update Component', () => {
        let comp: SpellBookUpdateComponent;
        let fixture: ComponentFixture<SpellBookUpdateComponent>;
        let service: SpellBookService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DungeonsandDatabasesTestModule],
                declarations: [SpellBookUpdateComponent]
            })
                .overrideTemplate(SpellBookUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SpellBookUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SpellBookService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new SpellBook(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.spellBook = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new SpellBook();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.spellBook = entity;
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
