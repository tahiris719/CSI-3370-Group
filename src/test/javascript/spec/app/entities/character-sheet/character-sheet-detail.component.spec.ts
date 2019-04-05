/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { DungeonsAndDatabasesTestModule } from '../../../test.module';
import { CharacterSheetDetailComponent } from 'app/entities/character-sheet/character-sheet-detail.component';
import { CharacterSheet } from 'app/shared/model/character-sheet.model';

describe('Component Tests', () => {
    describe('CharacterSheet Management Detail Component', () => {
        let comp: CharacterSheetDetailComponent;
        let fixture: ComponentFixture<CharacterSheetDetailComponent>;
        const route = ({ data: of({ characterSheet: new CharacterSheet(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DungeonsAndDatabasesTestModule],
                declarations: [CharacterSheetDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(CharacterSheetDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(CharacterSheetDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.characterSheet).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
