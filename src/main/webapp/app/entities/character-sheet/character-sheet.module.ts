import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { DungeonsandDatabasesSharedModule } from 'app/shared';
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
    imports: [DungeonsandDatabasesSharedModule, RouterModule.forChild(ENTITY_STATES)],
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
export class DungeonsandDatabasesCharacterSheetModule {}
