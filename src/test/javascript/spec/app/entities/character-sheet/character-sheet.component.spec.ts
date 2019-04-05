/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { DungeonsAndDatabasesTestModule } from '../../../test.module';
import { CharacterSheetComponent } from 'app/entities/character-sheet/character-sheet.component';
import { CharacterSheetService } from 'app/entities/character-sheet/character-sheet.service';
import { CharacterSheet } from 'app/shared/model/character-sheet.model';

describe('Component Tests', () => {
    describe('CharacterSheet Management Component', () => {
        let comp: CharacterSheetComponent;
        let fixture: ComponentFixture<CharacterSheetComponent>;
        let service: CharacterSheetService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DungeonsAndDatabasesTestModule],
                declarations: [CharacterSheetComponent],
                providers: []
            })
                .overrideTemplate(CharacterSheetComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(CharacterSheetComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CharacterSheetService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new CharacterSheet(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.characterSheets[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
