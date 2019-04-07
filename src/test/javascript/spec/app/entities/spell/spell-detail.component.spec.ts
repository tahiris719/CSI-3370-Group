/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { DungeonsAndDatabasesTestModule } from '../../../test.module';
import { SpellDetailComponent } from 'app/entities/spell/spell-detail.component';
import { Spell } from 'app/shared/model/spell.model';

describe('Component Tests', () => {
    describe('Spell Management Detail Component', () => {
        let comp: SpellDetailComponent;
        let fixture: ComponentFixture<SpellDetailComponent>;
        const route = ({ data: of({ spell: new Spell(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DungeonsAndDatabasesTestModule],
                declarations: [SpellDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(SpellDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SpellDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.spell).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
