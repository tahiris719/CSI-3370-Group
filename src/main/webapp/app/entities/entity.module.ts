import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
    imports: [
        RouterModule.forChild([
            {
                path: 'character-sheet',
                loadChildren: './character-sheet/character-sheet.module#DungeonsandDatabasesCharacterSheetModule'
            },
            {
                path: 'spell-book',
                loadChildren: './spell-book/spell-book.module#DungeonsandDatabasesSpellBookModule'
            },
            {
                path: 'spell',
                loadChildren: './spell/spell.module#DungeonsandDatabasesSpellModule'
            },
            {
                path: 'spell',
                loadChildren: './spell/spell.module#DungeonsandDatabasesSpellModule'
            }
            /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
        ])
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class DungeonsandDatabasesEntityModule {}
