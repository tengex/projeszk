# Projekt eszközök feladat dokumentációja
## Könyvtáros nyilvántartó rendszer

### Tartalomjegyzék
1. Követelményanalízis
2. Adatmodell
3. Kódtár használata
4. Telepítés
5. Tesztelés
6. Felhasznált projekt eszközök


### 1. Követelményanalízis

#### 1.1. Célkitűzés

A projekt célja egy olyan könyvtáros nyilvántartó alkalmazás elkészítése, amely lehetőséget nyújt könyvek és olvasók adatainak tárolására, valamint megkönnyíti a kölcsönzések kezelését.

#### 1.2. Követelmények összegyűjtése

##### 1.2.1. Funkcionális elvárások

1. Vendégként, olvasóként, könyvtárosként szeretném tudni megtekinteni a könyvtár adatait.
2. Vendégként, olvasóként, könyvtárosként szeretném tudni megtekinteni a könyvtár könyvállományát, és a könyvek adatait.
3. Vendégként, olvasóként, könyvtárosként szeretnék tudni könyvekre keresni egy vagy több könyvadat megadásával.
4. Vendégként szeretnék tudni beiratkozni (regisztrálni) a könyvtárba.
5. Olvasóként szeretném tudni módosítani felhasználói adataimat a felhasználói nevem (username) kivételével.
6. Olvasóként szeretném tudni törölni a profilomat.
7. Olvasóként szeretném tudni megtekinteni az eddigi összes kölcsönzéseim adatait.
8. Olvasóként szeretném tudni megtekinteni az egyenlegem információit.
	- Tartalmazza a következőket: eddigi befizetéseim összege, eddigi késedelmi díjaim összege, és a két összeg különbsége.
9. Könyvtárosként szeretnék tudni kölcsönzést regisztrálni.
	- A kikölcsönzésre kerülő példányt ki lehessen választani a könyv szabad példányainak listájából. 
10. Könyvtárosként szeretném tudni regisztrálni az olvasók pénzbefizetéseit.
11. Könyvtárosként szeretném tudni a kölcsönzést lezárt státuszúra állítani.
12. Könyvtárosként szeretnék tudni könyvet állományba venni az adatok (beleértve a példányszámot is) megadásával.
	- Ha ilyen könyvből már van az állományban, mindössze újabb példányok állományba vétele történik, akkor ki tudjam választani az adott könyvet, és csak a példányszámot kelljen megadnom.

##### 1.2.2. Nem funkcionális elvárások

1. Felhasználóbarát elrendezésű és kinézetű felhasználói felület.
2. Az alkalmazás jellegéhez mérten elvárható gyors működés.
3. Biztonságos működés: a jelszavak biztonságosan vannak tárolva, bizonyos funkciók csak megfelelő jogkörrel érhetők el autentikációt követően.
4. Hibásan bevitt adatok esetén a felhasználó kapjon figyelmeztetést az érintett mezőknél.

#### 1.3. Szakterületi fogalomjegyzék

- **Olvasó (User):** olyan személy, aki regisztrált az alkalmazás felületén, és képes a könyvkölcsönzéshez szükséges információk megtekintésére (pl. könyvtár állománya, könyvek adatai), valamint érvényes kölcsönzés meghosszabítására.
	> Az adatbázisban felhasználói névvel (username - elsődleges kulcs), teljes névvel, email címmel, telefonszámmal, lakcímmel, jelszóval, eddig befizetett összeggel és eddig összegyűjtött késedelmi díjjal rendelkező objektum.
	
- **Könyvtáros (Admin):** olyan személy, aki a könyvtár alkalmazottja, képes a könyvtár információinak megtekintésére és a kölcsönzések, késedelmi díjak, állományba vételek kezelésére.
	- Az egyszerűség érdekében egyetlen könyvtáros van, amely fix belépési adatokkal rendelkezik.
