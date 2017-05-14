# Projekt eszközök feladat dokumentációja
## Könyvtáros nyilvántartó rendszer

### Tartalomjegyzék
1. Követelményanalízis
2. Tervezés
3. Implementáció
4. Tesztelés
5. Felhasználói dokumentáció
6. Felhasznált források


### 1. Követelményanalízis

#### 1.1. Célkitűzés

A projekt célja egy olyan könyvtáros nyilvántartó alkalmazás elkészítése, amely lehetőséget nyújt a könyvtárak, könyvtárosok és olvasók adatainak tárolására, valamint megkönnyíti a kölcsönzési és előjegyzési folyamatok kezelését, nyilvántartását.

#### 1.2. Követelmények összegyűjtése

##### 1.2.1. Funkcionális elvárások

1. Vendégként, olvasóként, könyvtárosként szeretném tudni megtekinteni a könyvtár adatait.
2. Vendégként, olvasóként, könyvtárosként szeretném tudni megtekinteni a könyvtár könyvállományát, és a könyvek adatait.
3. Vendégként, olvasóként, könyvtárosként szeretnék tudni könyvekre keresni egy vagy több könyvadat megadásával.
4. Vendégként szeretnék tudni beiratkozni (regisztrálni) a könyvtárba.
5. Olvasóként szeretném tudni módosítani felhasználói adataimat a felhasználói nevem (username) kivételével.
6. Olvasóként szeretnék tudni előjegyezni könyvre.
7. Olvasóként szeretném tudni visszavonni az előjegyzésemet.
8. Olvasóként szeretnék tudni meghosszabbítani olyan kölcsönzést, amelynek a határideje hamarosan lejár, és nincs a kölcsönzött könyvre olyan előjegyzés, amely azért nem teljesül, mert a könyv összes példánya ki van kölcsönözve.
9. Olvasóként szeretném tudni megtekinteni az eddigi összes kölcsönzéseim adatait.
10. Olvasóként szeretném tudni megtekinteni az egyenlegem információit.
	- Tartalmazza a következőket: eddigi befizetéseim összege, eddigi késedelmi díjaim összege, és a két összeg különbsége.
11. Olvasóként szeretnék az alkalmazás főoldalán összesítést kapni a jelenleg érvényben lévő kölcsönzéseimről.
12. Olvasóként szeretnék az alkalmazás főoldalán értesítést kapni, ha valamelyik kölcsönzésem határideje hamarosan lejár vagy már lejárt.
13. Olvasóként szeretnék az alkalmazás főoldalán értesítést kapni, ha valamelyik előjegyzésem kölcsönözhetővé vált.
14. Olvasóként szeretnék az alkalmazás főoldalán értesítést kapni, ha tartozásom van. Tartozás kölcsönzési határidő átlépése esetén kerül kivetésre.
15. Könyvtárosként szeretném tudni a kölcsönözhetővé vált előjegyzéseket kikölcsönzöttre állítani, amikor a könyv példánya fizikailag kiadásra kerül a könyvtárból.
	- A kikölcsönzésre kerülő példányt ki lehessen választani a könyv szabad példányainak listájából. 
16. Könyvtárosként szeretném tudni regisztrálni az olvasók pénzbefizetéseit.
17. Könyvtárosként szeretném tudni a kölcsönzést lezárt státuszúra állítani, ha a könyv példánya fizikailag visszavételre került a könyvtárban.
18. Könyvtárosként szeretnék tudni könyvet állományba venni az adatok (beleértve a példányszámot is) megadásával.
	- Ha ilyen könyvből már van az állományban, mindössze újabb példányok állományba vétele történik, akkor ki tudjam választani az adott könyvet, és csak a példányszámot kelljen megadnom.
19. Könyvtárosként szeretnék az alkalmazás főoldalán összesítést kapni azokról az előjegyzésekről, amik kölcsönözhetővé váltak.
20. Könyvtárosként szeretnék az alkalmazás főoldalán összesítést kapni azokról a kölcsönzésekről és a kölcsönzők adatairól, amelyek túllépték a kölcsönzési határidőt.

