import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Spell } from 'app/shared/model/spell.model';
import { SpellService } from './spell.service';
import { SpellComponent } from './spell.component';
import { SpellDetailComponent } from './spell-detail.component';
import { SpellUpdateComponent } from './spell-update.component';
import { SpellDeletePopupComponent } from './spell-delete-dialog.component';
import { ISpell } from 'app/shared/model/spell.model';

@Injectable({ providedIn: 'root' })
export class SpellResolve implements Resolve<ISpell> {
    constructor(private service: SpellService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ISpell> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Spell>) => response.ok),
                map((spell: HttpResponse<Spell>) => spell.body)
            );
        }
        return of(new Spell());
    }
}

export const spellRoute: Routes = [
    {
        path: '',
        component: SpellComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Spells'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: SpellDetailComponent,
        resolve: {
            spell: SpellResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Spells'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: SpellUpdateComponent,
        resolve: {
            spell: SpellResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Spells'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: SpellUpdateComponent,
        resolve: {
            spell: SpellResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Spells'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const spellPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: SpellDeletePopupComponent,
        resolve: {
            spell: SpellResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Spells'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