- **Könyv (Book):** a könyvtár állományában lévő kikölcsönözhető dokumentum.
	> Az adatbázisban Book ID-vel, szerzővel, címmel, alcímmel, kiadó nevével, megjelenés évével, egyéb információval, ISBN-nel rendelkező objektum.
	
	- Lehet a könyv példányait kölcsönbe kiadni és visszavenni.
	- A könyv példányait külön kezeljük egy példányokat tároló tábla használatával.
- **Példány (Copy):** egy adott könyv példánya.
	> Az adatbázisban Copy ID-vel, Book ID-vel, státusszal rendelkező objektum.
	
	- A státusz lehet kölcsönzött (borrowed) / elérhető (available).
- **Kölcsönzés (Borrow):** az olvasók kikölcsönözhetnek könyvpéldányokat, ezt a könyvtáros regisztrálja. A kölcsönzés befejeztével a kölcsönzés adatai nem törlődnek.
	> Az adatbázisban Borrow ID-vel, User ID-vel, Copy ID-vel, kölcsönzés kezdő időponttal, kölcsönzés lejárati időponttal, kölcsönzés befejezési időponttal rendelkező objektum.
	
	- A kölcsönzés létrejöttekor ki lehet választani a kölcsönzendő példányt a könyv szabad példányai közül.

### 2. Adatmodell

Minta adatok a *projeszk/data_resource/Database_raw_data.xlsx* fájlban találhatók.

| User      | Book      | Copy       | Borrow     |
| --------- | ----------| -------    | ---------- |
|`username` |`bookId`   |`copyId`    |`borrowId`  |
| fullName  | author    |`book`      |`user`      |
| email     | title     | copyStatus |`copy`      |
| phoneNum  | subtitle  |            | borrowDate |
| address   | publisher |            | expiryDate |
| password  | year      |            | closeDate  |
| paidAmount| isbn      |            |            |
| feeAmount | details   |            |            |

### 3. Kódtár használata
1. Mindenki külön branchek vagy forkolás használatával dolgozik.
2. Ha valaki elkészült egy programrésszel, készít egy pull requestet.
3. A fölösleges fájlokat ne commitoljuk! Ilyenek például az IDE által hagyott bináris fájlok és a *target* mappa tartalma. Commit előtt mindig nyomjunk egy ``` mvn clean ``` parancsot a  *projeszk* mappában!
4. Lokális kódtárban a változások stagelése, commitolása és pusholása (a *projeszk* mappából):
	- ``` mvn clean ```
	- ``` git add . ```
	- ``` git commit -am "Változtatás leírása" ```
	- ``` git push ```
5. Master branch klónozása:
``` git clone https://github.com/tengex/projeszk.git ```

6. Tetszőleges branch klónozása:
``` git clone -b BRANCH_NEVE --single-branch https://github.com/tengex/projeszk.git ```

### 4. Telepítés
#### 1. PostgreSQL telepítése (Ubuntu)
``` sudo apt-get install postgresql postgresql-contrib ```

Csatlakozzunk *postgres* felhasználóként a szerverhez:

``` sudo -u postgres psql postgres ```

Állítsuk be *postgres* felhasználóhoz a *Rep1l2t34* jelszót:

``` \password Rep1l2t34 ```

Hozzunk létre üres adatbázist a következő névvel: *library*

```  sudo -u postgres createdb library ```

#### 2. Maven telepítése (Ubuntu)
``` sudo apt install maven ```

#### 3. Futtatás

A *projeszk* mappából parancssorral:

``` mvn spring-boot:run ```

URL: ```http://localhost:8080/```

### 5. Tesztelés

#### 5.1. Minta bejelentkezési adatok

- admin:
	- Felhasználónév: ``` admin ```
	- Jelszó: ``` admin ```
- olvasó:
	- Felhasználónév: ``` user ```
	- Jelszó: ``` user ```

#### 5.2. Automatizált tesztelés

### 6. Felhasznált projekt eszközök
- git
- maven
- javadoc: ``` mvn javadoc:javadoc ```
- trello
- **tesztelő eszköz**