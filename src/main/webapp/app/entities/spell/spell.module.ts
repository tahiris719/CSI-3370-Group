import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { DungeonsandDatabasesSharedModule } from 'app/shared';
import {
    SpellComponent,
    SpellDetailComponent,
    SpellUpdateComponent,
    SpellDeletePopupComponent,
    SpellDeleteDialogComponent,
    spellRoute,
    spellPopupRoute
} from './';

const ENTITY_STATES = [...spellRoute, ...spellPopupRoute];

@NgModule({
    imports: [DungeonsandDatabasesSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [SpellComponent, SpellDetailComponent, SpellUpdateComponent, SpellDeleteDialogComponent, SpellDeletePopupComponent],
    entryComponents: [SpellComponent, SpellUpdateComponent, SpellDeleteDialogComponent, SpellDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class DungeonsandDatabasesSpellModule {}
