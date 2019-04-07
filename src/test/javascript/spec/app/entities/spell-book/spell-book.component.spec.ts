/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { DungeonsandDatabasesTestModule } from '../../../test.module';
import { SpellBookComponent } from 'app/entities/spell-book/spell-book.component';
import { SpellBookService } from 'app/entities/spell-book/spell-book.service';
import { SpellBook } from 'app/shared/model/spell-book.model';

describe('Component Tests', () => {
    describe('SpellBook Management Component', () => {
        let comp: SpellBookComponent;
        let fixture: ComponentFixture<SpellBookComponent>;
        let service: SpellBookService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DungeonsandDatabasesTestModule],
                declarations: [SpellBookComponent],
                providers: []
            })
                .overrideTemplate(SpellBookComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SpellBookComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SpellBookService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new SpellBook(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.spellBooks[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
