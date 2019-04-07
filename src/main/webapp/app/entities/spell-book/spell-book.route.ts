import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { SpellBook } from 'app/shared/model/spell-book.model';
import { SpellBookService } from './spell-book.service';
import { SpellBookComponent } from './spell-book.component';
import { SpellBookDetailComponent } from './spell-book-detail.component';
import { SpellBookUpdateComponent } from './spell-book-update.component';
import { SpellBookDeletePopupComponent } from './spell-book-delete-dialog.component';
import { ISpellBook } from 'app/shared/model/spell-book.model';

@Injectable({ providedIn: 'root' })
export class SpellBookResolve implements Resolve<ISpellBook> {
    constructor(private service: SpellBookService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ISpellBook> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<SpellBook>) => response.ok),
                map((spellBook: HttpResponse<SpellBook>) => spellBook.body)
            );
        }
        return of(new SpellBook());
    }
}

export const spellBookRoute: Routes = [
    {
        path: '',
        component: SpellBookComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'SpellBooks'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: SpellBookDetailComponent,
        resolve: {
            spellBook: SpellBookResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'SpellBooks'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: SpellBookUpdateComponent,
        resolve: {
            spellBook: SpellBookResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'SpellBooks'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: SpellBookUpdateComponent,
        resolve: {
            spellBook: SpellBookResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'SpellBooks'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const spellBookPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: SpellBookDeletePopupComponent,
        resolve: {
            spellBook: SpellBookResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'SpellBooks'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
