import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ICharacterSheet } from 'app/shared/model/character-sheet.model';

type EntityResponseType = HttpResponse<ICharacterSheet>;
type EntityArrayResponseType = HttpResponse<ICharacterSheet[]>;

@Injectable({ providedIn: 'root' })
export class CharacterSheetService {
    public resourceUrl = SERVER_API_URL + 'api/character-sheets';

    constructor(protected http: HttpClient) {}

    create(characterSheet: ICharacterSheet): Observable<EntityResponseType> {
        return this.http.post<ICharacterSheet>(this.resourceUrl, characterSheet, { observe: 'response' });
    }

    update(characterSheet: ICharacterSheet): Observable<EntityResponseType> {
        return this.http.put<ICharacterSheet>(this.resourceUrl, characterSheet, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ICharacterSheet>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ICharacterSheet[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
