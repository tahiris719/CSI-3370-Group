/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { CharacterSheetService } from 'app/entities/character-sheet/character-sheet.service';
import {
    ICharacterSheet,
    CharacterSheet,
    CharacterClassEnum,
    BackgroundEnum,
    RaceEnum,
    AlignmentEnum,
    DiceEnum,
    DeathSaveSuccessEnum,
    DeathSaveFailEnum
} from 'app/shared/model/character-sheet.model';

describe('Service Tests', () => {
    describe('CharacterSheet Service', () => {
        let injector: TestBed;
        let service: CharacterSheetService;
        let httpMock: HttpTestingController;
        let elemDefault: ICharacterSheet;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(CharacterSheetService);
            httpMock = injector.get(HttpTestingController);

            elemDefault = new CharacterSheet(
                0,
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                CharacterClassEnum.BARBARIAN,
                BackgroundEnum.ACOLYTE,
                'AAAAAAA',
                RaceEnum.DWARF,
                AlignmentEnum.LAWFUL_GOOD,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                DiceEnum.D4,
                DeathSaveSuccessEnum.ZERO,
                DeathSaveFailEnum.ZERO,
                'AAAAAAA',
                0,
                0,
                0,
                0,
                0,
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                0,
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                0,
                0,
                0
            );
        });

        describe('Service methods', async () => {
            it('should find an element', async () => {
                const returnedFromService = Object.assign({}, elemDefault);
                service
                    .find(123)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: elemDefault }));

                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should create a CharacterSheet', async () => {
                const returnedFromService = Object.assign(
                    {
                        id: 0
                    },
                    elemDefault
                );
                const expected = Object.assign({}, returnedFromService);
                service
                    .create(new CharacterSheet(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'POST' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a CharacterSheet', async () => {
                const returnedFromService = Object.assign(
                    {
                        createdDate: 'BBBBBB',
                        createdBy: 'BBBBBB',
                        searchKeywords: 'BBBBBB',
                        characterName: 'BBBBBB',
                        characterClass: 'BBBBBB',
                        background: 'BBBBBB',
                        playerName: 'BBBBBB',
                        race: 'BBBBBB',
                        alignment: 'BBBBBB',
                        expPoints: 1,
                        strength: 1,
                        dexterity: 1,
                        constitution: 1,
                        intelligence: 1,
                        wisdom: 1,
                        charisma: 1,
                        inspiration: 1,
                        proficiencyBonus: 1,
                        strSavingThrow: 1,
                        dexSavingThrow: 1,
                        constSavingThrow: 1,
                        intSavingThrow: 1,
                        wisSavingThrow: 1,
                        charSavingThrow: 1,
                        acrobatics: 1,
                        animalHandling: 1,
                        arcana: 1,
                        athletics: 1,
                        deception: 1,
                        history: 1,
                        insight: 1,
                        intimidation: 1,
                        investigation: 1,
                        medicine: 1,
                        nature: 1,
                        perception: 1,
                        performance: 1,
                        persuasion: 1,
                        religion: 1,
                        sleightOfHand: 1,
                        stealth: 1,
                        survival: 1,
                        passiveWisdomPerception: 1,
                        armorClass: 1,
                        initiative: 1,
                        speed: 1,
                        hpMaximum: 1,
                        currentHP: 1,
                        temporaryHP: 1,
                        hitDice: 'BBBBBB',
                        deathSaveSuccess: 'BBBBBB',
                        deathSaveFail: 'BBBBBB',
                        otherProficienciesAndLanguages: 'BBBBBB',
                        copperPieces: 1,
                        silverPieces: 1,
                        electrumPieces: 1,
                        goldPieces: 1,
                        platinumPieces: 1,
                        equipment: 'BBBBBB',
                        featuresAndTraits: 'BBBBBB',
                        attacksAndSpellCasting: 'BBBBBB',
                        alliesAndOrganizations: 'BBBBBB',
                        additionalFeaturesAndTraits: 'BBBBBB',
                        treasure: 'BBBBBB',
                        characterBackstory: 'BBBBBB',
                        age: 1,
                        height: 'BBBBBB',
                        weight: 'BBBBBB',
                        eyes: 'BBBBBB',
                        skin: 'BBBBBB',
                        hair: 'BBBBBB',
                        spellCastingClass: 'BBBBBB',
                        spellCastingAbility: 1,
                        spellSaveDC: 1,
                        spellAttackBonus: 1
                    },
                    elemDefault
                );

                const expected = Object.assign({}, returnedFromService);
                service
                    .update(expected)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'PUT' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should return a list of CharacterSheet', async () => {
                const returnedFromService = Object.assign(
                    {
                        createdDate: 'BBBBBB',
                        createdBy: 'BBBBBB',
                        searchKeywords: 'BBBBBB',
                        characterName: 'BBBBBB',
                        characterClass: 'BBBBBB',
                        background: 'BBBBBB',
                        playerName: 'BBBBBB',
                        race: 'BBBBBB',
                        alignment: 'BBBBBB',
                        expPoints: 1,
                        strength: 1,
                        dexterity: 1,
                        constitution: 1,
                        intelligence: 1,
                        wisdom: 1,
                        charisma: 1,
                        inspiration: 1,
                        proficiencyBonus: 1,
                        strSavingThrow: 1,
                        dexSavingThrow: 1,
                        constSavingThrow: 1,
                        intSavingThrow: 1,
                        wisSavingThrow: 1,
                        charSavingThrow: 1,
                        acrobatics: 1,
                        animalHandling: 1,
                        arcana: 1,
                        athletics: 1,
                        deception: 1,
                        history: 1,
                        insight: 1,
                        intimidation: 1,
                        investigation: 1,
                        medicine: 1,
                        nature: 1,
                        perception: 1,
                        performance: 1,
                        persuasion: 1,
                        religion: 1,
                        sleightOfHand: 1,
                        stealth: 1,
                        survival: 1,
                        passiveWisdomPerception: 1,
                        armorClass: 1,
                        initiative: 1,
                        speed: 1,
                        hpMaximum: 1,
                        currentHP: 1,
                        temporaryHP: 1,
                        hitDice: 'BBBBBB',
                        deathSaveSuccess: 'BBBBBB',
                        deathSaveFail: 'BBBBBB',
                        otherProficienciesAndLanguages: 'BBBBBB',
                        copperPieces: 1,
                        silverPieces: 1,
                        electrumPieces: 1,
                        goldPieces: 1,
                        platinumPieces: 1,
                        equipment: 'BBBBBB',
                        featuresAndTraits: 'BBBBBB',
                        attacksAndSpellCasting: 'BBBBBB',
                        alliesAndOrganizations: 'BBBBBB',
                        additionalFeaturesAndTraits: 'BBBBBB',
                        treasure: 'BBBBBB',
                        characterBackstory: 'BBBBBB',
                        age: 1,
                        height: 'BBBBBB',
                        weight: 'BBBBBB',
                        eyes: 'BBBBBB',
                        skin: 'BBBBBB',
                        hair: 'BBBBBB',
                        spellCastingClass: 'BBBBBB',
                        spellCastingAbility: 1,
                        spellSaveDC: 1,
                        spellAttackBonus: 1
                    },
                    elemDefault
                );
                const expected = Object.assign({}, returnedFromService);
                service
                    .query(expected)
                    .pipe(
                        take(1),
                        map(resp => resp.body)
                    )
                    .subscribe(body => expect(body).toContainEqual(expected));
                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify([returnedFromService]));
                httpMock.verify();
            });

            it('should delete a CharacterSheet', async () => {
                const rxPromise = service.delete(123).subscribe(resp => expect(resp.ok));

                const req = httpMock.expectOne({ method: 'DELETE' });
                req.flush({ status: 200 });
            });
        });

        afterEach(() => {
            httpMock.verify();
        });
    });
});
