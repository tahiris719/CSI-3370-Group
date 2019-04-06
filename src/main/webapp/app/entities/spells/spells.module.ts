import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { DungeonsAndDatabasesSharedModule } from 'app/shared';
import {
    SpellsComponent,
    SpellsDetailComponent,
    SpellsUpdateComponent,
    SpellsDeletePopupComponent,
    SpellsDeleteDialogComponent,
    spellsRoute,
    spellsPopupRoute
} from './';

const ENTITY_STATES = [...spellsRoute, ...spellsPopupRoute];

@NgModule({
    imports: [DungeonsAndDatabasesSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [SpellsComponent, SpellsDetailComponent, SpellsUpdateComponent, SpellsDeleteDialogComponent, SpellsDeletePopupComponent],
    entryComponents: [SpellsComponent, SpellsUpdateComponent, SpellsDeleteDialogComponent, SpellsDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class DungeonsAndDatabasesSpellsModule {}
