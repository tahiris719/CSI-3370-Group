import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Spells } from 'app/shared/model/spells.model';
import { SpellsService } from './spells.service';
import { SpellsComponent } from './spells.component';
import { SpellsDetailComponent } from './spells-detail.component';
import { SpellsUpdateComponent } from './spells-update.component';
import { SpellsDeletePopupComponent } from './spells-delete-dialog.component';
import { ISpells } from 'app/shared/model/spells.model';

@Injectable({ providedIn: 'root' })
export class SpellsResolve implements Resolve<ISpells> {
    constructor(private service: SpellsService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ISpells> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Spells>) => response.ok),
                map((spells: HttpResponse<Spells>) => spells.body)
            );
        }
        return of(new Spells());
    }
}

export const spellsRoute: Routes = [
    {
        path: '',
        component: SpellsComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Spells'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: SpellsDetailComponent,
        resolve: {
            spells: SpellsResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Spells'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: SpellsUpdateComponent,
        resolve: {
            spells: SpellsResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Spells'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: SpellsUpdateComponent,
        resolve: {
            spells: SpellsResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Spells'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const spellsPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: SpellsDeletePopupComponent,
        resolve: {
            spells: SpellsResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Spells'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
