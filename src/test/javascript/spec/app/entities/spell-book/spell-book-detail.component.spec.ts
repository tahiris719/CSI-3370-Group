/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { DungeonsAndDatabasesTestModule } from '../../../test.module';
import { SpellBookDetailComponent } from 'app/entities/spell-book/spell-book-detail.component';
import { SpellBook } from 'app/shared/model/spell-book.model';

describe('Component Tests', () => {
    describe('SpellBook Management Detail Component', () => {
        let comp: SpellBookDetailComponent;
        let fixture: ComponentFixture<SpellBookDetailComponent>;
        const route = ({ data: of({ spellBook: new SpellBook(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DungeonsAndDatabasesTestModule],
                declarations: [SpellBookDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(SpellBookDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SpellBookDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.spellBook).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
