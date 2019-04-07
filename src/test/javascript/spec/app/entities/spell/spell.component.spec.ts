/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { DungeonsandDatabasesTestModule } from '../../../test.module';
import { SpellComponent } from 'app/entities/spell/spell.component';
import { SpellService } from 'app/entities/spell/spell.service';
import { Spell } from 'app/shared/model/spell.model';

describe('Component Tests', () => {
    describe('Spell Management Component', () => {
        let comp: SpellComponent;
        let fixture: ComponentFixture<SpellComponent>;
        let service: SpellService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DungeonsandDatabasesTestModule],
                declarations: [SpellComponent],
                providers: []
            })
                .overrideTemplate(SpellComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SpellComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SpellService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new Spell(123)],
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
