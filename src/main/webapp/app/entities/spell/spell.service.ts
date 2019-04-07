import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ISpell } from 'app/shared/model/spell.model';

type EntityResponseType = HttpResponse<ISpell>;
type EntityArrayResponseType = HttpResponse<ISpell[]>;

@Injectable({ providedIn: 'root' })
export class SpellService {
    public resourceUrl = SERVER_API_URL + 'api/spells';
    public resourceSearchUrl = SERVER_API_URL + 'api/_search/spells';

    constructor(protected http: HttpClient) {}

    create(spell: ISpell): Observable<EntityResponseType> {
        return this.http.post<ISpell>(this.resourceUrl, spell, { observe: 'response' });
    }

    update(spell: ISpell): Observable<EntityResponseType> {
        return this.http.put<ISpell>(this.resourceUrl, spell, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ISpell>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ISpell[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    search(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ISpell[]>(this.resourceSearchUrl, { params: options, observe: 'response' });
    }
}
