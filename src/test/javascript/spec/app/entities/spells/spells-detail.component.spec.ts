/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { DungeonsAndDatabasesTestModule } from '../../../test.module';
import { SpellsDetailComponent } from 'app/entities/spells/spells-detail.component';
import { Spells } from 'app/shared/model/spells.model';

describe('Component Tests', () => {
    describe('Spells Management Detail Component', () => {
        let comp: SpellsDetailComponent;
        let fixture: ComponentFixture<SpellsDetailComponent>;
        const route = ({ data: of({ spells: new Spells(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DungeonsAndDatabasesTestModule],
                declarations: [SpellsDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(SpellsDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SpellsDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.spells).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
