import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { DungeonsAndDatabasesSharedModule } from 'app/shared';
import {
    SpellBookComponent,
    SpellBookDetailComponent,
    SpellBookUpdateComponent,
    SpellBookDeletePopupComponent,
    SpellBookDeleteDialogComponent,
    spellBookRoute,
    spellBookPopupRoute
} from './';

const ENTITY_STATES = [...spellBookRoute, ...spellBookPopupRoute];

@NgModule({
    imports: [DungeonsAndDatabasesSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        SpellBookComponent,
        SpellBookDetailComponent,
        SpellBookUpdateComponent,
        SpellBookDeleteDialogComponent,
        SpellBookDeletePopupComponent
    ],
    entryComponents: [SpellBookComponent, SpellBookUpdateComponent, SpellBookDeleteDialogComponent, SpellBookDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class DungeonsAndDatabasesSpellBookModule {}
