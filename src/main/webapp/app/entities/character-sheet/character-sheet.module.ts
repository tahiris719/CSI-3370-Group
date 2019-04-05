import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { DungeonsAndDatabasesSharedModule } from 'app/shared';
import {
    CharacterSheetComponent,
    CharacterSheetDetailComponent,
    CharacterSheetUpdateComponent,
    CharacterSheetDeletePopupComponent,
    CharacterSheetDeleteDialogComponent,
    characterSheetRoute,
    characterSheetPopupRoute
} from './';

const ENTITY_STATES = [...characterSheetRoute, ...characterSheetPopupRoute];

@NgModule({
    imports: [DungeonsAndDatabasesSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        CharacterSheetComponent,
        CharacterSheetDetailComponent,
        CharacterSheetUpdateComponent,
        CharacterSheetDeleteDialogComponent,
        CharacterSheetDeletePopupComponent
    ],
    entryComponents: [
        CharacterSheetComponent,
        CharacterSheetUpdateComponent,
        CharacterSheetDeleteDialogComponent,
        CharacterSheetDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class DungeonsAndDatabasesCharacterSheetModule {}
