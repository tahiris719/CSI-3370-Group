/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { DungeonsAndDatabasesTestModule } from '../../../test.module';
import { SpellsComponent } from 'app/entities/spells/spells.component';
import { SpellsService } from 'app/entities/spells/spells.service';
import { Spells } from 'app/shared/model/spells.model';

describe('Component Tests', () => {
    describe('Spells Management Component', () => {
        let comp: SpellsComponent;
        let fixture: ComponentFixture<SpellsComponent>;
        let service: SpellsService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DungeonsAndDatabasesTestModule],
                declarations: [SpellsComponent],
                providers: []
            })
                .overrideTemplate(SpellsComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SpellsComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SpellsService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new Spells(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.spells[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