##### 1.2.2. Nem funkcionális elvárások

1. Felhasználóbarát elrendezésű és kinézetű felhasználói felület.
2. Az alkalmazás jellegéhez mérten elvárható gyors működés.
3. Biztonságos működés: a jelszavak biztonságosan vannak tárolva, bizonyos funkciók csak megfelelő jogkörrel érhetők el autentikációt követően.
4. Hibásan bevitt adatok esetén a felhasználó kapjon figyelmeztetést az érintett mezőknél.

#### 1.3. Szakterületi fogalomjegyzék

- **Olvasó (Reader):** olyan személy, aki regisztrált az alkalmazás felületén, és képes a könyvkölcsönzéshez szükséges információk megtekintésére (pl. könyvtár állománya, könyvek adatai), folyamatok kezdeményezésére (pl. előjegyzés).
	> Az adatbázisban Reader ID-vel, felhasználói névvel (username), teljes névvel, email címmel, telefonszámmal, lakcímmel, jelszóval, eddig befizetett összeggel és eddig összegyűjtött késedelmi díjjal rendelkező objektum.
	
- **Könyvtáros (Librarian):** olyan személy, aki a könyvtár alkalmazottja, képes a könyvtár információinak megtekintésére és a kölcsönzések, késedelmi díjak, állományba vételek kezelésére.
	- Az egyszerűség érdekében egyetlen könyvtáros van, amely fix belépési adatokkal rendelkezik.
- **Könyvtár (Library):** olyan intézmény, amely könyveket tárol, könyvtárost foglalkoztat, olvasókat tart nyilván, és a könyveket kiadja kölcsönbe.
	> Az adatbázisban Library ID-vel, könyvtárnévvel, címmel, alapítási dátummal rendelkező objektum.
	
	- Az egyszerűség érdekében egyetlen fix könyvtár van, törlésére, adatainak módosítására és új könyvtár létrehozására nincs lehetőség. Ennek megfelelően minden entitás ehhez a könyvtárhoz tartozik.
- **Könyv (Book):** a könyvtár állományában lévő kikölcsönözhető dokumentum.
	> Az adatbázisban Book ID-vel, szerzővel, címmel, alcímmel, kiadó nevével, megjelenés évével, egyéb információval, ISBN-nel rendelkező objektum.
	
	- Lehet a könyv egyes példányaira előjegyzést tenni, azokat kölcsönbe kiadni és visszavenni.
	- A könyv példányait külön kezeljük egy példányokat tároló tábla használatával.
- **Példány (Copy):** egy adott könyv példánya.
	> Az adatbázisban Copy ID-vel, Book ID-vel, státusszal rendelkező objektum.
	
	- A státusz lehet kölcsönzött (borrowed) / elérhető (available).
- **Kölcsönzés (Borrow):** az olvasók kikölcsönözhetik a könyveket, amelyekre korábban előjegyeztek. A kölcsönzés befejeztével a kölcsönzés adatai nem törlődnek.
	> Az adatbázisban Borrow ID-vel, User ID-vel, Copy ID-vel, kölcsönzés kezdő időponttal, kölcsönzés lejárati időponttal, kölcsönzés befejezési időponttal, meghosszabítások számával rendelkező objektum.
	
	- A kölcsönzés létrejöttekor ki lehet választani a kölcsönzendő példányt a könyv szabad példányai közül.
- **Előjegyzés (Appointment):** az olvasók előjegyezhetik a könyveket. A könyvtáros az előjegyzés alapján kölcsönzést rögzít, amikor a könyv fizikailag kiadásra kerül a könyvtárból. A kölcsönzés rögzítésekor az előjegyzés rekordja törlődik.
	> Az adatbázisban Appointment ID-vel, User ID-vel, Book ID-vel, előjegyzési időponttal rendelkező objektum.
	
	- Egy előjegyzés akkor válik kölcsönözhetővé, ha az előjegyzett könyvből a nem kikölcsönzött példányok száma legalább 1.
	- Az előjegyzések kölcsönözhetővé válása az előjegyzési időpontok növekvő sorrendű figyelembe vételével történik. Például ha adott könyvre van 5 előjegyzés, és van 2 szabad példány, akkor a 2 legrégebbi előjegyzés válik kölcsönözhetővé.

