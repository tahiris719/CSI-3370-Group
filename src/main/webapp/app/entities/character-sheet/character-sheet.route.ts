import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { CharacterSheet } from 'app/shared/model/character-sheet.model';
import { CharacterSheetService } from './character-sheet.service';
import { CharacterSheetComponent } from './character-sheet.component';
import { CharacterSheetDetailComponent } from './character-sheet-detail.component';
import { CharacterSheetUpdateComponent } from './character-sheet-update.component';
import { CharacterSheetDeletePopupComponent } from './character-sheet-delete-dialog.component';
import { ICharacterSheet } from 'app/shared/model/character-sheet.model';

@Injectable({ providedIn: 'root' })
export class CharacterSheetResolve implements Resolve<ICharacterSheet> {
    constructor(private service: CharacterSheetService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ICharacterSheet> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<CharacterSheet>) => response.ok),
                map((characterSheet: HttpResponse<CharacterSheet>) => characterSheet.body)
            );
        }
        return of(new CharacterSheet());
    }
}

export const characterSheetRoute: Routes = [
    {
        path: '',
        component: CharacterSheetComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CharacterSheets'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: CharacterSheetDetailComponent,
        resolve: {
            characterSheet: CharacterSheetResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CharacterSheets'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: CharacterSheetUpdateComponent,
        resolve: {
            characterSheet: CharacterSheetResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CharacterSheets'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: CharacterSheetUpdateComponent,
        resolve: {
            characterSheet: CharacterSheetResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CharacterSheets'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const characterSheetPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: CharacterSheetDeletePopupComponent,
        resolve: {
            characterSheet: CharacterSheetResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CharacterSheets'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
