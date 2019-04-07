import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ISpellBook } from 'app/shared/model/spell-book.model';

type EntityResponseType = HttpResponse<ISpellBook>;
type EntityArrayResponseType = HttpResponse<ISpellBook[]>;

@Injectable({ providedIn: 'root' })
export class SpellBookService {
    public resourceUrl = SERVER_API_URL + 'api/spell-books';
    public resourceSearchUrl = SERVER_API_URL + 'api/_search/spell-books';

    constructor(protected http: HttpClient) {}

    create(spellBook: ISpellBook): Observable<EntityResponseType> {
        return this.http.post<ISpellBook>(this.resourceUrl, spellBook, { observe: 'response' });
    }

    update(spellBook: ISpellBook): Observable<EntityResponseType> {
        return this.http.put<ISpellBook>(this.resourceUrl, spellBook, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ISpellBook>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ISpellBook[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    search(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ISpellBook[]>(this.resourceSearchUrl, { params: options, observe: 'response' });
    }
}