### 2. Tervezés

#### 2.1. Architektúra terv

##### 2.1.1. Oldaltérkép

**Publikus**

**Bejelentkezett olvasó**

**Bejelentkezett könyvtáros**

##### 2.1.2. Végpontok
- **GET/index:** Főoldal
- **GET/book_list:** Könyveink
- **GET/login:** Bejelentkezés
- **GET/registration:** Regisztráció

#### 2.2. Felhasználóifelület vázlatok

#### 2.3. Osztálymodell

##### 2.3.1. Adatmodell

| Readers   | *Library* | Books      | Copies  | Borrows    | Appointments   |
| --------- | -------   | ---------- | ------- | ---------- | -------------- |
|`reader_id`|`library_id`|`book_id`  |`copy_id`|`borrow_id` |`appointment_id`|
| username  | name      | author     |`book_id`|`reader_id` |`reader_id`     |
| fullname  | address   | title      | status  |`copy_id`   |`book_id`       |
| email     | year      | subtitle   |         | created_at | created_at     |
| telefon   |           | publisher  |         | deadline   |                |
| address   |           | year       |         | closed_at  |                |
| password  |           | isbn       |         | renewals   |                |
| paid_amount|          | details    |         |            |                |
| fee_amount ||||||

##### 2.3.2. Adatbázisterv

##### 2.3.3. Állapotdiagram

### 3. Implementáció

#### 3.1. Fejlesztői környezet

#### 3.2. Könyvtárstruktúra

#### 3.3. Szerveroldali fejlesztés
##### Dokumentáció generálás
A *projeszk/javadoc* mappába minden commitnál generáljunk Java dokumentációt!

#### 3.4. Kliensoldali fejlesztés

#### 3.5. Kódtár használata
1. Mindenki külön branchek vagy forkolás használatával dolgozik.
2. Ha valaki elkészült egy programrésszel, készít egy pull requestet véleményezés céljából.
3. A fölösleges fájlokat ne commitoljuk! Ilyenek például az IDE által hagyott bináris fájlok és a *target* mappa tartalma. Commit előtt mindig nyomjunk egy ``` mvn clean ``` parancsot a  *projeszk* mappában!
4. Lokális kódtárban a változások stagelése, commitolása és pusholása (a *projeszk* mappából):
	- ``` mvn clean ```
	- ``` git add . ```
	- ``` git commit -am "Változtatás leírása" ```
	- ``` git push ```

### 4. Tesztelés

#### 4.1. Minta bejelentkezési adatok

#### 4.2. Tesztesetek

#### 4.3. Automatizált tesztelés

### 5. Felhasználói dokumentáció

#### 5.1. Ajánlott hardver-, szerverkonfiguráció

#### 5.2. Telepítés
##### 1. PostgreSQL telepítése (Ubuntu)
``` sudo apt-get install postgresql postgresql-contrib ```

Csatlakozzunk *postgres* felhasználóként a szerverhez:

``` sudo -u postgres psql postgres ```

Állítsuk be *postgres* felhasználóhoz a *Rep1l2t34* jelszót:

``` \password Rep1l2t34 ```

Hozzunk létre üres adatbázist a következő névvel: *library*

```  sudo -u postgres createdb library ```

##### 2. Maven telepítése (Ubuntu)
``` sudo apt install maven ```

##### 3/a Master branch klónozása
``` git clone https://github.com/tengex/projeszk.git ```

##### 3/b Tetszőleges branch klónozása
``` git clone -b BRANCH_NEVE --single-branch https://github.com/tengex/projeszk.git ```

##### 4. Futtatás

A ```projeszk``` mappából parancssorral:

``` mvn spring-boot:run ```

URL: ```http://localhost:8080/```


#### 5.3. A program használata

#### 6. Felhasznált források
